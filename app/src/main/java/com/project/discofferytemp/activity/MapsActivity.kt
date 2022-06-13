package com.project.discofferytemp.activity

import android.Manifest
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.project.discofferytemp.R
import com.project.discofferytemp.adapter.MapsAdapter
import com.project.discofferytemp.api.ApiService
import com.project.discofferytemp.databinding.ActivityMapsBinding
import com.project.discofferytemp.helper.GoogleMaps
import com.project.discofferytemp.model.Results
import com.project.discofferytemp.viewmodel.MapsViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryMaps
import java.util.concurrent.TimeUnit

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // membuat location request dan location callback
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var flag: Boolean = false
    private lateinit var lastLocation: Location
    private lateinit var nearetsStore: ApiService
    private lateinit var mapsViewModel: MapsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mapsViewModel = ViewModelProvider(this@MapsActivity,ViewModelFactoryMaps.getInstance()).get(MapsViewModel::class.java)
        nearetsStore = GoogleMaps.network

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (this.flag) {
            binding.btnStart.visibility = View.VISIBLE
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        getMyLastLocation()
        createLocationRequest()
        createLocationCallback()
        //startLocationUpdates()

        if (this.flag == false) {
            binding.btnStart.visibility = View.GONE
        } else {
            binding.btnStart.visibility = View.VISIBLE
        }

        // binding.btnStart.visibility = View.VISIBLE
        updateText(this.flag)

        binding.btnStart.setOnClickListener {
            if (this.flag) {
                if (this.latitude != 0.0) {
                    showStartMarker(this.latitude, this.longitude)
                }
                this.flag = false
                updateText(false)
                stopLocationUpdates()
            } else {
                nearestStore()
            }
        }

    }

    private fun nearestStore(param: String ="cafe") {
        val url = GoogleMaps.geturl(this.latitude, this.longitude, param)
        mapsViewModel.getNearestStore(url)
        mapsViewModel.place.observe(this){place->
            if (place.result.isNotEmpty() && !place.status.equals("Error")){
                showMarkerNearby(place.result)
                showData1(place.result as ArrayList<Results>)
            }else{
                Toast.makeText(this,"DATA TIDAK ADA",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showMarkerNearby(paramData:List<Results>){
        for (i in paramData.indices) {
            val lat = paramData.get(i).geometri.location.lat
            val lng = paramData.get(i).geometri.location.lng
            val placename = paramData.get(i).name
            val parmlatLng = LatLng(lat , lng)
            showStartMarker(parmlatLng, placename , i.toString())
        }
    }

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)

                latitude = location.lastLocation.latitude
                longitude = location.lastLocation.longitude
                lastLocation = location.lastLocation
            }
        }

    }

    private fun showData1(value: ArrayList<Results>) {
        binding.rcMap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = MapsAdapter(this)
        adapter.setdata(value)
        binding.rcMap.adapter = adapter
        binding.btnStart.visibility = View.GONE
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(1)
            maxWaitTime = TimeUnit.SECONDS.toMillis(1)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(this)

        client.checkLocationSettings(builder.build())
            .addOnSuccessListener {
                getMyLastLocation()
            }
            .addOnFailureListener { ex ->
                if (ex is ResolvableApiException) {
                    try {

                        resolutionLauncher.launch(
                            IntentSenderRequest.Builder(ex.resolution).build()
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        Toast.makeText(this@MapsActivity, sendEx.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                    // Precise location access granted.
                    getMyLastLocation()
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    // Only approximate location access granted.
                    getMyLastLocation()
                }
                else -> {

                }
            }
        }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getMyLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {

                    this.latitude = location.latitude
                    this.longitude = location.longitude
                    showStartMarker(this.latitude, this.longitude)
                    this.flag = false
                    updateText(false)
                    binding.btnStart.visibility = View.VISIBLE
                } else {
                    showToast("Location not found ")
                    this.flag = true
                    updateText(this.flag)
                }
            }

        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun showStartMarker(lat: Double, long: Double) {
        val startLocation = LatLng(lat, long)
        mMap.addMarker(
            MarkerOptions()
                .position(startLocation)
                .title("Posisi Saat Ini")

        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 17f))
    }

    private fun showStartMarker(
        latLng: LatLng,
        paramTitle: String = "Posisi Saat ini",
        paramSnippet: String
    ) {
        //val startLocation = latLng
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(paramTitle)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .snippet(paramSnippet)
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f))
    }

    private fun showToast(pesan: String) {
        Toast.makeText(
            this@MapsActivity,
            pesan,
            Toast.LENGTH_SHORT
        ).show()
    }

    private val resolutionLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    startLocationUpdates()
                    Thread.sleep(3000)
                    binding.btnStart.visibility = View.VISIBLE
                }
                RESULT_CANCELED ->
                    Toast.makeText(
                        this@MapsActivity,
                        "Anda harus mengaktifkan GPS untuk menggunakan aplikasi ini!",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    private fun startLocationUpdates() {
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (exception: SecurityException) {
            showToast(exception.message.toString())
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun updateText(param: Boolean) {
        if (param) {
            binding.btnStart.setText(getString(R.string.dapat_Lokasi))
        } else {
            binding.btnStart.setText(getString(R.string.explore_kopi))
        }
    }
}
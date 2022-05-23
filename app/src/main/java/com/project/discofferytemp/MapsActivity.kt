package com.project.discofferytemp

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
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.project.discofferytemp.databinding.ActivityMapsBinding
import java.util.concurrent.TimeUnit

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    // membuat location request dan location callback
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude  =0.0F
    private var longitude =0.0F
    private  var flag:Boolean =false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        getMyLastLocation()
        createLocationRequest()
        createLocationCallback()
        //startLocationUpdates()

        binding.btnStart.visibility = View.GONE


       // binding.btnStart.visibility = View.VISIBLE

        updateText(this.flag)

        binding.btnStart.setOnClickListener {
            if (this.flag){

                if (this.latitude != 0.0F){
                    showStartMarker(LatLng(this.latitude.toDouble(),this.longitude.toDouble()))
                }

                this.flag =false
                updateText(false)
                stopLocationUpdates()

            } else{
                showToast("Fitur belum tersedia")
            }
        }

    }

    private fun createLocationCallback() {
        if (latitude != 0.0f){
            binding.btnStart.visibility = View.VISIBLE
        }
        locationCallback = object : LocationCallback(){
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)

                latitude  = location.lastLocation.latitude.toFloat()
                longitude = location.lastLocation.longitude.toFloat()

            }
        }

    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval    = TimeUnit.SECONDS.toMillis(1)
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
            .addOnFailureListener{ex->
                if (ex is ResolvableApiException){
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
        if  (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    showStartMarker(location)
                    this.latitude  = location.latitude.toFloat()
                    this.longitude = location.longitude.toFloat()


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

    private fun showStartMarker(location: Location) {
        val startLocation = LatLng(location.latitude, location.longitude)
        mMap.addMarker(
            MarkerOptions()
                .position(startLocation)
                .title("Posisi Saat Ini")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 17f))
    }
    private fun showStartMarker(latLng: LatLng) {
        val startLocation = latLng
        mMap.addMarker(
            MarkerOptions()
                .position(startLocation)
                .title("Posisi Saat Ini")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 17f))
    }
    private fun showToast(pesan:String){
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
                RESULT_OK ->{
                    startLocationUpdates()
                    Thread.sleep(3000)
                    binding.btnStart.visibility =View.VISIBLE

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
    private fun updateText(param:Boolean){
        if (param){

            binding.btnStart.setText("Dapatkan lokasi saat ini")
        }else{

            binding.btnStart.setText("Explore kopi")
        }

    }
}
package com.project.discofferytemp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.project.discofferytemp.api.ApiService
import com.project.discofferytemp.databinding.ActivityViewPlaceBinding
import com.project.discofferytemp.helper.GoogleMaps
import com.project.discofferytemp.model.PlaceDetail
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

class ViewPlace : AppCompatActivity() {
    private lateinit var binding:ActivityViewPlaceBinding
    private lateinit var mServices:ApiService
    private var mPlace:PlaceDetail? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityViewPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mServices =GoogleMaps.network
        binding.placeName.text =""
        binding.placeAddress.text=""
        binding.placeOpenHours.text=""

        binding.btnShowMap.setOnClickListener {
            val map =Intent(Intent.ACTION_VIEW, Uri.parse(mPlace!!.result!!.url))
            startActivity(map)
        }

        if (GoogleMaps.currentResult!!.photos != null && GoogleMaps.currentResult!!.photos.size>0){
            Picasso.with(this)
                .load(getPhotoOfPlace(GoogleMaps.currentResult!!.photos[0].photo_reference,1000))
                .into(binding.photo)
        }

        if (GoogleMaps.currentResult!!.rating != null ){
            binding.raingBar.rating =GoogleMaps.currentResult!!.rating.toFloat()

        }else{
            binding.raingBar.visibility =View.GONE
        }

        if (GoogleMaps.currentResult!!.opening_hours != null ){
            binding.placeOpenHours.text ="" +GoogleMaps.currentResult!!.opening_hours.open_now
        }else{
            binding.placeOpenHours.visibility =View.GONE
        }

        mServices.getDetailStore(getPlaceDetail(GoogleMaps.currentResult!!.place_id)).enqueue(object:retrofit2.Callback<PlaceDetail>{
            override fun onResponse(call: Call<PlaceDetail>, response: Response<PlaceDetail>) {
              mPlace = response!!.body()
                binding.placeAddress.text =mPlace!!.result!!.formatted_address
                binding.placeName.text =mPlace!!.result!!.name

            }

            override fun onFailure(call: Call<PlaceDetail>, t: Throwable) {
              
            }

        })

    }

    private fun getPlaceDetail(placeId: String): String {
     val url = StringBuilder("https://maps.googleapis.com/maps/api/place/details/json")
        url.append("?place_id=$placeId")
        url.append("&key=AIzaSyBEnAC2oDIfNxqS9vtgB6Bx0-XEM9GY2bY")
        Log.d("place", "getPlaceDetail: "+url.toString())
        return  url.toString()
    }

    private fun getPhotoOfPlace(photoReference: String, i: Int): String {
        val url = StringBuilder("https://maps.googleapis.com/maps/api/place/photo")
        url.append("?maxwidth=$i")
        url.append("&photoreference=$photoReference ")
        url.append("&key=AIzaSyBEnAC2oDIfNxqS9vtgB6Bx0-XEM9GY2bY")
        Log.d("place", "getPlaceDetail: "+url.toString())
        return  url.toString()
    }
}
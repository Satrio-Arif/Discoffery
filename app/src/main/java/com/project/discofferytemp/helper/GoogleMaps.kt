package com.project.discofferytemp.helper


import com.project.discofferytemp.api.ApiConfig
import java.lang.StringBuilder

object GoogleMaps {
    private const val GOOGLE_API_URL = "https://maps.googleapis.com/"
    val network = ApiConfig.getApi(GOOGLE_API_URL)

    fun geturl(paramlatitude: Double, paramlongitude: Double, param: String): String {
        val googlePlaceUrl =
            StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
        googlePlaceUrl.append("?location=${paramlatitude},${paramlongitude}")
        googlePlaceUrl.append("&radius=500")
        googlePlaceUrl.append("&type=$param")
        googlePlaceUrl.append("&key=AIzaSyBEnAC2oDIfNxqS9vtgB6Bx0-XEM9GY2bY")
        return googlePlaceUrl.toString()
    }

    fun getPhotoOfPlace(photoReference: String, i: Int): String {
        val url = StringBuilder("https://maps.googleapis.com/maps/api/place/photo")
        url.append("?maxwidth=$i")
        url.append("&photoreference=$photoReference ")
        url.append("&key=AIzaSyBEnAC2oDIfNxqS9vtgB6Bx0-XEM9GY2bY")
        return url.toString()
    }

    fun getPlaceDetail(placeId: String): String {
        val url = StringBuilder("https://maps.googleapis.com/maps/api/place/details/json")
        url.append("?place_id=$placeId")
        url.append("&key=AIzaSyBEnAC2oDIfNxqS9vtgB6Bx0-XEM9GY2bY")
        return url.toString()
    }
}
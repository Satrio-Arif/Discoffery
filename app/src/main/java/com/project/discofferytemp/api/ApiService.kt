package com.project.discofferytemp.api


import com.project.discofferytemp.model.Place
import com.project.discofferytemp.model.PlaceDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getNearestStore(@Url url:String):Call<Place>

    @GET
    fun getDetailStore(@Url url:String):Call<PlaceDetail>
}
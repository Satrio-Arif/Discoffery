package com.project.discofferytemp.api
import com.project.discofferytemp.model.Place
import com.project.discofferytemp.model.PlaceDetail
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getNearestStore(@Url url:String):Place

    @GET
    suspend fun getDetailStore(@Url url:String):PlaceDetail
}
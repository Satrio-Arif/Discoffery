package com.project.discofferytemp.repository

import com.project.discofferytemp.api.ApiService
import com.project.discofferytemp.model.Place
import com.project.discofferytemp.model.PlaceDetail
import java.lang.Exception

class MapsRepository(private val network: ApiService) {

    suspend fun getNearbyPlace(url: String): Place {
        var result = Place()
        try {
            result = network.getNearestStore(url)
        } catch (e: Exception) {
            result.status = "Error"
        }
        return result

    }

    suspend fun getDetailPlace(url: String): PlaceDetail {
        var result = PlaceDetail()
        try {
            result = network.getDetailStore(url)
        } catch (e: Exception) {
            result.status = "Error"
        }
        return result
    }

    companion object {
        @Volatile
        private var instance: MapsRepository? = null
        fun getInstance(
            apiService: ApiService
        ): MapsRepository =
            instance ?: synchronized(this) {
                instance ?: MapsRepository(apiService)
            }.also { instance = it }
    }
}
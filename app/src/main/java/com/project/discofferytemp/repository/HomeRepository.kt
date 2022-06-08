package com.project.discofferytemp.repository

import com.project.discofferytemp.api.ApiService
import com.project.discofferytemp.model.Articles
import java.lang.Exception

class HomeRepository (private val apiService: ApiService){
    suspend fun getArticle():ArrayList<Articles>{
        var data:ArrayList<Articles>
        try{
            data =apiService.getArticle()
        }catch (e: Exception){
            Exception(e.message)
            data = arrayListOf(Articles())
        }
        return data
    }

    companion object {
        @Volatile
        private var instance: HomeRepository? = null
        fun getInstance(apiService: ApiService): HomeRepository =
            instance ?: synchronized(this) {
                instance ?: HomeRepository(apiService)
            }.also { instance = it }
    }
}
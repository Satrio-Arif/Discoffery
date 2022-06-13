package com.project.discofferytemp.repository


import com.project.discofferytemp.api.ApiService
import com.project.discofferytemp.model.ArticleRespon


class DetailKopiRepository (private val apiService: ApiService)  {

    suspend fun getDetailKopi(param:Int):ArticleRespon{
       var data:ArticleRespon
       try{
           data = apiService.getArticleDetail(param)
       }catch (e:Exception){
           data = ArticleRespon(message = e.toString())
       }
        return  data
    }

    companion object {
        @Volatile
        private var instance: DetailKopiRepository? = null
        fun getInstance(apiService: ApiService): DetailKopiRepository =
            instance ?: synchronized(this) {
                instance ?: DetailKopiRepository(apiService)
            }.also { instance = it }
    }

}
package com.project.discofferytemp.api
import com.project.discofferytemp.model.ArticleRespon
import com.project.discofferytemp.model.Articles
import com.project.discofferytemp.model.Place
import com.project.discofferytemp.model.PlaceDetail
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getNearestStore(@Url url:String):Place

    @GET
    suspend fun getDetailStore(@Url url:String):PlaceDetail

    @GET("article_coffe")
    suspend fun getArticle():ArrayList<Articles>

    @GET("coffee")
    suspend fun getArticleDetail(@Query("id_coffee") param: Int):ArticleRespon
}
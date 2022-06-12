package com.project.discofferytemp.di

import com.project.discofferytemp.api.ApiConfig
import com.project.discofferytemp.helper.GoogleMaps
import com.project.discofferytemp.repository.*

object Injection {
    fun provideMapRepository(): MapsRepository {
        val api = GoogleMaps.network
        return MapsRepository.getInstance(api)
    }

    fun provideClasficationRepository(): ClasificationRepository {
        return ClasificationRepository.getInstance()
    }

    fun provideArticleRepository(): ArticleRepository {
        val apiService =ApiConfig.getApiArticle()
        return ArticleRepository.getInstance(apiService)
    }
    fun provideHomeRepository(): HomeRepository {
        val apiService =ApiConfig.getApiArticle()
        return HomeRepository.getInstance(apiService)
    }
    fun provideDetailKopiRepository(): DetailKopiRepository {
        return DetailKopiRepository.getInstance()
    }
}
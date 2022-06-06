package com.project.discofferytemp.di

import com.project.discofferytemp.helper.GoogleMaps
import com.project.discofferytemp.repository.ClasificationRepository
import com.project.discofferytemp.repository.MapsRepository

object Injection {
    fun provideMapRepository(): MapsRepository {
        val api = GoogleMaps.network
        return MapsRepository.getInstance(api)
    }

    fun provideClasficationRepository(): ClasificationRepository {
        return ClasificationRepository.getInstance()
    }
}
package com.project.discofferytemp.helper

import com.project.discofferytemp.api.ApiConfig
import com.project.discofferytemp.model.Results

object GoogleMaps {
    private val GOOGLE_API_URL = "https://maps.googleapis.com/"
    val network = ApiConfig.getApi(GOOGLE_API_URL)
    var currentResult:Results? = null
}
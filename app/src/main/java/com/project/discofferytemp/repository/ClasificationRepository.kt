package com.project.discofferytemp.repository

import android.app.Application
import android.graphics.Bitmap
import com.project.discofferytemp.clasification.ImageClasification

class ClasificationRepository {

    private var imageClasification = ImageClasification()

    fun getClasification(bitmap: Bitmap, application: Application): FloatArray {
        return imageClasification.clasification(bitmap, application)
    }

    companion object {
        @Volatile
        private var instance: ClasificationRepository? = null
        fun getInstance(): ClasificationRepository =
            instance ?: synchronized(this) {
                instance ?: ClasificationRepository()
            }.also { instance = it }
    }
}
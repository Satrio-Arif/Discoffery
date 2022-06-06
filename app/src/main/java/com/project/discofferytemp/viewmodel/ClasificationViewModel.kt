package com.project.discofferytemp.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.*
import com.project.discofferytemp.di.Injection
import com.project.discofferytemp.repository.ClasificationRepository

import kotlinx.coroutines.launch

class ClasificationViewModel(private val clasificationRepository: ClasificationRepository) :
    ViewModel() {

    private var _data = MutableLiveData<FloatArray>()
    val data: LiveData<FloatArray> = _data

    fun clasification(bitmap: Bitmap, application: Application) {
        viewModelScope.launch {
            _data.value = clasificationRepository.getClasification(bitmap, application)
        }

    }
}

class ViewModelFactoryClasification(
    private val clasificationRepository: ClasificationRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ClasificationViewModel::class.java) -> {
                return ClasificationViewModel(clasificationRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryClasification? = null
        fun getInstance(
            clasificationRepository: ClasificationRepository = Injection.provideClasficationRepository()
        ): ViewModelFactoryClasification =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryClasification(clasificationRepository)
            }.also { instance = it }
    }
}
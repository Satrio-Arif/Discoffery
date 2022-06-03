package com.project.discofferytemp.viewmodel

import androidx.lifecycle.*
import com.project.discofferytemp.MapsActivity
import com.project.discofferytemp.di.Injection
import com.project.discofferytemp.model.Place
import com.project.discofferytemp.model.PlaceDetail
import com.project.discofferytemp.repository.MapsRepository
import kotlinx.coroutines.launch

class MapsViewModel(private val mapsRepository: MapsRepository):ViewModel() {
    private var _place = MutableLiveData<Place>()
    val place: LiveData<Place> = _place

    private var _placeDetail = MutableLiveData<PlaceDetail>()
    val placeDetail: LiveData<PlaceDetail> = _placeDetail

    fun getNearestStore(url:String){
        viewModelScope.launch {
            _place.value = mapsRepository.getNearbyPlace(url)
        }

    }

    fun getDetailStore(url:String){
        viewModelScope.launch {
            _placeDetail.value = mapsRepository.getDetailPlace(url)
        }

    }
}

class ViewModelFactoryMaps(
    private val mapsRepository: MapsRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                return MapsViewModel(mapsRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryMaps? = null
        fun getInstance(
            mapsRepository: MapsRepository = Injection.provideMapRepository()
        ): ViewModelFactoryMaps =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryMaps(mapsRepository)
            }.also { instance = it }
    }
}
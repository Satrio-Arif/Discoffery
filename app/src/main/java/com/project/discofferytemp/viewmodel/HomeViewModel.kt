package com.project.discofferytemp.viewmodel

import androidx.lifecycle.*
import com.project.discofferytemp.di.Injection
import com.project.discofferytemp.model.Articles
import com.project.discofferytemp.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository): ViewModel() {
    private var _article = MutableLiveData<ArrayList<Articles>>()
    val article: LiveData<ArrayList<Articles>> = _article

    fun getArticle(){
        viewModelScope.launch {
            _article.value =homeRepository.getArticle()
        }
    }
}

class ViewModelFactoryHome(
    private val homeRepository: HomeRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(homeRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryHome? = null
        fun getInstance(
            homeRepository: HomeRepository =Injection.provideHomeRepository()
        ): ViewModelFactoryHome =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryHome(homeRepository)
            }.also { instance = it }
    }
}
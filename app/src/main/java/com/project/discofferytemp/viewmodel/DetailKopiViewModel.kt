package com.project.discofferytemp.viewmodel

import androidx.lifecycle.*
import com.project.discofferytemp.di.Injection
import com.project.discofferytemp.model.ArticlesDetail
import com.project.discofferytemp.repository.DetailKopiRepository
import kotlinx.coroutines.launch

class DetailKopiViewModel(private val detailKopiRepository: DetailKopiRepository) :ViewModel() {

    private var _data =MutableLiveData<ArrayList<ArticlesDetail>>()
    var data:LiveData<ArrayList<ArticlesDetail>> =_data

    fun getData (){
        viewModelScope.launch {
          _data.value = detailKopiRepository.getDetailKopi()
        }
    }

}
class ViewModelFactoryDetailKopi(
    private val detailKopiRepository: DetailKopiRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(DetailKopiViewModel::class.java) -> {
                return DetailKopiViewModel(detailKopiRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryDetailKopi? = null
        fun getInstance(
           detailKopiRepository: DetailKopiRepository = Injection.provideDetailKopiRepository()
        ): ViewModelFactoryDetailKopi =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryDetailKopi(detailKopiRepository)
            }.also { instance = it }
    }
}

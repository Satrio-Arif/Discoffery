package com.project.discofferytemp.viewmodel

import androidx.lifecycle.*
import com.project.discofferytemp.di.Injection
import com.project.discofferytemp.model.Articles
import com.project.discofferytemp.repository.ArticleRepository
import kotlinx.coroutines.launch


class ArticleViewModel(private val articleRepository: ArticleRepository):ViewModel() {
    private var _article = MutableLiveData<ArrayList<Articles>>()
    val article: LiveData<ArrayList<Articles>> = _article

    fun getArticle(){
        viewModelScope.launch {
            _article.value =articleRepository.getArticle()
        }
    }
}

class ViewModelFactoryArticle(
    private val articleRepository: ArticleRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                return ArticleViewModel(articleRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryArticle? = null
        fun getInstance(
            articleRepository: ArticleRepository =Injection.provideArticleRepository()
        ): ViewModelFactoryArticle =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactoryArticle(articleRepository)
            }.also { instance = it }
    }
}
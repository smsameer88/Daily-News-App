package com.sameer.dailypulse.articles.presentation

import com.sameer.dailypulse.BaseViewModel
import com.sameer.dailypulse.articles.application.ArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {
    private val _articleState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articleState: StateFlow<ArticlesState> get() = _articleState

    init {
        getArticles()
    }

    fun getArticles(
        forceFetch: Boolean = false
    ) {
        scope.launch {
            _articleState.emit(
                ArticlesState(
                    loading = true,
                    articles = _articleState.value.articles
                )
            )

            val fetchedArticles = useCase.getArticles(forceFetch)

            _articleState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

}
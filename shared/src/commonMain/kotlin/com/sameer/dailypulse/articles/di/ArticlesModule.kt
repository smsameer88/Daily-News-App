package com.sameer.dailypulse.articles.di

import com.sameer.dailypulse.articles.data.ArticlesDataSource
import com.sameer.dailypulse.articles.data.ArticlesRepository
import com.sameer.dailypulse.articles.data.ArticlesService
import com.sameer.dailypulse.articles.application.ArticlesUseCase
import com.sameer.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articleModule = module {

    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
}
package com.sameer.dailypulse.di

import com.sameer.dailypulse.articles.presentation.ArticlesViewModel
import com.sameer.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        ArticlesViewModel(get())
        SourcesViewModel(get())
    }
}
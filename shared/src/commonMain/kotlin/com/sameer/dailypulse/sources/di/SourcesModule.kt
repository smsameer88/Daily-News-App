package com.sameer.dailypulse.sources.di

import com.sameer.dailypulse.sources.application.SourcesUseCase
import com.sameer.dailypulse.sources.data.SourcesRepository
import com.sameer.dailypulse.sources.data.SourcesService
import com.sameer.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {

    single<SourcesService> { SourcesService(get()) }
    single<SourcesRepository> { SourcesRepository(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}
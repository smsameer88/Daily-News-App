package com.sameer.dailypulse.di

import com.sameer.dailypulse.articles.di.articleModule
import com.sameer.dailypulse.sources.di.sourcesModule

val sharedKoinModule = listOf(
    articleModule,
    sourcesModule,
    networkModule
)
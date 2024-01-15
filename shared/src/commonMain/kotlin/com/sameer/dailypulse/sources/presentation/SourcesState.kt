package com.sameer.dailypulse.sources.presentation

import com.sameer.dailypulse.sources.application.Source

data class SourcesState(
    val sources: List<Source> = listOf(),
    val loading: Boolean = true,
    val error: String? = null
)
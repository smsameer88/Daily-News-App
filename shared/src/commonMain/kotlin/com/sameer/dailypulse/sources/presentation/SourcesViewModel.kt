package com.sameer.dailypulse.sources.presentation

import com.sameer.dailypulse.BaseViewModel
import com.sameer.dailypulse.sources.application.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(
    private val useCase: SourcesUseCase
) : BaseViewModel() {

    private val _sourcesState: MutableStateFlow<SourcesState> =
        MutableStateFlow(SourcesState(loading = true))

    val sourceState:StateFlow<SourcesState> get() = _sourcesState

    init {
        getSources()
    }

    fun getSources() {
        scope.launch {
            val fetchedSources = useCase.getSources()
            _sourcesState.emit(SourcesState(sources = fetchedSources))
        }
    }
}
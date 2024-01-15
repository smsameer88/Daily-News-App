package com.sameer.dailypulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel: ViewModel() {

    actual val scope = viewModelScope

}
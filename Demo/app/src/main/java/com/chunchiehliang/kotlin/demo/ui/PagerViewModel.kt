package com.chunchiehliang.kotlin.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PagerViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState>
        get() = _uiState


//    val isLoading: LiveData<Boolean>

    private val _showFabEvent = MutableLiveData<Boolean>()
    val showFabEvent: LiveData<Boolean>
        get() = _showFabEvent

    init {

    }

    fun handleFab(pageIndex : Int ){
        when(pageIndex) {
            0, 2-> _showFabEvent.value = true
            else -> _showFabEvent.value = false
        }
    }
}

data class UIState(val fabVisible: Boolean = true)
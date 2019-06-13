package com.chunchiehliang.kotlin.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PagerViewModel : ViewModel(){
    private val _transientUiState = MutableLiveData<TransientUiState>()
    val transientUiState: LiveData<TransientUiState>
        get() = _transientUiState



}
data class TransientUiState(val isAgendaPage: Boolean, val hasAnyFilters: Boolean)
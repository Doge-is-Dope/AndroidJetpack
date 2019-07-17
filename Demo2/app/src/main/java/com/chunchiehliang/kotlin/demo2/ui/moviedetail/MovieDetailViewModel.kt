package com.chunchiehliang.kotlin.demo2.ui.moviedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chunchiehliang.kotlin.demo2.domain.Movie

class MovieDetailViewModel (movieId: Long, app: Application) : AndroidViewModel(app) {

    private val _selectedMovieId = MutableLiveData<Long>()
    val selectedMovieId: LiveData<Long>
        get() = _selectedMovieId

    init {
        _selectedMovieId.value = movieId
    }
}
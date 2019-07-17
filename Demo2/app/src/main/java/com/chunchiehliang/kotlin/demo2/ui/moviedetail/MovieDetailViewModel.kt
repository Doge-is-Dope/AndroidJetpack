package com.chunchiehliang.kotlin.demo2.ui.moviedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chunchiehliang.kotlin.demo2.domain.Movie

class MovieDetailViewModel (selectedMovie: Movie, app: Application) : AndroidViewModel(app) {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    init {
        _selectedMovie.value = selectedMovie
    }
}
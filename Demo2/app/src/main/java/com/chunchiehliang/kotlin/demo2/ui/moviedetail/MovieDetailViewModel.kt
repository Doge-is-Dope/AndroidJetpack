package com.chunchiehliang.kotlin.demo2.ui.moviedetail

import android.app.Application
import androidx.lifecycle.*

class MovieDetailViewModel(movieId: Long, app: Application) : AndroidViewModel(app) {

    private val _selectedMovieId = MutableLiveData<Long>()
    val selectedMovieId: LiveData<Long>
        get() = _selectedMovieId

    init {
        _selectedMovieId.value = movieId
    }


    class Factory(private val movieId: Long, private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                return MovieDetailViewModel(movieId, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
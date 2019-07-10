package com.chunchiehliang.kotlin.demo2.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunchiehliang.kotlin.demo2.BuildConfig
import com.chunchiehliang.kotlin.demo2.model.Movie
import com.chunchiehliang.kotlin.demo2.network.MovieApi
import com.chunchiehliang.kotlin.demo2.util.createDummyList
import kotlinx.coroutines.*

class MovieViewModel : ViewModel() {

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _navigateToMovieDetail = MutableLiveData<Movie>()
    val navigateToMovieDetail: LiveData<Movie>
        get() = _navigateToMovieDetail

    fun onMovieClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }

    init {
        getNowPlayingMovies()
    }


    private fun getNowPlayingMovies() {
        coroutineScope.launch {
            try {

                Log.d("MovieViewModel", "Loading...")

                val apiKey = BuildConfig.API_KEY
                val result = MovieApi.retrofitService.getNowPlayingMovies(apiKey = apiKey, language = "en-US", page = 1)

                Log.d("MovieViewModel", "Done!")

                _movieList.value = result.movies

                Log.d("MovieViewModel", _movieList.value?.size.toString())

            } catch (e: Exception) {

            }
        }
    }

    /**
     * Called when the ViewModel is dismantled. At this point, we want to cancel all coroutines;
     * otherwise we end up with processes that have nowhere to return to
     * using memory and resources.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
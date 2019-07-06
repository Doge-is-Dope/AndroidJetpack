package com.chunchiehliang.kotlin.demo2.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunchiehliang.kotlin.demo2.model.Movie
import com.chunchiehliang.kotlin.demo2.util.createDummyList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

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
        initializeMovie()
    }

    private fun initializeMovie() {
        uiScope.launch {
            //            delay(10000)
            _movieList.value = createDummyList()
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
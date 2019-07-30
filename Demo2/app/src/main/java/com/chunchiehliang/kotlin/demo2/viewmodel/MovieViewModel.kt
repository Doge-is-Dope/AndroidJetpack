package com.chunchiehliang.kotlin.demo2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.chunchiehliang.kotlin.demo2.database.getDatabase
import com.chunchiehliang.kotlin.demo2.domain.Movie
import com.chunchiehliang.kotlin.demo2.repository.DemoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    enum class MovieApiStatus { LOADING, ERROR, DONE }

    private var viewModelJob = Job()
    private var viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)

    private val demoRepository = DemoRepository(database)

    val movieList = demoRepository.movies
//    val movieList = MediatorLiveData<List<Movie>>()

    val genreList = demoRepository.genres

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _navigateToMovieDetail = MutableLiveData<Long>()
    val navigateToMovieDetail: LiveData<Long>
        get() = _navigateToMovieDetail

    fun onMovieClicked(movieId: Long) {
        _navigateToMovieDetail.value = movieId
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }

    init {
        Timber.d("MovieViewModel")
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            try {
                _status.value = MovieApiStatus.LOADING

                demoRepository.refreshGenres()
                demoRepository.refreshMovies()

                _status.value = MovieApiStatus.DONE

            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                Timber.e("Error: ${e.message}")
                _status.value = MovieApiStatus.DONE
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

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
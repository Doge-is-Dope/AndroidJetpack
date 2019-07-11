package com.chunchiehliang.kotlin.demo2.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chunchiehliang.kotlin.demo2.BuildConfig
import com.chunchiehliang.kotlin.demo2.model.Genre
import com.chunchiehliang.kotlin.demo2.model.Movie
import com.chunchiehliang.kotlin.demo2.network.MovieApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel : ViewModel() {

    enum class MovieApiStatus { LOADING, ERROR, DONE }

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>>
        get() = _genreList

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
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        getNowPlayingMovies()
    }


    private fun getNowPlayingMovies() {
        coroutineScope.launch {
            try {
                _status.value = MovieApiStatus.LOADING

                val apiKey = BuildConfig.TMDB_API_KEY

                // Get all the genres
                _genreList.value = MovieApi.retrofitService.getGenres(apiKey = apiKey, language = "en-US").genres

                // Get the now playing movies
                val movies =
                    MovieApi.retrofitService.getNowPlayingMovies(apiKey = apiKey, language = "en-US", page = 1).movies

                for (movie in movies) {
                    val movieGenreList = mutableListOf<String>()

                    Timber.d("Movie genreIds: ${movie.genreIds}")

                    // Convert the genre list to a map
                    val genreMap = _genreList.value!!.map { genre ->
                        genre.id to genre.name
                    }.toMap()

                    for (genreId in movie.genreIds) {
                        genreMap[genreId]?.let { movieGenreList.add(it) }
                    }
                    movie.genreStrings = movieGenreList
                    Timber.d("Movie genres: ${movie.genreStrings}")
                }

                _movieList.value = movies

                _status.value = MovieApiStatus.DONE

            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
            }
        }
    }

    private fun getGenres() {
        coroutineScope.launch {
            try {
                _status.value = MovieApiStatus.LOADING

                val apiKey = BuildConfig.TMDB_API_KEY
                val result = MovieApi.retrofitService.getGenres(apiKey = apiKey, language = "en-US")

                _status.value = MovieApiStatus.DONE

                _genreList.value = result.genres

                Timber.d("Done! Genre size: ${_genreList.value?.size}")

            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
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
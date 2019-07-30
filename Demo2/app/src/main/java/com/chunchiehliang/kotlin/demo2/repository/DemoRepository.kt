package com.chunchiehliang.kotlin.demo2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.chunchiehliang.kotlin.demo2.BuildConfig
import com.chunchiehliang.kotlin.demo2.database.DatabaseMovie
import com.chunchiehliang.kotlin.demo2.database.DemoDatabase
import com.chunchiehliang.kotlin.demo2.database.asDomainModel
import com.chunchiehliang.kotlin.demo2.domain.Genre
import com.chunchiehliang.kotlin.demo2.domain.Movie
import com.chunchiehliang.kotlin.demo2.network.MovieApi
import com.chunchiehliang.kotlin.demo2.network.asDatabaseModel
import com.chunchiehliang.kotlin.demo2.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber

class DemoRepository(private val database: DemoDatabase) {

    val movies: LiveData<List<Movie>> = Transformations.map(database.movieDao.getCurrentMovies(), ::setupMovieGenres)

    val genres: LiveData<List<Genre>> = Transformations.map(database.genreDao.getGenres()) {
        //        Timber.d("Movie List in database: $it")
        it.asDomainModel()
    }

    private fun setupMovieGenres(databaseMovies: List<DatabaseMovie>): List<Movie> {
        val movies = databaseMovies.asDomainModel()
        val genreMap = genres.value?.map { genre ->
            genre.id to genre
        }?.toMap()


        movies.forEach {
            val movieGenreList = mutableListOf<Genre>()

            for (id in it.genreIds) {
                genreMap?.get(id)?.let { genre -> movieGenreList.add(genre) }
            }

            it.genres = movieGenreList
        }
        return movies
    }

    /**
     * Refresh the genres stored in the offline cache.
     */
    suspend fun refreshGenres() {
        withContext(Dispatchers.IO) {
            Timber.d("refreshing genres")
            val genreList = MovieApi.retrofitService.getGenres(apiKey = apiKey, language = language)
            Timber.d("Genre List from internet: ${genreList.asDomainModel()}")
            database.genreDao.insertAllGenres(*genreList.asDatabaseModel())
        }
    }

    /**
     * Refresh the movies stored in the offline cache.
     */
    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            Timber.d("refreshing movies")
            delay(3_000)
            val movieList = MovieApi.retrofitService.getNowPlayingMovies(apiKey = apiKey, language = language, page = 1)
            // Drop the movie that doesn't have a poster
            database.movieDao.insertAll(*movieList.asDatabaseModel().filterNot { it.posterPath.isNullOrBlank() }.toTypedArray())
        }
    }

    companion object {
        const val apiKey = BuildConfig.TMDB_API_KEY
        const val language = "en-US"
    }
}
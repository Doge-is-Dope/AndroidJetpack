package com.chunchiehliang.kotlin.demo2.network

import android.os.Parcelable
import com.chunchiehliang.kotlin.demo2.domain.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


/**
 * VideoHolder holds a list of Movies.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkMovieContainer(
    val results: List<NetworkMovie>,
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int
):Parcelable

/**
 * Movie represents a current movie in a theater
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class NetworkMovie(
    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "id")
    val id: Long,

    @Json(name = "video")
    val video: Boolean,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "title")
    val title: String,

    @Json(name = "popularity")
    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "backdrop_path")
    val backDropPath: String?,

    @Json(name = "adult")
    val adult: Boolean,

    @Json(name = "overview")
    val overview: String,

    @Json(name = "release_date")
    val releaseDate: String
):Parcelable

/**
 * Convert Network results to database objects
 */
fun NetworkMovieContainer.asDomainModel(): List<Movie> {
    return results.map {
        Movie(
            id = it.id,
            title = it.title,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            backDropPath = it.backDropPath,
            genreIds = it.genreIds,
            overview = it.overview
        )
    }
}
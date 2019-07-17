package com.chunchiehliang.kotlin.demo2.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @Json(name = "vote_count")
    val voteCount: Int,
    val id: Long,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAvg: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "backdrop_path")
    val backDropPath: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    //
    var genres: List<Genre>?,
    val overview: String,
    val title: String,
    @Json(name = "release_date") val releaseDate: String
) : Parcelable


@Parcelize
data class MovieResponse(
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "results")
    val movies: List<Movie>
) : Parcelable
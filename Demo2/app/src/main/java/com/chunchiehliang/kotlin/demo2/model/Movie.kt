package com.chunchiehliang.kotlin.demo2.model

import android.os.Parcelable
import com.squareup.moshi.Json
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
    val backDropPath: String,
    val title: String,
    @Json(name = "release_date") val releaseDate: String
) : Parcelable {}
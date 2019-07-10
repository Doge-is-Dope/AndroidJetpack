package com.chunchiehliang.kotlin.demo2.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(val page: Int,
                         @Json(name = "total_results")
                         val totalResults: Int,
                         @Json(name = "total_pages")
                         val totalPages: Int ,
                         @Json(name = "results")
                         val movies: List<Movie>):Parcelable
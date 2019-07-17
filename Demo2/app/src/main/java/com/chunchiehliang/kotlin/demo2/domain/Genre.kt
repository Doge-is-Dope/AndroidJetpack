package com.chunchiehliang.kotlin.demo2.domain

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(val id: Int, val name: String) : Parcelable

@Parcelize
data class GenreResponse(val genres: List<Genre>) : Parcelable
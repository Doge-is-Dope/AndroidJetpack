package com.chunchiehliang.kotlin.demo2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val id: Long, val title: String, val releaseDate: String) : Parcelable {}
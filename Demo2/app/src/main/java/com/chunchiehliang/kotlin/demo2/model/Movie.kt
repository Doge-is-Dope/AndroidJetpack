package com.chunchiehliang.kotlin.demo2.model

data class Movie(
    val id: Long,
    val title: String,
    val popularity: Double,
    val releaseDate: String,
    val posterPath: String?,
    val backDropPath: String?,
    val genreIds: List<Int>,
    val overview: String) {

    var genres: List<Genre> = listOf()
}
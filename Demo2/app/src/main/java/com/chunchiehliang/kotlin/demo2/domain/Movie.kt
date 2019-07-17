package com.chunchiehliang.kotlin.demo2.domain

data class Movie(
    val id: Long,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val backDropPath: String?,
    val genreIds: List<Int>,
    val overview: String) {

    var genres: List<Genre> = listOf()
}
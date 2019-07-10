package com.chunchiehliang.kotlin.demo2.util

import com.chunchiehliang.kotlin.demo2.model.Movie

fun createDummyList(): List<Movie> {
    val movieList = mutableListOf<Movie>()
    movieList.add(Movie(912,
        429617,
        false,
        7.8,
        "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
        "sdasdasd",
        "Spider-Man: Far from Home",
        "2019-06-28"))
    return movieList
}
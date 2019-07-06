package com.chunchiehliang.kotlin.demo2.util

import com.chunchiehliang.kotlin.demo2.model.Movie

fun createDummyList(): List<Movie> {
    val movieList = mutableListOf<Movie>()
    movieList.add(Movie(1, "Dark Phoenix","2019-06-05"))
    movieList.add(Movie(2, "Men in Black: International", "2019-06-12"))
    movieList.add(Movie(3, "Aladdin", "2019-05-22"))
    movieList.add(Movie(301528, "Toy Story 4","2019-06-19"))
    movieList.add(Movie(458156, "John Wick: Chapter 3 – Parabellum","2019-05-15"))
    movieList.add(Movie(373571, "Godzilla: King of the Monsters","2019-05-29"))
    movieList.add(Movie(447404, "Pokémon Detective Pikachu", "2019-05-03"))
    movieList.add(Movie(412117, "The Secret Life of Pets 2","2019-05-24"))
    movieList.add(Movie(486131, "Shaft", "2019-06-14"))
    return movieList
}
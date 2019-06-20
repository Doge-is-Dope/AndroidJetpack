package com.chunchiehliang.kotlin.demo.ui.movie

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MovieViewModel : ViewModel() {
    data class Movie(val name: String, val time: String)

    lateinit var currentWord: Movie

    val wordList: MutableList<Movie> = mutableListOf()

    init {
        setWordList()
        setWord()
    }

    private fun setWordList() {
        wordList.add(Movie("Caesar Salad", "1 hr"))
        wordList.add(Movie("Classic Rib Eye", "50 min"))
        wordList.add(Movie("French Onion Soup", "45 min"))
        wordList.add(Movie("Mango", "20 min"))
        wordList.add(Movie("Orange", "3 min"))
        wordList.add(Movie("Orange", "3 min"))
        wordList.add(Movie("Orange", "3 min"))
        wordList.add(Movie("Orange", "3 min"))
        wordList.add(Movie("Orange", "3 min"))
    }

    fun setWord() {
        currentWord = wordList[Random.nextInt(wordList.size)]
    }
}
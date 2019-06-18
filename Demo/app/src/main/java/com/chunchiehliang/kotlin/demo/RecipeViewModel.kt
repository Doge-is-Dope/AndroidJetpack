package com.chunchiehliang.kotlin.demo

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class RecipeViewModel : ViewModel() {
    data class Recipe(val name: String, val time: String)

    lateinit var currentWord: Recipe

    val wordList: MutableList<Recipe> = mutableListOf()

    init {
        setWordList()
        setWord()
    }

    private fun setWordList() {
        wordList.add(Recipe("Caesar Salad", "1 hr"))
        wordList.add(Recipe("Classic Rib Eye", "50 min"))
        wordList.add(Recipe("French Onion Soup", "45 min"))
        wordList.add(Recipe("Mango", "20 min"))
        wordList.add(Recipe("Orange", "3 min"))
    }

    fun setWord() {
        currentWord = wordList[Random.nextInt(wordList.size)]
    }
}
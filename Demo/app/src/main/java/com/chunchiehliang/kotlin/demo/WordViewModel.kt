package com.chunchiehliang.kotlin.demo

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class WordViewModel : ViewModel() {
    data class Word(val english: String, val chinese: String)

    lateinit var currentWord: Word

    val wordList: MutableList<Word> = mutableListOf()

    init {
        setWordList()
        setWord()
    }

    private fun setWordList() {
        wordList.add(Word("Apple", "蘋果"))
        wordList.add(Word("Banana", "香蕉"))
        wordList.add(Word("Cherry", "櫻桃"))
        wordList.add(Word("Mango", "芒果"))
        wordList.add(Word("Orange", "橘子"))
    }

    fun setWord() {
        currentWord = wordList[Random.nextInt(wordList.size)]
    }

    fun nextWord() {

    }
}
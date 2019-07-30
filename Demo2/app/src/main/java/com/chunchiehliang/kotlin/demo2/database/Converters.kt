package com.chunchiehliang.kotlin.demo2.database

import androidx.room.TypeConverter

class Converters {
    /**
     * Convert a list of genre id to a string
     */
    @TypeConverter
    fun listFromString(str: String): List<Int> {
        return if (str != "") {
            str.split(",").map { it.trim().toInt() }
        } else {
            emptyList()
        }
    }

    @TypeConverter
    fun listToString(list: List<Int>): String {
        return list.joinToString()
    }

}
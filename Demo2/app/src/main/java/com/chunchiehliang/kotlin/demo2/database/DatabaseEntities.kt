package com.chunchiehliang.kotlin.demo2.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.chunchiehliang.kotlin.demo2.domain.Genre
import com.chunchiehliang.kotlin.demo2.domain.Movie

@Entity
data class DatabaseMovie(
    @PrimaryKey
    val id: Long,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val backDropPath: String?,
    val genreIds: List<Int>,
    val overview: String
)

class Converters {
    @TypeConverter
    fun listFromString(str: String): List<Int> {
        return str.split(",").map { it.trim().toInt()}
    }

    @TypeConverter
    fun listToString(list: List<Int>): String {
        return list.joinToString()
    }

}

@JvmName("databaseMovieListToDomainMovieList")
fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            id = it.id,
            title = it.title,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            backDropPath = it.backDropPath,
            genreIds = it.genreIds,
            overview = it.overview
        )
    }
}

@Entity
data class DatabaseGenre(
    @PrimaryKey
    val id: Int,
    val name: String
)

@JvmName("databaseGenreListToDomainGenreList")
fun List<DatabaseGenre>.asDomainModel(): List<Genre> {
    return map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
}

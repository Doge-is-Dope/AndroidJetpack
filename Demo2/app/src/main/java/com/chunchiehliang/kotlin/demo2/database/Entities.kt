package com.chunchiehliang.kotlin.demo2.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chunchiehliang.kotlin.demo2.model.Genre
import com.chunchiehliang.kotlin.demo2.model.Movie

@Entity
data class DatabaseMovie(
    @PrimaryKey
    val id: Long,
    val title: String,
    val popularity: Double,
    val releaseDate: String,
    val posterPath: String?,
    val backDropPath: String?,
    val genreIds: List<Int>,
    val overview: String
)

@JvmName("databaseMovieListToDomainMovieList")
fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            id = it.id,
            title = it.title,
            popularity = it.popularity,
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
        Genre(id = it.id, name = it.name)
    }
}

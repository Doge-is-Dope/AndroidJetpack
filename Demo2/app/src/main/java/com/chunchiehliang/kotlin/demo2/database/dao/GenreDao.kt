package com.chunchiehliang.kotlin.demo2.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chunchiehliang.kotlin.demo2.database.DatabaseGenre


/**
 * Store all the genres in the database
 */
@Dao
interface GenreDao {
    @Query("select * from databasegenre")
    fun getGenres(): LiveData<List<DatabaseGenre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGenres(vararg genres: DatabaseGenre)
}
package com.chunchiehliang.kotlin.demo2.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chunchiehliang.kotlin.demo2.database.DatabaseMovie

@Dao
interface MovieDao {
    @Query("select * from databasemovie order by popularity desc")
    fun getCurrentMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movies: DatabaseMovie)
}
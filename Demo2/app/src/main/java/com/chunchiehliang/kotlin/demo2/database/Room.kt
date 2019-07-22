package com.chunchiehliang.kotlin.demo2.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("select * from databasemovie")
    fun getCurrentMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movies: DatabaseMovie)
}


@Dao
interface GenreDao {
    @Query("select * from databasegenre")
    fun getGenres(): LiveData<List<DatabaseGenre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseGenre)
}

@Database(entities = [DatabaseMovie::class, DatabaseGenre::class], version = 1)
@TypeConverters(Converters::class)
abstract class DemoDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val genreDao: GenreDao
}

private lateinit var INSTANCE: DemoDatabase

fun getDatabase(context: Context): DemoDatabase {
    synchronized(DemoDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                DemoDatabase::class.java,
                "demo"
            ).build()
        }
    }
    return INSTANCE
}
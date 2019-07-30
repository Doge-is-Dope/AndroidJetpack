package com.chunchiehliang.kotlin.demo2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chunchiehliang.kotlin.demo2.database.dao.GenreDao
import com.chunchiehliang.kotlin.demo2.database.dao.MovieDao


@Database(entities = [DatabaseMovie::class, DatabaseGenre::class], version = 1, exportSchema = false)
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
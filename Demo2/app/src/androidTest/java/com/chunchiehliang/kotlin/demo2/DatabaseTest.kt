package com.chunchiehliang.kotlin.demo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chunchiehliang.kotlin.demo2.database.DemoDatabase
import com.chunchiehliang.kotlin.demo2.database.asDomainModel
import com.chunchiehliang.kotlin.demo2.database.dao.MovieDao
import com.chunchiehliang.kotlin.demo2.domain.Movie
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var movieDao: MovieDao
    private lateinit var db: DemoDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, DemoDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        movieDao = db.movieDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun getMovies() {
        val movieList: LiveData<List<Movie>> = Transformations.map(movieDao.getCurrentMovies()) {
            it.asDomainModel()
        }

        Timber.d("movieList: ${movieList.value}")
    }
}

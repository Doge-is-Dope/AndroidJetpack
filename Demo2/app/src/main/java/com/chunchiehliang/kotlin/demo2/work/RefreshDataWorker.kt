package com.chunchiehliang.kotlin.demo2.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.chunchiehliang.kotlin.demo2.database.getDatabase
import com.chunchiehliang.kotlin.demo2.repository.DemoRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        Timber.d("do work")
        val database = getDatabase(applicationContext)
        val repository = DemoRepository(database)

        return try {
            repository.refreshGenres()
            repository.refreshMovies()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}

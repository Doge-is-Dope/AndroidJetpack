package com.chunchiehliang.kotlin.room.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.chunchiehliang.kotlin.room.database.SleepDatabaseDao

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application
) : AndroidViewModel(application) {
}


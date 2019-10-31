package com.chunchiehliang.notifications

import android.app.Application
import timber.log.Timber

class NotificationsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
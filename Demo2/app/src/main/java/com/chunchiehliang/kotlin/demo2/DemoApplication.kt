package com.chunchiehliang.kotlin.demo2

import android.app.Application
import timber.log.Timber

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
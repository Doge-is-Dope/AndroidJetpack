package com.chunchiehliang.kotlin.lifecycle

import android.app.Application
import timber.log.Timber

class LifecycleApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
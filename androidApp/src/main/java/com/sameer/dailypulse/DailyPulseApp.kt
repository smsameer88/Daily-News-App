package com.sameer.dailypulse

import android.app.Application
import com.sameer.dailypulse.di.databaseModule
import com.sameer.dailypulse.di.viewModelsModule
import com.sameer.dailypulse.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
         val modules = sharedKoinModule + viewModelsModule + databaseModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}
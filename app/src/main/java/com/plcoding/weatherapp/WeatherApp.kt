package com.plcoding.weatherapp

import android.app.Application
import com.plcoding.weatherapp.di.appModule
import com.plcoding.weatherapp.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(appModule, viewModels)
        }
    }
}
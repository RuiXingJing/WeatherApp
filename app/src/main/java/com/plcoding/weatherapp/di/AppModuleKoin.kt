package com.plcoding.weatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.plcoding.weatherapp.data.location.DefaultLocationTracker
import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.data.repository.WeatherRepositoryImpl
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.presentation.WeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    single {
        LocationServices.getFusedLocationProviderClient(androidContext() as Application)
    }

    single<LocationTracker> {
        DefaultLocationTracker(get(), androidContext() as Application)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }
}

val viewModels = module {
    viewModel {
        WeatherViewModel(get(), get())
    }
}

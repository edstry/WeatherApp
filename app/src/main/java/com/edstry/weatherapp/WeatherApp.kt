package com.edstry.weatherapp

import android.app.Application
import com.edstry.weatherapp.di.ApplicationComponent
import com.edstry.weatherapp.di.DaggerApplicationComponent

class WeatherApp: Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}
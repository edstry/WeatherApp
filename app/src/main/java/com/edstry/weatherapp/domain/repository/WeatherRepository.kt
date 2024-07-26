package com.edstry.weatherapp.domain.repository

import com.edstry.weatherapp.domain.entity.Weather


interface WeatherRepository {
    suspend fun getWeather(cityId: Int): Weather
    suspend fun getForecast(cityId: Int): Weather
}
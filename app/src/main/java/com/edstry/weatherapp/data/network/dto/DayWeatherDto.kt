package com.edstry.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName


/// Dto - data transfer object

data class WeatherForecastDto(
    @SerializedName("current") val current: WeatherDto,
    @SerializedName("forecast") val forecastDto: ForecastDto,
)

data class ForecastDto(
    @SerializedName("forecast") val forecastDay: List<DayDto>,
)
data class DayDto(
    @SerializedName("date_epoch") val date: Long,
    @SerializedName("condition") val dayWeatherDto: DayWeatherDto
)
data class DayWeatherDto(
    @SerializedName("avgtemp_c") val tempC: Float,
    @SerializedName("condition") val conditionDto: ConditionDto
)

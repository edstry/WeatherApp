package com.edstry.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherCurrentDto(
    @SerializedName("current") val current: WeatherDto,
)
data class WeatherDto(
    @SerializedName("last_updated_epoch") val date: Long,
    @SerializedName("temp_c") val tempC: Float,
    @SerializedName("condition") val conditionDto: ConditionDto
)

package com.edstry.weatherapp.data.mapper

import com.edstry.weatherapp.data.network.dto.CityDto
import com.edstry.weatherapp.domain.entity.City

fun CityDto.toEntity(): City = City(id, name, country)
fun List<CityDto>.toEntities(): List<City> = map { it.toEntity() }
package com.edstry.weatherapp.data.mapper

import com.edstry.weatherapp.data.local.model.CityDbModel
import com.edstry.weatherapp.domain.entity.City

fun City.toDbModel(): CityDbModel = CityDbModel(id, name, country)
fun CityDbModel.toEntity(): City = City(id, name, country)
fun List<CityDbModel>.toEntities(): List<City> = map { it.toEntity() }
package com.edstry.weatherapp.domain.repository

import com.edstry.weatherapp.domain.entity.City

interface SearchRepository {
    suspend fun search(query: String): List<City>
}
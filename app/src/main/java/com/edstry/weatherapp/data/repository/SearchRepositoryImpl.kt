package com.edstry.weatherapp.data.repository

import com.edstry.weatherapp.data.mapper.toEntities
import com.edstry.weatherapp.data.network.api.ApiService
import com.edstry.weatherapp.domain.entity.City
import com.edstry.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> {
        return apiService.searchCity(query).toEntities()
    }
}
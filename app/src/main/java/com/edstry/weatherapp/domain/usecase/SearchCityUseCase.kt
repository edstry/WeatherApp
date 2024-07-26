package com.edstry.weatherapp.domain.usecase

import com.edstry.weatherapp.domain.repository.SearchRepository
import retrofit2.http.Query
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String) = repository.search(query)
}
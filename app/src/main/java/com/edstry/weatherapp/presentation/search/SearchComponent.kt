package com.edstry.weatherapp.presentation.search

import com.edstry.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.StateFlow

interface SearchComponent {
    val model: StateFlow<SearchStore.State>
    fun changeSearchQuery(query: String)
    fun onClickBack()
    fun onClickSearch()
    fun onClickCity(city: City)
}
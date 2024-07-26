package com.edstry.weatherapp.domain.usecase

import com.edstry.weatherapp.domain.repository.FavouriteRepository
import javax.inject.Inject

/// Возвращает статус города(true or false), был ли он добавлен в избранное или нет
class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(cityId: Int) = repository.observeIsFavourite(cityId)
}
package com.edstry.weatherapp.di

import android.content.Context
import com.edstry.weatherapp.data.local.db.FavouriteCitiesDao
import com.edstry.weatherapp.data.local.db.FavouriteDatabase
import com.edstry.weatherapp.data.network.api.ApiFactory
import com.edstry.weatherapp.data.network.api.ApiService
import com.edstry.weatherapp.data.repository.FavouriteRepositoryImpl
import com.edstry.weatherapp.data.repository.SearchRepositoryImpl
import com.edstry.weatherapp.data.repository.WeatherRepositoryImpl
import com.edstry.weatherapp.domain.repository.FavouriteRepository
import com.edstry.weatherapp.domain.repository.SearchRepository
import com.edstry.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository
    @[ApplicationScope Binds]
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository
    @[ApplicationScope Binds]
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {
        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService
        @[ApplicationScope Provides]
        fun provideFavouriteDatabase(context: Context): FavouriteDatabase {
            return FavouriteDatabase.getInstance(context)
        }
        @[ApplicationScope Provides]
        fun provideFavouriteCitiesDao(database: FavouriteDatabase): FavouriteCitiesDao {
            return database.favouriteCitiesDao()
        }
    }
}
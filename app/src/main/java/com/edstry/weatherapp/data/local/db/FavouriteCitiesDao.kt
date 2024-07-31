package com.edstry.weatherapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edstry.weatherapp.data.local.model.CityDbModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouriteCitiesDao {
    @Query("SELECT * FROM favourite_cities")
    fun getFavouriteCities(): Flow<List<CityDbModel>>

    @Query("SELECT EXISTS(SELECT * FROM favourite_cities WHERE id=:cityId LIMIT 1)")
    fun observeIsFavourite(cityId: Int): Flow<Boolean> /// есть ли город в избранном

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(cityDbModel: CityDbModel) /// добавить город, если город был, то заменить

    @Query("DELETE FROM favourite_cities WHERE id=:cityId")
    suspend fun removeFromFavourite(cityId: Int)
}
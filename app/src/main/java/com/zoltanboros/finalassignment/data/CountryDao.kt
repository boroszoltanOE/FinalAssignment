package com.zoltanboros.finalassignment.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zoltanboros.finalassignment.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: Country)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(country: Country)

    @Delete
    suspend fun delete(country:Country)

    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<Country>>

    @Query("SELECT * FROM countries WHERE id=:countryId")
    fun getCountry(countryId: Int): Flow<Country>


}
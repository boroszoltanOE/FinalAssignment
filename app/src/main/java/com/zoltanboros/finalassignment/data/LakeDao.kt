package com.zoltanboros.finalassignment.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zoltanboros.finalassignment.model.Lake
import kotlinx.coroutines.flow.Flow

@Dao
interface LakeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lake: Lake)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(lake: Lake)

    @Delete
    suspend fun delete(lake:Lake)

    @Query("SELECT * FROM lakes")
    fun getAllLakes(): Flow<List<Lake>>

    @Query("SELECT * FROM lakes WHERE id=:lakeId")
    fun getLake(lakeId: Int): Flow<Lake>

    @Query("SELECT * FROM countries INNER JOIN lakes ON countries.id = lakes.countryIdFk WHERE countries.id=:countryId")
    fun getLakesForCountry(countryId: Int): Flow<List<Lake>>

    @Query("SELECT * FROM lakes WHERE lakes.selected = 1")
    fun getSelectedLakes(): Flow<List<Lake>>

    @Query("UPDATE lakes SET selected = 0")
    suspend fun clearSelectedLakes()
}
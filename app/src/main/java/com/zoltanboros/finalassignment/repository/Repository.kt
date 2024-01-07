package com.zoltanboros.finalassignment.repository

import com.zoltanboros.finalassignment.data.CountryDao
import com.zoltanboros.finalassignment.data.LakeDao
import com.zoltanboros.finalassignment.model.Country
import com.zoltanboros.finalassignment.model.Lake

class Repository(
    private val countryDao:CountryDao,
    private val lakeDao: LakeDao
) {
    val countries = countryDao.getAllCountries()
    val lakes = lakeDao.getAllLakes()
    val selectedLakes = lakeDao.getSelectedLakes()

    fun getCountry(id:Int) = countryDao.getCountry(id)
    fun getLake(id:Int) = lakeDao.getLake(id)

    fun getLakesForCountry(countryId:Int) = lakeDao.getLakesForCountry(countryId)

    suspend fun clearSelectedLakesInDatabase() {
        lakeDao.clearSelectedLakes()
    }

    suspend fun insertLake(lake: Lake){
        lakeDao.insert(lake)
    }
    suspend fun insertCountry(country: Country){
       countryDao.insert(country)
    }
    suspend fun deleteLake(lake:Lake){
        lakeDao.delete(lake)
    }
    suspend fun deleteCountry(country: Country){
        countryDao.delete(country)
    }
    suspend fun updateLake(lake:Lake){
        lakeDao.update(lake)
    }
    suspend fun updateCountry(country: Country){
        countryDao.update(country)
    }
}
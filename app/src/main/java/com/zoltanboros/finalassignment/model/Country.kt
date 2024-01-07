package com.zoltanboros.finalassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
)
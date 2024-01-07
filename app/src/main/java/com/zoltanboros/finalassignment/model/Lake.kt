package com.zoltanboros.finalassignment.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "lakes",foreignKeys = [ForeignKey(entity = Country::class, parentColumns = ["id"], childColumns = ["countryIdFk"])])
data class Lake(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val title: String,
    val desc: String,
    val type: String,
    val rating: Float,
    val selected: Boolean,
    val countryIdFk: Int
)

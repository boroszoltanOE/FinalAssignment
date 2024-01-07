package com.zoltanboros.finalassignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zoltanboros.finalassignment.model.Country
import com.zoltanboros.finalassignment.model.Lake

@Database(
    entities = [Country::class, Lake::class],
    version = 2,
    exportSchema = false
)

abstract class LakesDatabase: RoomDatabase() {
    abstract fun countryDao():CountryDao
    abstract fun lakeDao():LakeDao

    companion object{
        @Volatile
        var INSTANCE:LakesDatabase? = null
        fun getDatabase(context: Context):LakesDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    LakesDatabase::class.java,
                    "lakes_database"
                ).createFromAsset("database/LakesDatabase.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
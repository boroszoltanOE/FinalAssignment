package com.zoltanboros.finalassignment.singleton

import android.content.Context
import com.zoltanboros.finalassignment.data.LakesDatabase
import com.zoltanboros.finalassignment.repository.Repository

object Graph {
    lateinit var db:LakesDatabase
        private set

    val repository by lazy {
        Repository(
            countryDao = db.countryDao(),
            lakeDao = db.lakeDao()
        )
    }

    fun provide(context: Context){
        db = LakesDatabase.getDatabase(context)
    }
}
package com.zoltanboros.finalassignment.singleton

import android.app.Application

class LakeTripsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}
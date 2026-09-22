package com.hkw.roomfit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RoomFitApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
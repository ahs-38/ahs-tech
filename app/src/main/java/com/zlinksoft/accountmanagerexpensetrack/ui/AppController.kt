package com.zlinksoft.accountmanagerexpensetrack.ui

import android.app.Application
import com.zlinksoft.accountmanagerexpensetrack.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppController : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
    }
}
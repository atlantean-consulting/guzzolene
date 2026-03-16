package com.guzzoline.app

import android.app.Application
import androidx.room.Room
import com.guzzoline.app.data.AppDatabase

class GuzzolineApp : Application() {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "guzzoline.db"
        ).build()
    }
}

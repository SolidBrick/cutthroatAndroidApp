package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.db.AppDatabase

class DBInitializer : Application() {
    companion object {
        lateinit var APPDATABASE : AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        APPDATABASE = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        )
        .fallbackToDestructiveMigration(true)
        .build()
    }


}
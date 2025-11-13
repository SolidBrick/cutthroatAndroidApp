package com.example.myapplication.db
// test
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Player::class, Rack::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "APP_DB"
    }
    abstract fun getPlayerDao(): PlayerDao
    abstract fun getRackDao(): RackDao
}
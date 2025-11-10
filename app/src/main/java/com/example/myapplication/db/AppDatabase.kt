package com.example.myapplication.db
// test
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "PLAYER_DB"
    }
    abstract fun getPlayerDao(): PlayerDao
    abstract fun getRackDao(): RackDao
}
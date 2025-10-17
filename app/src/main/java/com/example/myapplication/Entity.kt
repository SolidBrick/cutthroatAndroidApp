package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate=true) val id: Int = 0,
    val playerName: String
)

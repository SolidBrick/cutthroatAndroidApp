package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey
    val playerName: String,
    var gamesPlayed: Int = 0,
    var wins: Int = 0,
    var ballsSunk: Int = 0,
    var totalRemainingBalls: Int = 0,
    var wipes: Int = 0,

    )

package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "racks",
    foreignKeys = [
        ForeignKey(
            entity = Player::class,
            parentColumns = arrayOf("playerName"),
            childColumns = arrayOf("playerOne", "playerTwo",  "PlayerThree", "winner"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Rack(
    @PrimaryKey(autoGenerate = true)
    val rackID: Int = 0,
    var playerOne: Player,
    var playerTwo: Player,
    var playerThree: Player,
    var winner: Player,
    var playerOneRemaining: Int = 5,
    var playerTwoRemaining: Int = 5,
    var playerThreeRemaining: Int = 5,
)
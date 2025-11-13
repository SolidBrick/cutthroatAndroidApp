package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "racks",
    foreignKeys = [
        ForeignKey(
            entity = Player::class,
            parentColumns = ["playerName"],
            childColumns = ["playerOneName"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Player::class,
            parentColumns = ["playerName"],
            childColumns = ["playerTwoName"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Player::class,
            parentColumns = ["playerName"],
            childColumns = ["playerThreeName"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Player::class,
            parentColumns = ["playerName"],
            childColumns = ["winnerName"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Rack(
    @PrimaryKey(autoGenerate = true)
    val rackID: Int = 0,
    var playerOneName: String,
    var playerTwoName: String,
    var playerThreeName: String,
    var winnerName: String,
    var playerOneRemaining: Int = 5,
    var playerTwoRemaining: Int = 5,
    var playerThreeRemaining: Int = 5,
)
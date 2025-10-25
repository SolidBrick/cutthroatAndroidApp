package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM players")
    fun getAllPlayers(): Flow<List<Player>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlayer(player: Player)

    @Query("DELETE FROM players WHERE playerName = :name")
    suspend fun deletePlayer(name: String)

    @Query("UPDATE players " +
            "SET totalRemainingBalls = totalRemainingBalls + :ballsRemaining, ballsSunk = ballsSunk + (5 - :ballsRemaining)" +
            "WHERE playerName = :name")
    suspend fun addBallsRemainingAndSunk(name: String, ballsRemaining: Int)

    @Query("UPDATE players SET wins = wins + 1 WHERE playerName = :name")
    suspend fun addWin(name: String)

    @Query("UPDATE players SET gamesPlayed = gamesPlayed + 1 WHERE playerName = :name")
    suspend fun addGamesPlayed(name: String)
}
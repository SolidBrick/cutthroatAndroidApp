package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.myapplication.db.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlayerViewModel() : ViewModel() {
    val playerDao = DBInitializer.APPDATABASE.getPlayerDao()
    // Add matches dao after

    // Live data for the UI
    val allPlayers: Flow<List<Player>> = playerDao.getAllPlayers()

    fun addPlayer(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playerDao.addPlayer(Player(playerName = name))
        }
    }

    fun deletePlayer(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playerDao.deletePlayer(name)
        }

    }

    fun addBallsRemainingAndSunk(name: String, ballsRemaining: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            playerDao.addBallsRemainingAndSunk(name, ballsRemaining)
        }

    }

    fun addWin(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playerDao.addWin(name)
        }

    }
    fun addGamesPlayed(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playerDao.addGamesPlayed(name)
        }

    }
}
package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.Player
import com.example.myapplication.db.Rack
import com.example.myapplication.db.RackDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RackViewModel : ViewModel() {
    val rackDao = DBInitializer.APPDATABASE.getRackDao()

    val allRacks: Flow<List<Rack>> = rackDao.getAllRacks()

    fun addRack(playerOneName: String,
                playerTwoName: String,
                playerThreeName: String,
                winnerName: String,
                playerOneRemaining: Int,
                playerTwoRemaining: Int,
                playerThreeRemaining: Int,
        ) {
        viewModelScope.launch(Dispatchers.IO) {
            rackDao.addRack(Rack(
                playerOneName = playerOneName,
                playerTwoName = playerTwoName,
                playerThreeName = playerThreeName,
                winnerName = winnerName,
                playerOneRemaining = playerOneRemaining,
                playerTwoRemaining = playerTwoRemaining,
                playerThreeRemaining = playerThreeRemaining,
            ))
        }
    }
}
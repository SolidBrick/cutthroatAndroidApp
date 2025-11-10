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

    fun addRack(playerOne: Player,
                playerTwo: Player,
                playerThree: Player,
                winner: Player,
                playerOneRemaining: Int,
                playerTwoRemaining: Int,
                playerThreeRemaining: Int,
        ) {
        viewModelScope.launch(Dispatchers.IO) {
            rackDao.addRack(Rack(
                playerOne = playerOne,
                playerTwo = playerTwo,
                playerThree = playerThree,
                winner = winner,
                playerOneRemaining = playerOneRemaining,
                playerTwoRemaining = playerTwoRemaining,
                playerThreeRemaining = playerThreeRemaining,
            ))
        }
    }
}
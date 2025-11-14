package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentPlayerDetailsBinding
import com.example.myapplication.db.Player
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class PlayerDetails(private val playerName: String) : BottomSheetDialogFragment() {

    private val playerViewModel: PlayerViewModel by viewModels()
    private var _binding: FragmentPlayerDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            super.onViewCreated(view, savedInstanceState)
            val player = playerViewModel.getPlayer(playerName)
            val wins = player.wins
            val games = player.gamesPlayed
            val ballsSunk = player.ballsSunk
            val ballsRemaining = player.totalRemainingBalls
            var avgRemaining: Double = 5.0
            var winrate: Double = 0.00

            if (games != 0) {
                winrate = (wins.toDouble() / games.toDouble() * 100)
                avgRemaining = ballsRemaining.toDouble() / games.toDouble()
            }

            println(winrate)

            binding.playerName.text = playerName
            binding.playerWins.text = "Wins: " + wins.toString()
            binding.playerWinrate.text = "Winrate: " + "%.3f".format(winrate) + "%"
            binding.playerGamesPlayed.text = "Games played: " + games.toString()
            binding.playerBallsSunk.text = "Total balls sunk: " + ballsSunk.toString()
            binding.playerBallsRemaining.text = "Total balls remaining: " + ballsRemaining.toString()
            binding.playerAvgBallsRemaining.text = "Average balls remaining: " + "%.3f".format(avgRemaining)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
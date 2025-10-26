package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentPlayerDetailsBinding
import com.example.myapplication.db.Player
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerDetails(private val player: Player) : BottomSheetDialogFragment() {

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
        super.onViewCreated(view, savedInstanceState)
        val wins = player.wins
        val games = player.gamesPlayed
        val ballsSunk = player.ballsSunk
        val ballsRemaining = player.totalRemainingBalls
        var avgRemaining = 5
        var winrate: Double = 0.00

        if (games != 0) {
            winrate = (wins.toDouble() / games.toDouble() * 100).toDouble()
            avgRemaining = ballsRemaining / games
        }

        println(winrate)

        binding.playerName.text = player.playerName
        binding.playerWins.text = "Wins: " + wins.toString()
        binding.playerWinrate.text = "Winrate: " + winrate.toString() + "%"
        binding.playerGamesPlayed.text = "Games played: " + games.toString()
        binding.playerBallsSunk.text = "Total balls sunk: " + ballsSunk.toString()
        binding.playerBallsRemaining.text = "Total balls remaining: " + ballsRemaining.toString()
        binding.playerAvgBallsRemaining.text = "Average balls remaining: " + avgRemaining.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
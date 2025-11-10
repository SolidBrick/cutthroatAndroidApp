package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.db.Rack

class MatchHistoryAdapter : RecyclerView.Adapter<MatchHistoryAdapter.RackViewHolder>() {

    private var racks: List<Rack> = emptyList()

    class RackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerNameText: TextView = itemView.findViewById(R.id.playerName)
        val playerBallsSunkText: TextView = itemView.findViewById(R.id.playerBallsSunk)
        val playerWinsText: TextView = itemView.findViewById(R.id.playerWins)
        val playerGamesPlayed: TextView = itemView.findViewById(R.id.playerGamesPlayed)
    }

}
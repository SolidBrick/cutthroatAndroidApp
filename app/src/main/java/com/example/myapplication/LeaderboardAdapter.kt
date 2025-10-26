package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.db.Player

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.PlayerViewHolder>() {

    private var players: List<Player> = emptyList()

    fun setPlayers(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerNameText: TextView = itemView.findViewById(R.id.playerName)
        val playerBallsSunkText: TextView = itemView.findViewById(R.id.playerBallsSunk)
        val playerWinsText: TextView = itemView.findViewById(R.id.playerWins)
        val playerGamesPlayed: TextView = itemView.findViewById(R.id.playerGamesPlayed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item_layout, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.playerNameText.text = player.playerName
        holder.playerWinsText.text = player.wins.toString()
        holder.playerGamesPlayed.text = player.gamesPlayed.toString()
        holder.playerBallsSunkText.text = player.ballsSunk.toString()

        holder.playerNameText.setOnClickListener {
            val playerDetails = PlayerDetails(player)
            playerDetails.show(
                (holder.itemView.context as AppCompatActivity).supportFragmentManager,
                "PlayerDetails"
            )
        }
    }

    override fun getItemCount(): Int = players.size

    fun sortByBallsSunk(reverse: Boolean) {
        players = if (reverse) {
            players.sortedBy { it.ballsSunk }
        } else {
            players.sortedByDescending { it.ballsSunk }
        }
        notifyDataSetChanged()
    }

    fun sortByWins(reverse: Boolean) {
        players = if (reverse) {
            players.sortedBy { it.wins }
        } else {
            players.sortedByDescending { it.wins }
        }
        notifyDataSetChanged()
    }

    fun sortByGamesPlayed(reverse: Boolean) {
        players = if (reverse) {
            players.sortedBy { it.gamesPlayed }
        } else {
            players.sortedByDescending { it.gamesPlayed }
        }
        notifyDataSetChanged()
    }
}
package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.LeaderboardAdapter.PlayerViewHolder
import com.example.myapplication.db.Player
import com.example.myapplication.db.Rack

class MatchHistoryAdapter : RecyclerView.Adapter<MatchHistoryAdapter.RackViewHolder>() {

    private var racks: List<Rack> = emptyList()

    fun setRacks(racks: List<Rack>) {
        this.racks = racks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = racks.size

    class RackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerOneNameText: TextView = itemView.findViewById(R.id.playerOneName)
        val playerTwoNameText: TextView = itemView.findViewById(R.id.playerTwoName)
        val playerThreeNameText: TextView = itemView.findViewById(R.id.playerThreeName)
        val playerOneRemainingText: TextView = itemView.findViewById(R.id.playerOneRemaining)
        val playerTwoRemainingText: TextView = itemView.findViewById(R.id.playerTwoRemaining)
        val playerThreeRemainingText: TextView = itemView.findViewById(R.id.playerThreeRemaining)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rack_item_layout, parent, false)
        return RackViewHolder(view)
    }

    override fun onBindViewHolder(holder: RackViewHolder, position: Int) {
        val rack = racks[position]
        holder.playerOneNameText.text = rack.playerOneName
        holder.playerTwoNameText.text = rack.playerTwoName
        holder.playerThreeNameText.text = rack.playerThreeName
        holder.playerOneRemainingText.text = rack.playerOneRemaining.toString()
        holder.playerTwoRemainingText.text = rack.playerTwoRemaining.toString()
        holder.playerThreeRemainingText.text = rack.playerThreeRemaining.toString()

        holder.playerOneNameText.setOnClickListener {
            val playerDetails = PlayerDetails(rack.playerOneName)
            playerDetails.show(
                (holder.itemView.context as AppCompatActivity).supportFragmentManager,
                "PlayerDetails"
            )
        }

        holder.playerTwoNameText.setOnClickListener {
            val playerDetails = PlayerDetails(rack.playerTwoName)
            playerDetails.show(
                (holder.itemView.context as AppCompatActivity).supportFragmentManager,
                "PlayerDetails"
            )
        }

        holder.playerThreeNameText.setOnClickListener {
            val playerDetails = PlayerDetails(rack.playerThreeName)
            playerDetails.show(
                (holder.itemView.context as AppCompatActivity).supportFragmentManager,
                "PlayerDetails"
            )
        }
    }

}
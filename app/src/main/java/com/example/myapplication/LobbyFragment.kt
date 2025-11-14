package com.example.myapplication
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.db.Player
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class LobbyFragment : Fragment() {
    private var activePlayers = 0
    private val playerViewModel: PlayerViewModel by viewModels()
    private val rackViewModel: RackViewModel by viewModels()
    var firstGroupClaimed = false
    var secondGroupClaimed = false
    var thirdGroupClaimed = false
    var claimedState = mutableMapOf<Int, Boolean>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_lobby, container, false)
        val player1Lobby = root.findViewById<View>(R.id.player1LobbyFragment)
        val player2Lobby = root.findViewById<View>(R.id.player2LobbyFragment)
        val player3Lobby = root.findViewById<View>(R.id.player3LobbyFragment)
        player1Lobby.setTag(R.id.totalScoreTag, 0)
        player2Lobby.setTag(R.id.totalScoreTag, 0)
        player3Lobby.setTag(R.id.totalScoreTag, 0)
        player1Lobby.setTag(R.id.curScoreTag, 5)
        player2Lobby.setTag(R.id.curScoreTag, 5)
        player3Lobby.setTag(R.id.curScoreTag, 5)


        setupPlayerCard(
            addView = root.findViewById(R.id.addPlayer1Fragment),
            lobbyView = root.findViewById(R.id.player1LobbyFragment),
            otherLobbyViews = listOf(player2Lobby, player3Lobby)
        )
        setupPlayerCard(
            addView = root.findViewById(R.id.addPlayer2Fragment),
            lobbyView = root.findViewById(R.id.player2LobbyFragment),
            otherLobbyViews = listOf(player1Lobby, player3Lobby)
        )
        setupPlayerCard(
            addView = root.findViewById(R.id.addPlayer3Fragment),
            lobbyView = root.findViewById(R.id.player3LobbyFragment),
            otherLobbyViews = listOf(player1Lobby, player2Lobby)
        )
        return root
    }


    @SuppressLint("SetTextI18n")
    private fun setupPlayerCard(
        addView: View,
        lobbyView: View,
        otherLobbyViews: List<View>
    ) {
        val nameInput = addView.findViewById<EditText>(R.id.nameInput)
        val addButton = addView.findViewById<Button>(R.id.addPlayerBtn)
        val playerName = lobbyView.findViewById<TextView>(R.id.playerName)
        val resetButton = lobbyView.findViewById<FloatingActionButton>(R.id.resetPlayerBtn)
        val firstGrouping = lobbyView.findViewById<Chip>(R.id.firstGrouping)
        val secondGrouping = lobbyView.findViewById<Chip>(R.id.secondGrouping)
        val thirdGrouping = lobbyView.findViewById<Chip>(R.id.thirdGrouping)
        val incrScoreBtn = lobbyView.findViewById<TextView>(R.id.addScore)
        val decrScoreBtn = lobbyView.findViewById<TextView>(R.id.subtractScore)
        val scoreCount = lobbyView.findViewById<TextView>(R.id.curScore)
        val claimWinBtn = lobbyView.findViewById<Button>(R.id.claimWin)
        val totalWinsCount = lobbyView.findViewById<TextView>(R.id.numWinsNum)

        var totalWins = 0

        claimedState[lobbyView.id] = false

        addButton.setOnClickListener {
            val text = nameInput.text.toString().trim()
            if (text.isBlank()) {
                Toast.makeText(requireContext(), "Name cannot be blank", Toast.LENGTH_SHORT).show()
            } else {
                println(text)
                playerName.text = text
                lobbyView.visibility = View.VISIBLE
                addView.visibility = View.GONE
                activePlayers++
                playerViewModel.addPlayer(text)
            }
        }

        resetButton.setOnClickListener {
            val playerName = lobbyView.findViewById<TextView>(R.id.playerName)
            val nameInput = addView.findViewById<EditText>(R.id.nameInput)
            val totalScoreCount = lobbyView.findViewById<TextView>(R.id.totalScoreNum)

            nameInput.setText(playerName.text)
            lobbyView.visibility = View.GONE
            addView.visibility = View.VISIBLE
            resetScoresAndGroupings(otherLobbyViews + lobbyView)

            lobbyView.setTag(R.id.totalScoreTag, 0)
            totalScoreCount.text = lobbyView.getTag(R.id.totalScoreTag).toString()

            totalWins = 0
            totalWinsCount.text = totalWins.toString()


        }

        firstGrouping.setOnClickListener {
            if (firstGroupClaimed) { // Re-enable other groups
                firstGrouping.setAlpha(1f)
                firstGrouping.chipStrokeWidth = 0f
                if (!secondGroupClaimed) {
                    secondGrouping.setAlpha(1f)
                    secondGrouping.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping.setAlpha(1f)
                    thirdGrouping.setClickable(true)
                }
            } else {
                firstGrouping.chipStrokeWidth = 4f
                secondGrouping.setAlpha(.3f)
                secondGrouping.setClickable(false)
                thirdGrouping.setAlpha(.3f)
                thirdGrouping.setClickable(false)
            }
            firstGroupClaimed = !firstGroupClaimed
            claimedState[lobbyView.id] = !claimedState[lobbyView.id]!!
            restrictFirstGrouping(otherLobbyViews, firstGroupClaimed)
        }

        secondGrouping.setOnClickListener {
            if (secondGroupClaimed) { // Re-enable other groups
                secondGrouping.setAlpha(1f)
                secondGrouping.chipStrokeWidth = 0f
                if (!firstGroupClaimed) {
                    firstGrouping.setAlpha(1f)
                    firstGrouping.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping.setAlpha(1f)
                    thirdGrouping.setClickable(true)
                }
            } else {
                secondGrouping.chipStrokeWidth = 4f
                firstGrouping.setAlpha(.3f)
                firstGrouping.setClickable(false)
                thirdGrouping.setAlpha(.3f)
                thirdGrouping.setClickable(false)
            }
            secondGroupClaimed = !secondGroupClaimed
            claimedState[lobbyView.id] = !claimedState[lobbyView.id]!!
            restrictSecondGrouping(otherLobbyViews, secondGroupClaimed)
        }

        thirdGrouping.setOnClickListener {
            if (thirdGroupClaimed) { // Re-enable other groups
                thirdGrouping.setAlpha(1f)
                thirdGrouping.chipStrokeWidth = 0f
                if (!firstGroupClaimed) {
                    firstGrouping.setAlpha(1f)
                    firstGrouping.setClickable(true)
                }
                if (!secondGroupClaimed) {
                    secondGrouping.setAlpha(1f)
                    secondGrouping.setClickable(true)
                }
            } else {
                thirdGrouping.chipStrokeWidth = 4f
                firstGrouping.setAlpha(.3f)
                firstGrouping.setClickable(false)
                secondGrouping.setAlpha(.3f)
                secondGrouping.setClickable(false)
            }
            thirdGroupClaimed = !thirdGroupClaimed
            claimedState[lobbyView.id] = !claimedState[lobbyView.id]!!
            restrictThirdGrouping(otherLobbyViews, thirdGroupClaimed)

        }

        incrScoreBtn.setOnClickListener {
            var score = lobbyView.getTag(R.id.curScoreTag) as Int
            if (score < 5) {
                score += 1
            }
            scoreCount.text = score.toString()
            claimWinBtn.setClickable(false)
            claimWinBtn.setAlpha(0.3f)
            lobbyView.setTag(R.id.curScoreTag, score)
        }

        decrScoreBtn.setOnClickListener {
            var score = lobbyView.getTag(R.id.curScoreTag) as Int
            if (score > 0) {
                score -= 1
                scoreCount.text = score.toString()
                if (score == 0) {
                    claimWinBtn.setClickable(true)
                    claimWinBtn.setAlpha(1f)
                }
            }
            lobbyView.setTag(R.id.curScoreTag, score)
        }

        claimWinBtn.setOnClickListener {
            totalWins += 1
            totalWinsCount.text = totalWins.toString()
            lifecycleScope.launch {
                endFrame(listOf(lobbyView) + otherLobbyViews)
            }

        }
    }

    private fun restrictFirstGrouping(otherLobbyViews: List<View>, otherPlayerClaimed: Boolean) {
        otherLobbyViews.forEach { lobbyView ->
            val firstGrouping = lobbyView.findViewById<Chip>(R.id.firstGrouping)
            if (otherPlayerClaimed) {
                firstGrouping.setClickable(false)
                firstGrouping.setAlpha(.3f)
            } else if (claimedState[lobbyView.id] == false) {
                firstGrouping.setClickable(true)
                firstGrouping.setAlpha(1f)
            }
        }

    }

    private fun restrictSecondGrouping(otherLobbyViews: List<View>, otherPlayerClaimed: Boolean) {
        otherLobbyViews.forEach { lobbyView ->
            val secondGrouping = lobbyView.findViewById<Chip>(R.id.secondGrouping)
            if (otherPlayerClaimed) {
                secondGrouping.setClickable(false)
                secondGrouping.setAlpha(.3f)
            } else if (claimedState[lobbyView.id] == false) {
                secondGrouping.setClickable(true)
                secondGrouping.setAlpha(1f)
            }
        }

    }

    private fun restrictThirdGrouping(otherLobbyViews: List<View>, otherPlayerClaimed: Boolean) {
        otherLobbyViews.forEach { lobbyView ->
            val thirdGrouping = lobbyView.findViewById<Chip>(R.id.thirdGrouping)
            if (otherPlayerClaimed) {
                thirdGrouping.setClickable(false)
                thirdGrouping.setAlpha(.3f)
            } else if (claimedState[lobbyView.id] == false) {
                thirdGrouping.setClickable(true)
                thirdGrouping.setAlpha(1f)
            }
        }

    }

    suspend private fun endFrame(lobbyViews: List<View>) {
        val players = Array<String>(3) { "" }
        val remaining = Array<Int>(3) { 5 }
        var winner: String = ""

        for (i in 0 until 3) {
            val lobbyView = lobbyViews[i]
            val scoreCount = lobbyView.findViewById<TextView>(R.id.curScore).text.toString().toInt()
            val totalScoreCount = lobbyView.findViewById<TextView>(R.id.totalScoreNum)
            val playerName = lobbyView.findViewById<TextView>(R.id.playerName).text.toString()
            val player: Player = playerViewModel.getPlayer(playerName)

            if (scoreCount == 0) {
                playerViewModel.addWin(playerName)
                winner = playerName
            }

            players[i] = playerName
            remaining[i] = scoreCount
            playerViewModel.addGamesPlayed(playerName)
            playerViewModel.addBallsRemainingAndSunk(playerName, scoreCount)

            lobbyView.setTag(
                R.id.totalScoreTag,
                lobbyView.getTag(R.id.totalScoreTag) as Int + scoreCount
            )

            totalScoreCount.text = lobbyView.getTag(R.id.totalScoreTag).toString()
        }
        rackViewModel.addRack(
            playerOneName = players[0],
            playerTwoName = players[1],
            playerThreeName = players[2],
            winnerName = winner,
            playerOneRemaining = remaining[0],
            playerTwoRemaining = remaining[1],
            playerThreeRemaining = remaining[2],
        )
        resetScoresAndGroupings(lobbyViews)
    }

    private fun resetScoresAndGroupings(lobbyViews: List<View>) {
        lobbyViews.forEach { lobbyView ->
            val firstGrouping = lobbyView.findViewById<Chip>(R.id.firstGrouping)
            val secondGrouping = lobbyView.findViewById<Chip>(R.id.secondGrouping)
            val thirdGrouping = lobbyView.findViewById<Chip>(R.id.thirdGrouping)
            val scoreCount = lobbyView.findViewById<TextView>(R.id.curScore)
            val claimWinBtn = lobbyView.findViewById<Button>(R.id.claimWin)

            lobbyView.setTag(R.id.curScoreTag, 5)
            scoreCount.text = "5"
            claimWinBtn.setClickable(false)
            claimWinBtn.setAlpha(0.3f)

            firstGrouping.setClickable(true)
            secondGrouping.setClickable(true)
            thirdGrouping.setClickable(true)

            firstGrouping.setAlpha(1f)
            secondGrouping.setAlpha(1f)
            thirdGrouping.setAlpha(1f)

            firstGrouping.chipStrokeWidth = 0f
            secondGrouping.chipStrokeWidth = 0f
            thirdGrouping.chipStrokeWidth = 0f
        }
    }

    fun debug() {
        println("debug")
    }
}
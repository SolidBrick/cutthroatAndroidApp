package com.example.myapplication
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LobbyFragment : Fragment() {
    private var activePlayers = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_lobby, container, false)
        val player1Lobby = root.findViewById<View>(R.id.player1LobbyFragment)
        val player2Lobby = root.findViewById<View>(R.id.player2LobbyFragment)
        val player3Lobby = root.findViewById<View>(R.id.player3LobbyFragment)

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

//        resetPlayer(
//            addView = root.findViewById(R.id.addPlayer1Fragment),
//            lobbyView = root.findViewById(R.id.player1LobbyFragment)
//        )
//        resetPlayer(
//            addView = root.findViewById(R.id.addPlayer2Fragment),
//            lobbyView = root.findViewById(R.id.player2LobbyFragment)
//        )
//        resetPlayer(
//            addView = root.findViewById(R.id.addPlayer3Fragment),
//            lobbyView = root.findViewById(R.id.player3LobbyFragment)
//        )


        return root
    }


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

        var score = 5
        var totalScore = 0
        var totalWins = 0
        var firstGroupClaimed = false
        var secondGroupClaimed = false
        var thirdGroupClaimed = false
        var claimed = false

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
            }
        }

        resetButton.setOnClickListener {
            val playerName = lobbyView.findViewById<TextView>(R.id.playerName)
            val nameInput = addView.findViewById<EditText>(R.id.nameInput)
            nameInput.setText(playerName.text)
            lobbyView.visibility = View.GONE
            addView.visibility = View.VISIBLE
//            resetScoresAndGroupings()
            val totalScoreText = lobbyView.findViewById<TextView>(R.id.totalScoreNum)
            totalScoreText.text = totalScore.toString()
            val winCount = lobbyView.findViewById<TextView>(R.id.numWinsNum)
            winCount.text = totalWins.toString()
            activePlayers--
        }

        firstGrouping.setOnClickListener {
            firstGrouping.chipStrokeWidth = 4f
            secondGrouping.setAlpha(.3f)
            secondGrouping.setClickable(false)
            thirdGrouping.setAlpha(.3f)
            thirdGrouping.setClickable(false)
            restrictFirstGrouping(otherLobbyViews)
            firstGroupClaimed = firstGroupClaimed
        }

        secondGrouping.setOnClickListener {
            secondGrouping.chipStrokeWidth = 4f
            firstGrouping.setAlpha(.3f)
            firstGrouping.setClickable(false)
            thirdGrouping.setAlpha(.3f)
            thirdGrouping.setClickable(false)
            restrictSecondGrouping(otherLobbyViews)
            secondGroupClaimed = secondGroupClaimed
        }

        thirdGrouping.setOnClickListener {
            thirdGrouping.chipStrokeWidth = 4f
            firstGrouping.setAlpha(.3f)
            firstGrouping.setClickable(false)
            secondGrouping.setAlpha(.3f)
            secondGrouping.setClickable(false)
            restrictThirdGrouping(otherLobbyViews)
            thirdGroupClaimed = !thirdGroupClaimed
        }
    }

    private fun restrictFirstGrouping(otherLobbyViews: List<View>) {
        otherLobbyViews.forEach { lobbyView ->
            val firstGrouping = lobbyView.findViewById<Chip>(R.id.firstGrouping)
            firstGrouping.setClickable(false)
            firstGrouping.setAlpha(.3f)
        }

    }
    private fun restrictSecondGrouping(otherLobbyViews: List<View>) {
        otherLobbyViews.forEach { lobbyView ->
            val secondGrouping = lobbyView.findViewById<Chip>(R.id.secondGrouping)
            secondGrouping.setClickable(false)
            secondGrouping.setAlpha(.3f)
        }

    }
    private fun restrictThirdGrouping(otherLobbyViews: List<View>) {
        otherLobbyViews.forEach { lobbyView ->
            val thirdGrouping = lobbyView.findViewById<Chip>(R.id.thirdGrouping)
            thirdGrouping.setClickable(false)
            thirdGrouping.setAlpha(.3f)
        }

    }


    fun debug() {
        println("debug")
    }

//    fun resetPlayer(addView: View, lobbyView: View) {
//        pass
//    }



//    fun resetScoresAndGroupings() {
//        score1 = 5
//        score2 = 5
//        score3 = 5
//
//        val scoreCount1 = findViewById<TextView>(R.id.p1Score)
//        scoreCount1.text = score1.toString()
//        val scoreCount2 = findViewById<TextView>(R.id.p2Score)
//        scoreCount2.text = score2.toString()
//        val scoreCount3 = findViewById<TextView>(R.id.p3Score)
//        scoreCount3.text = score3.toString()
//
//        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
//        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
//        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
//        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
//        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
//        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
//        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
//        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
//        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
//
//        firstGrouping1.setClickable(true)
//        firstGrouping2.setClickable(true)
//        firstGrouping3.setClickable(true)
//        secondGrouping1.setClickable(true)
//        secondGrouping2.setClickable(true)
//        secondGrouping3.setClickable(true)
//        thirdGrouping1.setClickable(true)
//        thirdGrouping2.setClickable(true)
//        thirdGrouping3.setClickable(true)
//
//        firstGrouping1.setAlpha(1f)
//        firstGrouping2.setAlpha(1f)
//        firstGrouping3.setAlpha(1f)
//        secondGrouping1.setAlpha(1f)
//        secondGrouping2.setAlpha(1f)
//        secondGrouping3.setAlpha(1f)
//        thirdGrouping1.setAlpha(1f)
//        thirdGrouping2.setAlpha(1f)
//        thirdGrouping3.setAlpha(1f)
//
//        firstGrouping1.chipStrokeWidth = 0f
//        firstGrouping2.chipStrokeWidth = 0f
//        firstGrouping3.chipStrokeWidth = 0f
//        secondGrouping1.chipStrokeWidth = 0f
//        secondGrouping2.chipStrokeWidth = 0f
//        secondGrouping3.chipStrokeWidth = 0f
//        thirdGrouping1.chipStrokeWidth = 0f
//        thirdGrouping2.chipStrokeWidth = 0f
//        thirdGrouping3.chipStrokeWidth = 0f
//
//        firstGroupClaimed = false
//        secondGroupClaimed = false
//        thirdGroupClaimed = false
//        p1Claimed = false
//        p2Claimed = false
//        p3Claimed = false
//    }
//
//    fun gameOver() {
//        totalScore1 += score1
//        totalScore2 += score2
//        totalScore3 += score3
//
//        val totalScore1Text = findViewById<TextView>(R.id.p1TotalScore)
//        totalScore1Text.text = totalScore1.toString()
//        val totalScore2Text = findViewById<TextView>(R.id.p2TotalScore)
//        totalScore2Text.text = totalScore2.toString()
//        val totalScore3Text = findViewById<TextView>(R.id.p3TotalScore)
//        totalScore3Text.text = totalScore3.toString()
//
//
//        resetScoresAndGroupings()
//    }
//
//    fun resetConfirm(player: Int) {
//        if (player != 1 && score1 <= 0) {
//            score1 = 1
//            val scoreCount = findViewById<TextView>(R.id.p1Score)
//            scoreCount.text = score1.toString()
//        }
//        if (player != 2 && score2 <= 0) {
//            score2 = 1
//            val scoreCount = findViewById<TextView>(R.id.p2Score)
//            scoreCount.text = score2.toString()
//        }
//        if (player != 3 && score3 <= 0) {
//            score3 = 1
//            val scoreCount = findViewById<TextView>(R.id.p3Score)
//            scoreCount.text = score3.toString()
//        }
//    }
//
//    fun incScore1(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        if (score1 < 5) {
//            score1 += 1
//            resetConfirm(0)
//            val scoreCount = findViewById<TextView>(R.id.p1Score)
//            scoreCount.text = score1.toString()
//        }
//    }
//
//    fun decrScore1(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        resetConfirm(1)
//        score1 -= 1
//        if (score1 >= 0) {
//
//            val scoreCount = findViewById<TextView>(R.id.p1Score)
//            scoreCount.text = score1.toString()
//            if (score1 == 0) {
//                println(p1Name + " wins!")
//                Toast.makeText(this, "Double tap \"-\" to confirm win", Toast.LENGTH_SHORT).show()
//            }
//        } else if (score1 <= -2) {
//            Toast.makeText(this, p1Name + " wins!", Toast.LENGTH_LONG).show()
//            totalWins1++;
//            val winCount = findViewById<TextView>(R.id.p1Wins)
//            winCount.text = totalWins1.toString()
//            score1 = 0
//            gameOver()
//        }
//    }
//
//    fun incScore2(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        if (score2 < 5) {
//            score2 += 1
//            resetConfirm(0)
//            val scoreCount = findViewById<TextView>(R.id.p2Score)
//            scoreCount.text = score2.toString()
//        }
//    }
//
//    fun decrScore2(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        resetConfirm(2)
//        score2 -= 1
//        if (score2 >= 0) {
//
//            val scoreCount = findViewById<TextView>(R.id.p2Score)
//            scoreCount.text = score2.toString()
//            if (score2 == 0) {
//                println(p2Name + " wins!")
//                Toast.makeText(this, "Double tap \"-\" to confirm win", Toast.LENGTH_SHORT).show()
//            }
//        } else if (score2 <= -2) {
//            Toast.makeText(this, p2Name + " wins!", Toast.LENGTH_LONG).show()
//            totalWins2++;
//            val winCount = findViewById<TextView>(R.id.p2Wins)
//            winCount.text = totalWins2.toString()
//            score2 = 0
//            gameOver()
//        }
//    }
//
//    fun incScore3(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        if (score3 < 5) {
//            score3 += 1
//            resetConfirm(0)
//            val scoreCount = findViewById<TextView>(R.id.p3Score)
//            scoreCount.text = score3.toString()
//        }
//    }
//
//    fun decrScore3(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        resetConfirm(3)
//        score3 -= 1
//        if (score3 >= 0) {
//
//            val scoreCount = findViewById<TextView>(R.id.p3Score)
//            scoreCount.text = score3.toString()
//            if (score3 == 0) {
//                println(p3Name + " wins!")
//                Toast.makeText(this, "Double tap \"-\" to confirm win", Toast.LENGTH_SHORT).show()
//            }
//        } else if (score3 <= -2) {
//            Toast.makeText(this, p3Name + " wins!", Toast.LENGTH_LONG).show()
//            totalWins3++;
//            val winCount = findViewById<TextView>(R.id.p3Wins)
//            winCount.text = totalWins3.toString()
//            score3 = 0
//            gameOver()
//        }
//    }
//
//    fun toggleFirstGroup(player: Int) {
//        firstGroupClaimed = !firstGroupClaimed
//        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
//        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
//        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
//        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
//        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
//        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
//        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
//        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
//        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
//        if (firstGroupClaimed) {
//            if (player != 1) {
//                firstGrouping1.setClickable(false)
//                firstGrouping1.setAlpha(.3f)
//            } else {
//                firstGrouping1.chipStrokeWidth = 4f
//                secondGrouping1.setAlpha(.3f)
//                secondGrouping1.setClickable(false)
//                thirdGrouping1.setAlpha(.3f)
//                thirdGrouping1.setClickable(false)
//            }
//            if (player != 2) {
//                firstGrouping2.setClickable(false)
//                firstGrouping2.setAlpha(.3f)
//            } else {
//                firstGrouping2.chipStrokeWidth = 4f
//                secondGrouping2.setAlpha(.3f)
//                secondGrouping2.setClickable(false)
//                thirdGrouping2.setAlpha(.3f)
//                thirdGrouping2.setClickable(false)
//            }
//            if (player != 3) {
//                firstGrouping3.setClickable(false)
//                firstGrouping3.setAlpha(.3f)
//            } else {
//                firstGrouping3.chipStrokeWidth = 4f
//                secondGrouping3.setAlpha(.3f)
//                secondGrouping3.setClickable(false)
//                thirdGrouping3.setAlpha(.3f)
//                thirdGrouping3.setClickable(false)
//            }
//        } else {
//            if (player == 1) {
//                firstGrouping1.chipStrokeWidth = 0f
//                if (!secondGroupClaimed) {
//                    secondGrouping1.setAlpha(1f)
//                    secondGrouping1.setClickable(true)
//                }
//                if (!thirdGroupClaimed) {
//                    thirdGrouping1.setAlpha(1f)
//                    thirdGrouping1.setClickable(true)
//                }
//            } else if (!p1Claimed) {
//                firstGrouping1.setAlpha(1f)
//                firstGrouping1.setClickable(true)
//            }
//            if (player == 2) {
//                firstGrouping2.chipStrokeWidth = 0f
//                if (!secondGroupClaimed) {
//                    secondGrouping2.setAlpha(1f)
//                    secondGrouping2.setClickable(true)
//                }
//                if (!thirdGroupClaimed) {
//                    thirdGrouping2.setAlpha(1f)
//                    thirdGrouping2.setClickable(true)
//                }
//            } else if (!p2Claimed) {
//                firstGrouping2.setAlpha(1f)
//                firstGrouping2.setClickable(true)
//            }
//            if (player == 3) {
//                firstGrouping3.chipStrokeWidth = 0f
//                if (!secondGroupClaimed) {
//                    secondGrouping3.setAlpha(1f)
//                    secondGrouping3.setClickable(true)
//                }
//                if (!thirdGroupClaimed) {
//                    thirdGrouping3.setAlpha(1f)
//                    thirdGrouping3.setClickable(true)
//                }
//            } else if (!p3Claimed) {
//                firstGrouping3.setAlpha(1f)
//                firstGrouping3.setClickable(true)
//            }
//        }
//    }
//
//    fun toggleSecondGroup(player: Int) {
//        secondGroupClaimed = !secondGroupClaimed
//        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
//        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
//        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
//        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
//        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
//        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
//        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
//        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
//        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
//        if (secondGroupClaimed) {
//            if (player != 1) {
//                secondGrouping1.setClickable(false)
//                secondGrouping1.setAlpha(.3f)
//            } else {
//                secondGrouping1.chipStrokeWidth = 4f
//                firstGrouping1.setAlpha(.3f)
//                firstGrouping1.setClickable(false)
//                thirdGrouping1.setAlpha(.3f)
//                thirdGrouping1.setClickable(false)
//            }
//            if (player != 2) {
//                secondGrouping2.setClickable(false)
//                secondGrouping2.setAlpha(.3f)
//            } else {
//                secondGrouping2.chipStrokeWidth = 4f
//                firstGrouping2.setAlpha(.3f)
//                firstGrouping2.setClickable(false)
//                thirdGrouping2.setAlpha(.3f)
//                thirdGrouping2.setClickable(false)
//            }
//            if (player != 3) {
//                secondGrouping3.setClickable(false)
//                secondGrouping3.setAlpha(.3f)
//            } else {
//                secondGrouping3.chipStrokeWidth = 4f
//                firstGrouping3.setAlpha(.3f)
//                firstGrouping3.setClickable(false)
//                thirdGrouping3.setAlpha(.3f)
//                thirdGrouping3.setClickable(false)
//            }
//        } else {
//            if (player == 1) {
//                secondGrouping1.chipStrokeWidth = 0f
//                if (!firstGroupClaimed) {
//                    firstGrouping1.setAlpha(1f)
//                    firstGrouping1.setClickable(true)
//                }
//                if (!thirdGroupClaimed) {
//                    thirdGrouping1.setAlpha(1f)
//                    thirdGrouping1.setClickable(true)
//                }
//            } else if (!p1Claimed) {
//                secondGrouping1.setAlpha(1f)
//                secondGrouping1.setClickable(true)
//            }
//            if (player == 2) {
//                secondGrouping2.chipStrokeWidth = 0f
//                if (!firstGroupClaimed) {
//                    firstGrouping2.setAlpha(1f)
//                    firstGrouping2.setClickable(true)
//                }
//                if (!thirdGroupClaimed) {
//                    thirdGrouping2.setAlpha(1f)
//                    thirdGrouping2.setClickable(true)
//                }
//            } else if (!p2Claimed) {
//                secondGrouping2.setAlpha(1f)
//                secondGrouping2.setClickable(true)
//            }
//            if (player == 3) {
//                secondGrouping3.chipStrokeWidth = 0f
//                if (!firstGroupClaimed) {
//                    firstGrouping3.setAlpha(1f)
//                    firstGrouping3.setClickable(true)
//                }
//                if (!thirdGroupClaimed) {
//                    thirdGrouping3.setAlpha(1f)
//                    thirdGrouping3.setClickable(true)
//                }
//            } else if (!p3Claimed) {
//                secondGrouping3.setAlpha(1f)
//                secondGrouping3.setClickable(true)
//            }
//        }
//    }
//
//    fun toggleThirdGroup(player: Int) {
//        thirdGroupClaimed = !thirdGroupClaimed
//        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
//        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
//        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
//        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
//        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
//        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
//        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
//        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
//        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
//        if (thirdGroupClaimed) {
//            if (player != 1) {
//                thirdGrouping1.setClickable(false)
//                thirdGrouping1.setAlpha(.3f)
//            } else {
//                thirdGrouping1.chipStrokeWidth = 4f
//                secondGrouping1.setAlpha(.3f)
//                secondGrouping1.setClickable(false)
//                firstGrouping1.setAlpha(.3f)
//                firstGrouping1.setClickable(false)
//            }
//            if (player != 2) {
//                thirdGrouping2.setClickable(false)
//                thirdGrouping2.setAlpha(.3f)
//            } else {
//                thirdGrouping2.chipStrokeWidth = 4f
//                secondGrouping2.setAlpha(.3f)
//                secondGrouping2.setClickable(false)
//                firstGrouping2.setAlpha(.3f)
//                firstGrouping2.setClickable(false)
//            }
//            if (player != 3) {
//                thirdGrouping3.setClickable(false)
//                thirdGrouping3.setAlpha(.3f)
//            } else {
//                thirdGrouping3.chipStrokeWidth = 4f
//                secondGrouping3.setAlpha(.3f)
//                secondGrouping3.setClickable(false)
//                firstGrouping3.setAlpha(.3f)
//                firstGrouping3.setClickable(false)
//            }
//        } else {
//            if (player == 1) {
//                thirdGrouping1.chipStrokeWidth = 0f
//                if (!secondGroupClaimed) {
//                    secondGrouping1.setAlpha(1f)
//                    secondGrouping1.setClickable(true)
//                }
//                if (!firstGroupClaimed) {
//                    firstGrouping1.setAlpha(1f)
//                    firstGrouping1.setClickable(true)
//                }
//            } else if (!p1Claimed) {
//                thirdGrouping1.setAlpha(1f)
//                thirdGrouping1.setClickable(true)
//            }
//            if (player == 2) {
//                thirdGrouping2.chipStrokeWidth = 0f
//                if (!secondGroupClaimed) {
//                    secondGrouping2.setAlpha(1f)
//                    secondGrouping2.setClickable(true)
//                }
//                if (!firstGroupClaimed) {
//                    firstGrouping2.setAlpha(1f)
//                    firstGrouping2.setClickable(true)
//                }
//            } else if (!p2Claimed) {
//                thirdGrouping2.setAlpha(1f)
//                thirdGrouping2.setClickable(true)
//            }
//            if (player == 3) {
//                thirdGrouping3.chipStrokeWidth = 0f
//                if (!secondGroupClaimed) {
//                    secondGrouping3.setAlpha(1f)
//                    secondGrouping3.setClickable(true)
//                }
//                if (!firstGroupClaimed) {
//                    firstGrouping3.setAlpha(1f)
//                    firstGrouping3.setClickable(true)
//                }
//            } else if (!p3Claimed) {
//                thirdGrouping3.setAlpha(1f)
//                thirdGrouping3.setClickable(true)
//            }
//        }
//    }
//
//    fun claimFirstGroup1(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p1Claimed = !p1Claimed
//        toggleFirstGroup(1)
//    }
//
//    fun claimFirstGroup2(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p2Claimed = !p2Claimed
//        toggleFirstGroup(2)
//    }
//
//    fun claimFirstGroup3(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p3Claimed = !p3Claimed
//        toggleFirstGroup(3)
//    }
//
//    fun claimSecondGroup1(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p1Claimed = !p1Claimed
//        toggleSecondGroup(1)
//    }
//
//    fun claimSecondGroup2(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p2Claimed = !p2Claimed
//        toggleSecondGroup(2)
//    }
//
//    fun claimSecondGroup3(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p3Claimed = !p3Claimed
//        toggleSecondGroup(3)
//    }
//    fun claimThirdGroup1(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p1Claimed = !p1Claimed
//        toggleThirdGroup(1)
//    }
//
//    fun claimThirdGroup2(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p2Claimed = !p2Claimed
//        toggleThirdGroup(2)
//    }
//
//    fun claimThirdGroup3(view: View?) {
//        if (activePlayers != 3) {
//            return
//        }
//        p3Claimed = !p3Claimed
//        toggleThirdGroup(3)
//    }
}
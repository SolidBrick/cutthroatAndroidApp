package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.EditText
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {
    private var score1 = 5
    private var score2 = 5
    private var score3 = 5

    private var totalScore1 = 0
    private var totalScore2 = 0
    private var totalScore3 = 0

    private var totalWins1 = 0
    private var totalWins2 = 0
    private var totalWins3 = 0

    private var p1Name = "Player 1"
    private var p2Name = "Player 2"
    private var p3Name = "Player 3"
    private var activePlayers = 0

    private var firstGroupClaimed = false
    private var secondGroupClaimed = false
    private var thirdGroupClaimed = false
    private var p1Claimed = false
    private var p2Claimed = false
    private var p3Claimed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun debug() {
        println(p1Claimed)
    }
    fun addPlayer1(view: View?) {
        val editText = findViewById<EditText>(R.id.nameInput1)
        val text = editText.text.toString().trim()
        if (text.isNullOrBlank()) {
            Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_SHORT).show()
        } else {
            println(text)
            p1Name = text
            val infoCard = findViewById<CardView>(R.id.playerInfo1)
            val addPlayerCard = findViewById<CardView>(R.id.addPlayer1Card)
            val name: TextView = findViewById(R.id.playerName1)
            name.text = text
            infoCard.visibility = View.VISIBLE
            addPlayerCard.visibility = View.GONE
            activePlayers++
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }

    }

    fun addPlayer2(view: View?) {
        val editText = findViewById<EditText>(R.id.nameInput2)
        val text = editText.text.toString().trim()
        if (text.isNullOrBlank()) {
            Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_SHORT).show()
        } else {
            println(text)
            p2Name = text
            val infoCard = findViewById<CardView>(R.id.playerInfo2)
            val addPlayerCard = findViewById<CardView>(R.id.addPlayer2Card)
            val name: TextView = findViewById(R.id.playerName2)
            name.text = text
            infoCard.visibility = View.VISIBLE
            addPlayerCard.visibility = View.GONE
            activePlayers++
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    fun addPlayer3(view: View?) {
        val editText = findViewById<TextView>(R.id.nameInput3)
        val text = editText.text.toString().trim()
        if (text.isNullOrBlank()) {
            Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_SHORT).show()
        } else {
            println(text)
            p3Name = text
            val infoCard = findViewById<CardView>(R.id.playerInfo3)
            val addPlayerCard = findViewById<CardView>(R.id.addPlayer3Card)
            val name: TextView = findViewById(R.id.playerName3)
            name.text = text
            infoCard.visibility = View.VISIBLE
            addPlayerCard.visibility = View.GONE
            activePlayers++
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    fun resetPlayer1(view: View?) {
        val infoCard = findViewById<CardView>(R.id.playerInfo1)
        val addPlayerCard = findViewById<CardView>(R.id.addPlayer1Card)
        val nameInput = findViewById<EditText>(R.id.nameInput1)
        nameInput.setText(p1Name)
        infoCard.visibility = View.GONE
        addPlayerCard.visibility = View.VISIBLE
        resetScoresAndGroupings()
        activePlayers--
    }

    fun resetPlayer2(view: View?) {
        val infoCard = findViewById<CardView>(R.id.playerInfo2)
        val addPlayerCard = findViewById<CardView>(R.id.addPlayer2Card)
        val nameInput = findViewById<EditText>(R.id.nameInput2)
        nameInput.setText(p2Name)
        infoCard.visibility = View.GONE
        addPlayerCard.visibility = View.VISIBLE
        resetScoresAndGroupings()
        activePlayers--
    }

    fun resetPlayer3(view: View?) {
        val infoCard = findViewById<CardView>(R.id.playerInfo3)
        val addPlayerCard = findViewById<CardView>(R.id.addPlayer3Card)
        val nameInput = findViewById<EditText>(R.id.nameInput3)
        nameInput.setText(p3Name)
        infoCard.visibility = View.GONE
        addPlayerCard.visibility = View.VISIBLE
        resetScoresAndGroupings()
        activePlayers--
    }

    fun resetScoresAndGroupings() {
        score1 = 5
        score2 = 5
        score3 = 5

        val scoreCount1 = findViewById<TextView>(R.id.p1Score)
        scoreCount1.text = score1.toString()
        val scoreCount2 = findViewById<TextView>(R.id.p2Score)
        scoreCount2.text = score2.toString()
        val scoreCount3 = findViewById<TextView>(R.id.p3Score)
        scoreCount3.text = score3.toString()

        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)

        firstGrouping1.setClickable(true)
        firstGrouping2.setClickable(true)
        firstGrouping3.setClickable(true)
        secondGrouping1.setClickable(true)
        secondGrouping2.setClickable(true)
        secondGrouping3.setClickable(true)
        thirdGrouping1.setClickable(true)
        thirdGrouping2.setClickable(true)
        thirdGrouping3.setClickable(true)

        firstGrouping1.setAlpha(1f)
        firstGrouping2.setAlpha(1f)
        firstGrouping3.setAlpha(1f)
        secondGrouping1.setAlpha(1f)
        secondGrouping2.setAlpha(1f)
        secondGrouping3.setAlpha(1f)
        thirdGrouping1.setAlpha(1f)
        thirdGrouping2.setAlpha(1f)
        thirdGrouping3.setAlpha(1f)

        firstGrouping1.chipStrokeWidth = 0f
        firstGrouping2.chipStrokeWidth = 0f
        firstGrouping3.chipStrokeWidth = 0f
        secondGrouping1.chipStrokeWidth = 0f
        secondGrouping2.chipStrokeWidth = 0f
        secondGrouping3.chipStrokeWidth = 0f
        thirdGrouping1.chipStrokeWidth = 0f
        thirdGrouping2.chipStrokeWidth = 0f
        thirdGrouping3.chipStrokeWidth = 0f

        firstGroupClaimed = false
        secondGroupClaimed = false
        thirdGroupClaimed = false
        p1Claimed = false
        p2Claimed = false
        p3Claimed = false
    }

    fun gameOver() {
        totalScore1 += score1
        totalScore2 += score2
        totalScore3 += score3

        val totalScore1Text = findViewById<TextView>(R.id.p1TotalScore)
        totalScore1Text.text = totalScore1.toString()
        val totalScore2Text = findViewById<TextView>(R.id.p2TotalScore)
        totalScore2Text.text = totalScore2.toString()
        val totalScore3Text = findViewById<TextView>(R.id.p3TotalScore)
        totalScore3Text.text = totalScore3.toString()


        resetScoresAndGroupings()
    }

    fun resetConfirm(player: Int) {
        if (player != 1 && score1 <= 0) {
            score1 = 1
            val scoreCount = findViewById<TextView>(R.id.p1Score)
            scoreCount.text = score1.toString()
        }
        if (player != 2 && score2 <= 0) {
            score2 = 1
            val scoreCount = findViewById<TextView>(R.id.p2Score)
            scoreCount.text = score2.toString()
        }
        if (player != 1 && score3 <= 0) {
            score3 = 1
            val scoreCount = findViewById<TextView>(R.id.p3Score)
            scoreCount.text = score3.toString()
        }
    }

    fun incScore1(view: View?) {
        if (activePlayers != 3) {
            return
        }
        if (score1 < 5) {
            score1 += 1
            resetConfirm(0)
            val scoreCount = findViewById<TextView>(R.id.p1Score)
            scoreCount.text = score1.toString()
        }
    }

    fun decrScore1(view: View?) {
        if (activePlayers != 3) {
            return
        }
        resetConfirm(1)
        score1 -= 1
        if (score1 >= 0) {

            val scoreCount = findViewById<TextView>(R.id.p1Score)
            scoreCount.text = score1.toString()
            if (score1 == 0) {
                println(p1Name + " wins!")
                Toast.makeText(this, "Double tap \"-\" to confirm win", Toast.LENGTH_SHORT).show()
            }
        } else if (score1 <= -2) {
            Toast.makeText(this, p1Name + " wins!", Toast.LENGTH_LONG).show()
            totalWins1++;
            val winCount = findViewById<TextView>(R.id.p1Wins)
            winCount.text = totalWins1.toString()
            score1 = 0
            gameOver()
        }
    }

    fun incScore2(view: View?) {
        if (activePlayers != 3) {
            return
        }
        if (score2 < 5) {
            score2 += 1
            resetConfirm(0)
            val scoreCount = findViewById<TextView>(R.id.p2Score)
            scoreCount.text = score2.toString()
        }
    }

    fun decrScore2(view: View?) {
        if (activePlayers != 3) {
            return
        }
        resetConfirm(2)
        score2 -= 1
        if (score2 >= 0) {

            val scoreCount = findViewById<TextView>(R.id.p2Score)
            scoreCount.text = score2.toString()
            if (score2 == 0) {
                println(p2Name + " wins!")
                Toast.makeText(this, "Double tap \"-\" to confirm win", Toast.LENGTH_SHORT).show()
            }
        } else if (score2 <= -2) {
            Toast.makeText(this, p2Name + " wins!", Toast.LENGTH_LONG).show()
            totalWins2++;
            val winCount = findViewById<TextView>(R.id.p2Wins)
            winCount.text = totalWins2.toString()
            score2 = 0
            gameOver()
        }
    }

    fun incScore3(view: View?) {
        if (activePlayers != 3) {
            return
        }
        if (score3 < 5) {
            score3 += 1
            resetConfirm(0)
            val scoreCount = findViewById<TextView>(R.id.p3Score)
            scoreCount.text = score3.toString()
        }
    }

    fun decrScore3(view: View?) {
        if (activePlayers != 3) {
            return
        }
        resetConfirm(3)
        score3 -= 1
        if (score3 >= 0) {

            val scoreCount = findViewById<TextView>(R.id.p3Score)
            scoreCount.text = score3.toString()
            if (score3 == 0) {
                println(p3Name + " wins!")
                Toast.makeText(this, "Double tap \"-\" to confirm win", Toast.LENGTH_SHORT).show()
            }
        } else if (score3 <= -2) {
            Toast.makeText(this, p3Name + " wins!", Toast.LENGTH_LONG).show()
            totalWins3++;
            val winCount = findViewById<TextView>(R.id.p3Wins)
            winCount.text = totalWins3.toString()
            score3 = 0
            gameOver()
        }
    }

    fun toggleFirstGroup(player: Int) {
        firstGroupClaimed = !firstGroupClaimed
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        if (firstGroupClaimed) {
            if (player != 1) {
                firstGrouping1.setClickable(false)
                firstGrouping1.setAlpha(.3f)
            } else {
                firstGrouping1.chipStrokeWidth = 4f
                secondGrouping1.setAlpha(.3f)
                secondGrouping1.setClickable(false)
                thirdGrouping1.setAlpha(.3f)
                thirdGrouping1.setClickable(false)
            }
            if (player != 2) {
                firstGrouping2.setClickable(false)
                firstGrouping2.setAlpha(.3f)
            } else {
                firstGrouping2.chipStrokeWidth = 4f
                secondGrouping2.setAlpha(.3f)
                secondGrouping2.setClickable(false)
                thirdGrouping2.setAlpha(.3f)
                thirdGrouping2.setClickable(false)
            }
            if (player != 3) {
                firstGrouping3.setClickable(false)
                firstGrouping3.setAlpha(.3f)
            } else {
                firstGrouping3.chipStrokeWidth = 4f
                secondGrouping3.setAlpha(.3f)
                secondGrouping3.setClickable(false)
                thirdGrouping3.setAlpha(.3f)
                thirdGrouping3.setClickable(false)
            }
        } else {
            if (player == 1) {
                firstGrouping1.chipStrokeWidth = 0f
                if (!secondGroupClaimed) {
                    secondGrouping1.setAlpha(1f)
                    secondGrouping1.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping1.setAlpha(1f)
                    thirdGrouping1.setClickable(true)
                }
            } else if (!p1Claimed) {
                firstGrouping1.setAlpha(1f)
                firstGrouping1.setClickable(true)
            }
            if (player == 2) {
                firstGrouping2.chipStrokeWidth = 0f
                if (!secondGroupClaimed) {
                    secondGrouping2.setAlpha(1f)
                    secondGrouping2.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping2.setAlpha(1f)
                    thirdGrouping2.setClickable(true)
                }
            } else if (!p2Claimed) {
                firstGrouping2.setAlpha(1f)
                firstGrouping2.setClickable(true)
            }
            if (player == 3) {
                firstGrouping3.chipStrokeWidth = 0f
                if (!secondGroupClaimed) {
                    secondGrouping3.setAlpha(1f)
                    secondGrouping3.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping3.setAlpha(1f)
                    thirdGrouping3.setClickable(true)
                }
            } else if (!p3Claimed) {
                firstGrouping3.setAlpha(1f)
                firstGrouping3.setClickable(true)
            }
        }
    }

    fun toggleSecondGroup(player: Int) {
        secondGroupClaimed = !secondGroupClaimed
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        if (secondGroupClaimed) {
            if (player != 1) {
                secondGrouping1.setClickable(false)
                secondGrouping1.setAlpha(.3f)
            } else {
                secondGrouping1.chipStrokeWidth = 4f
                firstGrouping1.setAlpha(.3f)
                firstGrouping1.setClickable(false)
                thirdGrouping1.setAlpha(.3f)
                thirdGrouping1.setClickable(false)
            }
            if (player != 2) {
                secondGrouping2.setClickable(false)
                secondGrouping2.setAlpha(.3f)
            } else {
                secondGrouping2.chipStrokeWidth = 4f
                firstGrouping2.setAlpha(.3f)
                firstGrouping2.setClickable(false)
                thirdGrouping2.setAlpha(.3f)
                thirdGrouping2.setClickable(false)
            }
            if (player != 3) {
                secondGrouping3.setClickable(false)
                secondGrouping3.setAlpha(.3f)
            } else {
                secondGrouping3.chipStrokeWidth = 4f
                firstGrouping3.setAlpha(.3f)
                firstGrouping3.setClickable(false)
                thirdGrouping3.setAlpha(.3f)
                thirdGrouping3.setClickable(false)
            }
        } else {
            if (player == 1) {
                secondGrouping1.chipStrokeWidth = 0f
                if (!firstGroupClaimed) {
                    firstGrouping1.setAlpha(1f)
                    firstGrouping1.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping1.setAlpha(1f)
                    thirdGrouping1.setClickable(true)
                }
            } else if (!p1Claimed) {
                secondGrouping1.setAlpha(1f)
                secondGrouping1.setClickable(true)
            }
            if (player == 2) {
                secondGrouping2.chipStrokeWidth = 0f
                if (!firstGroupClaimed) {
                    firstGrouping2.setAlpha(1f)
                    firstGrouping2.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping2.setAlpha(1f)
                    thirdGrouping2.setClickable(true)
                }
            } else if (!p2Claimed) {
                secondGrouping2.setAlpha(1f)
                secondGrouping2.setClickable(true)
            }
            if (player == 3) {
                secondGrouping3.chipStrokeWidth = 0f
                if (!firstGroupClaimed) {
                    firstGrouping3.setAlpha(1f)
                    firstGrouping3.setClickable(true)
                }
                if (!thirdGroupClaimed) {
                    thirdGrouping3.setAlpha(1f)
                    thirdGrouping3.setClickable(true)
                }
            } else if (!p3Claimed) {
                secondGrouping3.setAlpha(1f)
                secondGrouping3.setClickable(true)
            }
        }
    }

    fun toggleThirdGroup(player: Int) {
        thirdGroupClaimed = !thirdGroupClaimed
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        if (thirdGroupClaimed) {
            if (player != 1) {
                thirdGrouping1.setClickable(false)
                thirdGrouping1.setAlpha(.3f)
            } else {
                thirdGrouping1.chipStrokeWidth = 4f
                secondGrouping1.setAlpha(.3f)
                secondGrouping1.setClickable(false)
                firstGrouping1.setAlpha(.3f)
                firstGrouping1.setClickable(false)
            }
            if (player != 2) {
                thirdGrouping2.setClickable(false)
                thirdGrouping2.setAlpha(.3f)
            } else {
                thirdGrouping2.chipStrokeWidth = 4f
                secondGrouping2.setAlpha(.3f)
                secondGrouping2.setClickable(false)
                firstGrouping2.setAlpha(.3f)
                firstGrouping2.setClickable(false)
            }
            if (player != 3) {
                thirdGrouping3.setClickable(false)
                thirdGrouping3.setAlpha(.3f)
            } else {
                thirdGrouping3.chipStrokeWidth = 4f
                secondGrouping3.setAlpha(.3f)
                secondGrouping3.setClickable(false)
                firstGrouping3.setAlpha(.3f)
                firstGrouping3.setClickable(false)
            }
        } else {
            if (player == 1) {
                thirdGrouping1.chipStrokeWidth = 0f
                if (!secondGroupClaimed) {
                    secondGrouping1.setAlpha(1f)
                    secondGrouping1.setClickable(true)
                }
                if (!firstGroupClaimed) {
                    firstGrouping1.setAlpha(1f)
                    firstGrouping1.setClickable(true)
                }
            } else if (!p1Claimed) {
                thirdGrouping1.setAlpha(1f)
                thirdGrouping1.setClickable(true)
            }
            if (player == 2) {
                thirdGrouping2.chipStrokeWidth = 0f
                if (!secondGroupClaimed) {
                    secondGrouping2.setAlpha(1f)
                    secondGrouping2.setClickable(true)
                }
                if (!firstGroupClaimed) {
                    firstGrouping2.setAlpha(1f)
                    firstGrouping2.setClickable(true)
                }
            } else if (!p2Claimed) {
                thirdGrouping2.setAlpha(1f)
                thirdGrouping2.setClickable(true)
            }
            if (player == 3) {
                thirdGrouping3.chipStrokeWidth = 0f
                if (!secondGroupClaimed) {
                    secondGrouping3.setAlpha(1f)
                    secondGrouping3.setClickable(true)
                }
                if (!firstGroupClaimed) {
                    firstGrouping3.setAlpha(1f)
                    firstGrouping3.setClickable(true)
                }
            } else if (!p3Claimed) {
                thirdGrouping3.setAlpha(1f)
                thirdGrouping3.setClickable(true)
            }
        }
    }

    fun claimFirstGroup1(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p1Claimed = !p1Claimed
        toggleFirstGroup(1)
    }

    fun claimFirstGroup2(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p2Claimed = !p2Claimed
        toggleFirstGroup(2)
    }

    fun claimFirstGroup3(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p3Claimed = !p3Claimed
        toggleFirstGroup(3)
    }

    fun claimSecondGroup1(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p1Claimed = !p1Claimed
        toggleSecondGroup(1)
    }

    fun claimSecondGroup2(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p2Claimed = !p2Claimed
        toggleSecondGroup(2)
    }

    fun claimSecondGroup3(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p3Claimed = !p3Claimed
        toggleSecondGroup(3)
    }
    fun claimThirdGroup1(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p1Claimed = !p1Claimed
        toggleThirdGroup(1)
    }

    fun claimThirdGroup2(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p2Claimed = !p2Claimed
        toggleThirdGroup(2)
    }

    fun claimThirdGroup3(view: View?) {
        if (activePlayers != 3) {
            return
        }
        p3Claimed = !p3Claimed
        toggleThirdGroup(3)
    }
}
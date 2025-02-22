package com.example.myapplication

import android.os.Bundle
import android.view.View
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
        }
    }

    fun resetPlayer1(view: View?) {
        val infoCard = findViewById<CardView>(R.id.playerInfo1)
        val addPlayerCard = findViewById<CardView>(R.id.addPlayer1Card)
        val nameInput = findViewById<EditText>(R.id.nameInput1)
        nameInput.setText(p1Name)
        infoCard.visibility = View.GONE
        addPlayerCard.visibility = View.VISIBLE
        // add whatever score change or other reset
    }

    fun resetPlayer2(view: View?) {
        val infoCard = findViewById<CardView>(R.id.playerInfo2)
        val addPlayerCard = findViewById<CardView>(R.id.addPlayer2Card)
        val nameInput = findViewById<EditText>(R.id.nameInput2)
        nameInput.setText(p2Name)
        infoCard.visibility = View.GONE
        addPlayerCard.visibility = View.VISIBLE
        // add whatever score change or other reset
    }

    fun resetPlayer3(view: View?) {
        val infoCard = findViewById<CardView>(R.id.playerInfo3)
        val addPlayerCard = findViewById<CardView>(R.id.addPlayer3Card)
        val nameInput = findViewById<EditText>(R.id.nameInput3)
        nameInput.setText(p3Name)
        infoCard.visibility = View.GONE
        addPlayerCard.visibility = View.VISIBLE

        resetScoresAndGroupings()

         // add whatever score change or other reset
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

        firstGroupClaimed = false
        secondGroupClaimed = false
        thirdGroupClaimed = false
        p1Claimed = false
        p2Claimed = false
        p3Claimed = false

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

        if (score1 < 5) {
            score1 += 1
            resetConfirm(0)
            val scoreCount = findViewById<TextView>(R.id.p1Score)
            scoreCount.text = score1.toString()
        }
    }

    fun decrScore1(view: View?) {
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

        if (score2 < 5) {
            score2 += 1
            resetConfirm(0)
            val scoreCount = findViewById<TextView>(R.id.p2Score)
            scoreCount.text = score2.toString()
        }
    }

    fun decrScore2(view: View?) {
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

        if (score3 < 5) {
            score3 += 1
            resetConfirm(0)
            val scoreCount = findViewById<TextView>(R.id.p3Score)
            scoreCount.text = score3.toString()
        }
    }

    fun decrScore3(view: View?) {
        resetConfirm(1)
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

    fun claimFirstGroup1(view: View?) {
        firstGroupClaimed = !firstGroupClaimed
        p1Claimed = !p1Claimed
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        if (firstGroupClaimed) {
            firstGrouping1.chipStrokeWidth = 4f
            firstGrouping2.setAlpha(.3f)
            firstGrouping2.setClickable(false)
            firstGrouping3.setAlpha(.3f)
            firstGrouping3.setClickable(false)
            secondGrouping1.setAlpha(.3f)
            secondGrouping1.setClickable(false)
            thirdGrouping1.setAlpha(.3f)
            thirdGrouping1.setClickable(false)
        } else {
            firstGrouping1.chipStrokeWidth = 0f
            if (!p2Claimed) {
                firstGrouping2.setAlpha(1f)
                firstGrouping2.setClickable(true)
            }
            if (!p3Claimed) {
                firstGrouping3.setAlpha(1f)
                firstGrouping3.setClickable(true)
            }
            if (!secondGroupClaimed) {
                secondGrouping1.setAlpha(1f)
                secondGrouping1.setClickable(true)
            }
            if (!thirdGroupClaimed) {
                thirdGrouping1.setAlpha(1f)
                thirdGrouping1.setClickable(true)
            }
        }
    }

    fun claimFirstGroup2(view: View?) {
        firstGroupClaimed = !firstGroupClaimed
        p2Claimed = !p2Claimed
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        if (firstGroupClaimed) {
            firstGrouping2.chipStrokeWidth = 4f
            firstGrouping1.setAlpha(.3f)
            firstGrouping1.setClickable(false)
            firstGrouping3.setAlpha(.3f)
            firstGrouping3.setClickable(false)
            secondGrouping2.setAlpha(.3f)
            secondGrouping2.setClickable(false)
            thirdGrouping2.setAlpha(.3f)
            thirdGrouping2.setClickable(false)
        } else {
            firstGrouping2.chipStrokeWidth = 0f
            if (!p1Claimed) {
                firstGrouping1.setAlpha(1f)
                firstGrouping1.setClickable(true)
            }
            if (!p3Claimed) {
                firstGrouping3.setAlpha(1f)
                firstGrouping3.setClickable(true)
            }
            if (!secondGroupClaimed) {
                secondGrouping2.setAlpha(1f)
                secondGrouping2.setClickable(true)
            }
            if (!thirdGroupClaimed) {
                thirdGrouping2.setAlpha(1f)
                thirdGrouping2.setClickable(true)
            }
        }
    }

    fun claimFirstGroup3(view: View?) {
        firstGroupClaimed = !firstGroupClaimed
        p3Claimed = !p3Claimed
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        if (firstGroupClaimed) {
            firstGrouping3.chipStrokeWidth = 4f
            firstGrouping2.setAlpha(.3f)
            firstGrouping2.setClickable(false)
            firstGrouping1.setAlpha(.3f)
            firstGrouping1.setClickable(false)
            secondGrouping3.setAlpha(.3f)
            secondGrouping3.setClickable(false)
            thirdGrouping3.setAlpha(.3f)
            thirdGrouping3.setClickable(false)
        } else {
            firstGrouping3.chipStrokeWidth = 0f
            if (!p2Claimed) {
                firstGrouping2.setAlpha(1f)
                firstGrouping2.setClickable(true)
            }
            if (!p1Claimed) {
                firstGrouping1.setAlpha(1f)
                firstGrouping1.setClickable(true)
            }
            if (!secondGroupClaimed) {
                secondGrouping3.setAlpha(1f)
                secondGrouping3.setClickable(true)
            }
            if (!thirdGroupClaimed) {
                thirdGrouping3.setAlpha(1f)
                thirdGrouping3.setClickable(true)
            }
        }
    }

    fun claimSecondGroup1(view: View?) {
        secondGroupClaimed = !secondGroupClaimed
        p1Claimed = !p1Claimed
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        if (secondGroupClaimed) {
            secondGrouping1.chipStrokeWidth = 4f
            secondGrouping2.setAlpha(.3f)
            secondGrouping2.setClickable(false)
            secondGrouping3.setAlpha(.3f)
            secondGrouping3.setClickable(false)
            firstGrouping1.setAlpha(.3f)
            firstGrouping1.setClickable(false)
            thirdGrouping1.setAlpha(.3f)
            thirdGrouping1.setClickable(false)
        } else {
            secondGrouping1.chipStrokeWidth = 0f
            if (!p2Claimed) {
                secondGrouping2.setAlpha(1f)
                secondGrouping2.setClickable(true)
            }
            if (!p3Claimed) {
                secondGrouping3.setAlpha(1f)
                secondGrouping3.setClickable(true)
            }
            if (!firstGroupClaimed) {
                firstGrouping1.setAlpha(1f)
                firstGrouping1.setClickable(true)
            }
            if (!thirdGroupClaimed) {
                thirdGrouping1.setAlpha(1f)
                thirdGrouping1.setClickable(true)
            }
        }
    }

    fun claimSecondGroup2(view: View?) {
        secondGroupClaimed = !secondGroupClaimed
        p2Claimed = !p2Claimed
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        if (secondGroupClaimed) {
            secondGrouping2.chipStrokeWidth = 4f
            secondGrouping1.setAlpha(.3f)
            secondGrouping1.setClickable(false)
            secondGrouping3.setAlpha(.3f)
            secondGrouping3.setClickable(false)
            firstGrouping2.setAlpha(.3f)
            firstGrouping2.setClickable(false)
            thirdGrouping2.setAlpha(.3f)
            thirdGrouping2.setClickable(false)
        } else {
            secondGrouping2.chipStrokeWidth = 0f
            if (!p1Claimed) {
                secondGrouping1.setAlpha(1f)
                secondGrouping1.setClickable(true)
            }
            if (!p3Claimed) {
                secondGrouping3.setAlpha(1f)
                secondGrouping3.setClickable(true)
            }
            if (!firstGroupClaimed) {
                firstGrouping2.setAlpha(1f)
                firstGrouping2.setClickable(true)
            }
            if (!thirdGroupClaimed) {
                thirdGrouping2.setAlpha(1f)
                thirdGrouping2.setClickable(true)
            }
        }
    }

    fun claimSecondGroup3(view: View?) {
        secondGroupClaimed = !secondGroupClaimed
        p3Claimed = !p3Claimed
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        if (secondGroupClaimed) {
            secondGrouping3.chipStrokeWidth = 4f
            secondGrouping2.setAlpha(.3f)
            secondGrouping2.setClickable(false)
            secondGrouping1.setAlpha(.3f)
            secondGrouping1.setClickable(false)
            firstGrouping3.setAlpha(.3f)
            firstGrouping3.setClickable(false)
            thirdGrouping3.setAlpha(.3f)
            thirdGrouping3.setClickable(false)
        } else {
            secondGrouping3.chipStrokeWidth = 0f
            if (!p2Claimed) {
                secondGrouping2.setAlpha(1f)
                secondGrouping2.setClickable(true)
            }
            if (!p1Claimed) {
                secondGrouping1.setAlpha(1f)
                secondGrouping1.setClickable(true)
            }
            if (!firstGroupClaimed) {
                firstGrouping3.setAlpha(1f)
                firstGrouping3.setClickable(true)
            }
            if (!thirdGroupClaimed) {
                thirdGrouping3.setAlpha(1f)
                thirdGrouping3.setClickable(true)
            }
        }
    }
    fun claimThirdGroup1(view: View?) {
        thirdGroupClaimed = !thirdGroupClaimed
        p1Claimed = !p1Claimed
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        val firstGrouping1 = findViewById<Chip>(R.id.firstGrouping1)
        val secondGrouping1 = findViewById<Chip>(R.id.secondGrouping1)
        if (thirdGroupClaimed) {
            thirdGrouping1.chipStrokeWidth = 4f
            thirdGrouping2.setAlpha(.3f)
            thirdGrouping2.setClickable(false)
            thirdGrouping3.setAlpha(.3f)
            thirdGrouping3.setClickable(false)
            firstGrouping1.setAlpha(.3f)
            firstGrouping1.setClickable(false)
            secondGrouping1.setAlpha(.3f)
            secondGrouping1.setClickable(false)
        } else {
            thirdGrouping1.chipStrokeWidth = 0f
            if (!p2Claimed) {
                thirdGrouping2.setAlpha(1f)
                thirdGrouping2.setClickable(true)
            }
            if (!p3Claimed) {
                thirdGrouping3.setAlpha(1f)
                thirdGrouping3.setClickable(true)
            }
            if (firstGroupClaimed) {
                firstGrouping1.setAlpha(1f)
                firstGrouping1.setClickable(true)
            }
            if (secondGroupClaimed) {
                secondGrouping1.setAlpha(1f)
                secondGrouping1.setClickable(true)
            }
        }
    }

    fun claimThirdGroup2(view: View?) {
        secondGroupClaimed = !secondGroupClaimed
        p2Claimed = !p2Claimed
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        val firstGrouping2 = findViewById<Chip>(R.id.firstGrouping2)
        val secondGrouping2 = findViewById<Chip>(R.id.secondGrouping2)
        if (secondGroupClaimed) {
            thirdGrouping2.chipStrokeWidth = 4f
            thirdGrouping1.setAlpha(.3f)
            thirdGrouping1.setClickable(false)
            thirdGrouping3.setAlpha(.3f)
            thirdGrouping3.setClickable(false)
            firstGrouping2.setAlpha(.3f)
            firstGrouping2.setClickable(false)
            secondGrouping2.setAlpha(.3f)
            secondGrouping2.setClickable(false)
        } else {
            thirdGrouping2.chipStrokeWidth = 0f
            if (!p1Claimed) {
                thirdGrouping1.setAlpha(1f)
                thirdGrouping1.setClickable(true)
            }
            if (!p3Claimed) {
                thirdGrouping3.setAlpha(1f)
                thirdGrouping3.setClickable(true)
            }
            if (!firstGroupClaimed) {
                firstGrouping2.setAlpha(1f)
                firstGrouping2.setClickable(true)
            }
            if (!secondGroupClaimed) {
                secondGrouping2.setAlpha(1f)
                secondGrouping2.setClickable(true)
            }
        }
    }

    fun claimThirdGroup3(view: View?) {
        thirdGroupClaimed = !thirdGroupClaimed
        p3Claimed = !p3Claimed
        val thirdGrouping3 = findViewById<Chip>(R.id.thirdGrouping3)
        val thirdGrouping2 = findViewById<Chip>(R.id.thirdGrouping2)
        val thirdGrouping1 = findViewById<Chip>(R.id.thirdGrouping1)
        val firstGrouping3 = findViewById<Chip>(R.id.firstGrouping3)
        val secondGrouping3 = findViewById<Chip>(R.id.secondGrouping3)
        if (thirdGroupClaimed) {
            thirdGrouping3.chipStrokeWidth = 4f
            thirdGrouping2.setAlpha(.3f)
            thirdGrouping2.setClickable(false)
            thirdGrouping1.setAlpha(.3f)
            thirdGrouping1.setClickable(false)
            firstGrouping3.setAlpha(.3f)
            firstGrouping3.setClickable(false)
            secondGrouping3.setAlpha(.3f)
            secondGrouping3.setClickable(false)
        } else {
            thirdGrouping3.chipStrokeWidth = 0f
            if (!p2Claimed) {
                thirdGrouping2.setAlpha(1f)
                thirdGrouping2.setClickable(true)
            }
            if (!p1Claimed) {
                thirdGrouping1.setAlpha(1f)
                thirdGrouping1.setClickable(true)
            }
            if (!firstGroupClaimed) {
                firstGrouping3.setAlpha(1f)
                firstGrouping3.setClickable(true)
            }
            if (!secondGroupClaimed) {
                secondGrouping3.setAlpha(1f)
                secondGrouping3.setClickable(true)
            }
        }
    }
}
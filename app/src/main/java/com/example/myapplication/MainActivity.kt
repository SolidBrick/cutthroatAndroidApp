package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.EditText
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private var score1 = 5
    private var score2 = 5
    private var score3 = 5

    private var p1Name = "Player 1"
    private var p2Name = "Player 2"
    private var p3Name = "Player 3"

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
        // add whatever score change or other reset
    }

    fun incScore1(view: View?) {
        if (score1 < 5) {
            score1 += 1
            val scoreCount = findViewById<TextView>(R.id.p1Score)
            scoreCount.text = score1.toString()
        }
    }

    fun decrScore1(view: View?) {
        if (score1 > 0) {
            score1 -= 1
            val scoreCount = findViewById<TextView>(R.id.p1Score)
            scoreCount.text = score1.toString()
            if (score1 == 0) {
                println(p1Name + " wins!")
                Toast.makeText(this, p1Name + " wins!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
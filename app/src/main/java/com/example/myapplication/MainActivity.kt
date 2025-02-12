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
        }
    }
    fun addPlayer3(view: View?) {
        val editText = findViewById<EditText>(R.id.nameInput3)
        val text = editText.text.toString().trim()
        if (text.isNullOrBlank()) {
            Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_SHORT).show()
        } else {
            println(text)
        }
    }
    fun resetPlayer1(view: View?) {
        val infoCard = findViewById<CardView>(R.id.playerInfo1)
        val addPlayerCard = findViewById<CardView>(R.id.addPlayer1Card)
        val nameInput = findViewById<EditText>(R.id.nameInput1)
        nameInput.setText("Player 1")
        infoCard.visibility = View.GONE
        addPlayerCard.visibility = View.VISIBLE
        // add whatever score change or other reset
    }
}
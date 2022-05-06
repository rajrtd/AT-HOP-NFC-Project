package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelectCardScreen :AppCompatActivity() {

    private lateinit var addHopCardBtn: Button
    private lateinit var addGymCardBtn: Button
    private lateinit var addLoyaltyCardBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_cards)

        addHopCardBtn = findViewById(R.id.hopCardBtn)
        addGymCardBtn = findViewById(R.id.gymCardBtn)
        addLoyaltyCardBtn = findViewById(R.id.loyaltyCardBtn)

        addHopCardBtn.setOnClickListener {
            val intent = Intent(this, AddHopCardScreen::class.java)
            startActivity(intent)
        }

        addGymCardBtn.setOnClickListener {
            val intent = Intent(this, AddGymCardScreen::class.java)
            startActivity(intent)
        }

        addLoyaltyCardBtn.setOnClickListener {
            val intent = Intent(this, AddLoyaltyCardScreen::class.java)
            startActivity(intent)
        }
    }
}
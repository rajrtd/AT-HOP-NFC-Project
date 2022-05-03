package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelectCardScreen :AppCompatActivity() {

    private lateinit var addHopCardBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_cards)

        addHopCardBtn = findViewById(R.id.hopCard);

        addHopCardBtn.setOnClickListener {
            val intent = Intent(this, AddHopCard::class.java)
            startActivity(intent)
        }
    }

}
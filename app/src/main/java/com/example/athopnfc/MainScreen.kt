package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainScreen : AppCompatActivity() {

    private lateinit var addCardsBtn: Button
    // What is persistable bundle? - second paramater in onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        addCardsBtn = findViewById(R.id.addCardsBtn)

        addCardsBtn.setOnClickListener {
            val intent = Intent(this, SelectCardScreen::class.java)
            startActivity(intent)
        }

    }
}
package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_card)
=======
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

>>>>>>> Raj
    }
}
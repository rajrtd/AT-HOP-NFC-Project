package com.example.athopnfc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val button : Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
        val button2 : Button = findViewById(R.id.alreadyLogged)
        button2.setOnClickListener {
            val intent2 = Intent(this, MainScreen::class.java)
            startActivity(intent2)
        }
    }
}
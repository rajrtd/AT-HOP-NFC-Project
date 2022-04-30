package com.example.athopnfc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD

        val button: Button = findViewById(R.id.button)
=======
        val button : Button = findViewById(R.id.debugButton)

>>>>>>> Raj
        button.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
        val button2: Button = findViewById(R.id.alreadyLogged)
        button2.setOnClickListener {
            val intent2 = Intent(this, MainScreen::class.java)
            startActivity(intent2)
        }
    }
}
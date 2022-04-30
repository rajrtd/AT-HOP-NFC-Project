package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainScreen : AppCompatActivity(){

    //LOGOUT BUTTON THINGS
    private lateinit var btnLogOut : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        auth = Firebase.auth

        btnLogOut = findViewById(R.id.btnLogOut)
        btnLogOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginScreen::class.java))
        }
    }
}
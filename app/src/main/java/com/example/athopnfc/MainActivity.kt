package com.example.athopnfc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//The whole purpose of this class is to find out if the user is already logged in.
class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //On creation of the screen it will display the main activity which is just a blank screen.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
    }

    override fun onStart(){
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser

        updateUI(currentUser)
    }

    //This function checks to see if the user is logged in already, if they are logged in then they get taken to the main screen
    //else they get taken to the login screen.
    private fun updateUI(user: FirebaseUser?){
        if (user == null){
            startActivity(Intent(this, LoginScreen::class.java))
        }else{
            startActivity(Intent(this, MainScreen::class.java))
        }
    }
}
// HELP ME
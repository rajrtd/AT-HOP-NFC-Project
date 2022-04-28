package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest

//Here we can see the AppCompatActivity() extend
class LoginScreen : AppCompatActivity() {

//    val signInRequest = BeginSignInRequest.builder()
//    .setGoogleIdTokenRequestOptions(
//    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//    .setSupported(true)
//    // Your server's client ID, not your Android client ID.
//    .setServerClientId(getString(R.string.your_web_client_id))
//    // Only show accounts previously used to sign in.
//    .setFilterByAuthorizedAccounts(true)
//    .build())
//    .build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_screen)

        //Here I am declaring a button and giving it the XML button via ID, in your case do findViewByID(R.id.idofthebutton)
        val sUButton: Button = findViewById(R.id.signUpButton)
        //Now that I have the button object I can access the action listener

        sUButton.setOnClickListener {
            //Making an intent object i think, well you just give it this class and the next one.
            val intent = Intent(this, CreateAccount::class.java)
            //dont forget this part which makes it run.
            startActivity(intent)
            }
    }
}

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //In here make sure to set the content to the new XML screen.
        setContentView(R.layout.create_account_screen)
    }
}
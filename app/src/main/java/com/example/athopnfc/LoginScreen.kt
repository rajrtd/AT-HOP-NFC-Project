package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//Here we can see the AppCompatActivity() extend
class LoginScreen : AppCompatActivity(), UserFunctions {
    private lateinit var logInEmailEditText: EditText
    private lateinit var logInPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var logInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_screen)

        //Here I am declaring a button and giving it the XML button via ID, in your case do findViewByID(R.id.idofthebutton)
        signUpButton = findViewById(R.id.signUpButton)
        logInButton = findViewById(R.id.logInButton)
        logInEmailEditText = findViewById(R.id.emailField)
        logInPasswordEditText = findViewById(R.id.passwordField)

        //Now that I have the button object I can access the action listener
        signUpButton.setOnClickListener {
            //Making an intent object i think, well you just give it this class and the next one.
            val intent = Intent(this, CreateAccount::class.java)
            //dont forget this part which makes it run.
            startActivity(intent)
        }
        logInButton.setOnClickListener {
            if(validateEmail(logInEmailEditText) && validatePassword(logInPasswordEditText)){
                val user = User(logInEmailEditText.text.toString(), logInPasswordEditText.text.toString())
                Toast.makeText(this@LoginScreen, user.emailAddress, Toast.LENGTH_LONG).show()
            }
        }
    }


}
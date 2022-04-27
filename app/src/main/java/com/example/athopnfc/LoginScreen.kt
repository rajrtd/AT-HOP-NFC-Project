package com.example.athopnfc

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

        val account = getAccountPreference()
        if(account.emailAddress != null && account.password != null){
            //TODO change the intent to show the main screen instead of the debug screen again.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else Toast.makeText(this@LoginScreen, "Error", Toast.LENGTH_LONG).show()

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
                val account = Account(logInEmailEditText.text.toString(), logInPasswordEditText.text.toString())
                if (saveToPreference(account.emailAddress, account.password)){
                    Toast.makeText(this@LoginScreen, account.emailAddress, Toast.LENGTH_SHORT).show()
                    //TODO change the intent to show the mainscreen instead of the login screen again.
                    val intent = Intent(this, LoginScreen::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginScreen, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun saveToPreference(emailAddress: String?, password: String?): Boolean {
        val sp : SharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
        val ed : SharedPreferences.Editor = sp.edit()
        ed.putString("email", emailAddress)
        ed.putString("Password", password)
        return ed.commit()
    }

    override fun getAccountPreference(): Account {
        val sp = this.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = sp.getString("Email", "")
        val pass = sp.getString("Password", "")
        return Account(email, pass)
    }


}
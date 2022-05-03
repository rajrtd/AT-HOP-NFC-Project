package com.example.athopnfc

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateAccount : AppCompatActivity(), UserFunctions {

    //Declare all the buttons and textFields
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPassEditText: EditText
    private lateinit var btnCreateAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //In here make sure to set the content to the new XML screen.
        setContentView(R.layout.create_account_screen)

        //Initialize buttons and textFields here
        emailEditText = findViewById(R.id.signUpEmailField)
        passwordEditText = findViewById(R.id.signUpPasswordField)
        confirmPassEditText = findViewById(R.id.confirmPasswordField)
        btnCreateAccount = findViewById(R.id.createAccountButton)

        btnCreateAccount.setOnClickListener {
            /*
            if (validateEmail(emailEditText) && passwordsMatch(passwordEditText, confirmPassEditText)){
                val account = Account(emailEditText.text.toString(), passwordEditText.text.toString())

                if (saveToPreference(account.emailAddress, account.password)){
                    Toast.makeText(this@CreateAccount, account.emailAddress, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginScreen::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@CreateAccount, "Error", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@CreateAccount, "Error", Toast.LENGTH_LONG).show()
            }
        }
        */

        }
    }

    override fun saveToPreference(emailAddress: String?, password: String?): Boolean {
        val sp: SharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sp.edit()
        ed.putString("email", emailAddress)
        ed.putString("Password", password)
        return ed.commit()
    }

    override fun getAccountPreference(): Account {
        return Account(null, null)

    }
}
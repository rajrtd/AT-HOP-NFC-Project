package com.example.athopnfc

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateAccount : AppCompatActivity(), UserFunctions {

    //Declare all the buttons and textFields
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPassEditText: EditText
    private lateinit var btnCreateAccount: Button

    private companion object{
        lateinit var auth: FirebaseAuth
    }


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
            if (validateEmail(emailEditText) && passwordsMatch(passwordEditText, confirmPassEditText)) {
                val account = Account(emailEditText.text.toString(), passwordEditText.text.toString())
                //add to database
                auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword("${account.emailAddress}", "${account.password}").addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this@CreateAccount, account.emailAddress, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginScreen::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener{
                    Toast.makeText(this@CreateAccount, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
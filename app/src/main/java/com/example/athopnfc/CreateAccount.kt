package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

//This class is in charge of creating an account
//I decided to add lots of comments to this one just so you understand the code but a lot of these actions get repeated throughout the code,
//Like click listeners and intent objects.
class CreateAccount : AppCompatActivity(), UserFunctions {

    //Declare all the buttons and textFields
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPassEditText: EditText
    private lateinit var btnCreateAccount: Button

    //Object that gets called statically.
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

        //Adding an on click listener that listens to the press of the user.
        btnCreateAccount.setOnClickListener {
            if (validateEmail(emailEditText) && passwordsMatch(passwordEditText, confirmPassEditText)) {    //This is a simple validation for the email and password entered by the user.
                val account = Account(emailEditText.text.toString(), passwordEditText.text.toString())      //Creating an account object.
                //add to database section.
                auth = FirebaseAuth.getInstance()   //This is pretty much getting a connection to the authentication part of the database, after making a successful connection.
                auth.createUserWithEmailAndPassword("${account.emailAddress}", "${account.password}").addOnCompleteListener{ //Creating a user in the database with custom log in.
                    if (it.isSuccessful){ //if the creation of the account is successful
                        Toast.makeText(this@CreateAccount, account.emailAddress, Toast.LENGTH_SHORT).show() //displays a message to the user.
                        val intent = Intent(this, LoginScreen::class.java) //intent will get called a few times throughout the code, it basically creates a binding between two applications

                        startActivity(intent)
                    }
                }.addOnFailureListener{ //if creating the account fails it will display a message.
                    Toast.makeText(this@CreateAccount, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
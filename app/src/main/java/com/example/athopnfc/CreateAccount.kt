package com.example.athopnfc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
            if (validateEmail(emailEditText) && passwordsMatch(passwordEditText, confirmPassEditText)){
                val user = User(emailEditText.text.toString(), passwordEditText.text.toString())
                Toast.makeText(this@CreateAccount, user.emailAddress, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@CreateAccount, "Error", Toast.LENGTH_LONG).show()
            }
        }
    }

}
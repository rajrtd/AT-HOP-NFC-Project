package com.example.athopnfc

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//Here we can see the AppCompatActivity() extend
class LoginScreen : AppCompatActivity(), UserFunctions {
    private lateinit var logInEmailEditText: EditText
    private lateinit var logInPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var logInButton: Button
    private lateinit var myGoogleSignInClient: GoogleSignInClient
    private lateinit var myGoogleSignInOptions: GoogleSignInOptions
    private lateinit var googlSgnInBtn: SignInButton
    private lateinit var sgnUpBtn: Button
    private lateinit var auth: FirebaseAuth

    // Way to hold all the constant static variables in the class
    private companion object {
        private const val TAG = "LoginScreen"
        private const val RC_GOOGLE_SIGN_IN = 4926
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_screen)
        auth = Firebase.auth

        //Here I am declaring a button and giving it the XML button via ID, in your case do findViewByID(R.id.idofthebutton)
        signUpButton = findViewById(R.id.signUpButton)
        logInButton = findViewById(R.id.logInButton)
        logInEmailEditText = findViewById(R.id.emailField)
        logInPasswordEditText = findViewById(R.id.passwordField)
        googlSgnInBtn = findViewById(R.id.googleSignInButton)
        myGoogleSignInClient = GoogleSignIn.getClient(this, myGoogleSignInOptions)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        myGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        //Now that I have the button object I can access the action listener
        signUpButton.setOnClickListener {
            //Making an intent object i think, well you just give it this class and the next one.
            val intent = Intent(this, CreateAccount::class.java)
            //dont forget this part which makes it run.
            startActivity(intent)
        }

        logInButton.setOnClickListener {
            if (validateEmail(logInEmailEditText) && validatePassword(logInPasswordEditText)) {
                val account = Account(logInEmailEditText.text.toString(), logInPasswordEditText.text.toString())

                if (saveToPreference(account.emailAddress, account.password)) {
                    Toast.makeText(this@LoginScreen, account.emailAddress, Toast.LENGTH_SHORT).show()
                    //TODO change the intent to show the mainscreen instead of the login screen again.
                    val intent = Intent(this, LoginScreen::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginScreen, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }

        googlSgnInBtn.setOnClickListener {
            val sgnInIntent = myGoogleSignInClient.signInIntent
            startActivityForResult(sgnInIntent, RC_GOOGLE_SIGN_IN)
            startActivity(sgnInIntent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                // Google Sign In was successful
                Log.d(TAG, "firebaseAuthWithGoogle: " + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In was NOT successful
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    // To navigate to the LoginScreen
    private fun updateUI(user: FirebaseUser?) {

        if (user == null) {
            Log.w(TAG, "User is null, not going to navigate")
        }

        startActivity(Intent(this, MainScreen::class.java))
        finish()
    }

    override fun saveToPreference(emailAddress: String?, password: String?): Boolean {
        // Added this. for sp variable
        val sp: SharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sp.edit()
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

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this)
            { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)

                } else {
                    Log.w(TAG, "SignInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
}
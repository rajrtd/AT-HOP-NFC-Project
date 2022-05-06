package com.example.athopnfc

import android.content.Intent
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//Here we can see the AppCompatActivity() extend
class LoginScreen : AppCompatActivity(), UserFunctions {
    private lateinit var logInEmailEditText: EditText
    private lateinit var logInPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var logInButton: Button
    private lateinit var myGoogleSignInClient: GoogleSignInClient
    //private lateinit var myGoogleSignInOptions: GoogleSignInOptions
    private lateinit var googlSgnInBtn: SignInButton
    private lateinit var sgnUpBtn: Button
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    // Way to hold all the constant static variables in the class
    private companion object {
        private const val TAG = "LoginScreen"
        private const val RC_GOOGLE_SIGN_IN = 4926
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in_screen)
        auth = Firebase.auth

        signUpButton = findViewById(R.id.signUpButton)
        logInButton = findViewById(R.id.logInButton)
        logInEmailEditText = findViewById(R.id.emailField)
        logInPasswordEditText = findViewById(R.id.passwordField)
        googlSgnInBtn = findViewById(R.id.googleSignInButton)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
         val myGoogleSignInOptions : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        myGoogleSignInClient = GoogleSignIn.getClient(this, myGoogleSignInOptions) //this is how you interact with the Google sign in API, retrieved directly from firebase
        signUpButton.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }

        logInButton.setOnClickListener {
            if (validateEmail(logInEmailEditText) && validatePassword(logInPasswordEditText)) { //validating user inputs.
                val account = Account(logInEmailEditText.text.toString(), logInPasswordEditText.text.toString())

                //Log in without Google account to database and displaying error messages
                auth.signInWithEmailAndPassword("${account.emailAddress}", "${account.password}").addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this@LoginScreen, account.emailAddress, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainScreen::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener{
                    Toast.makeText(this@LoginScreen, "Incorrect password or account does not exist.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        googlSgnInBtn.setOnClickListener { //This is to log in with a Google account, the code is also provided from Google firebase
            val sgnInIntent = myGoogleSignInClient.signInIntent
            startActivityForResult(sgnInIntent, RC_GOOGLE_SIGN_IN)
            startActivity(sgnInIntent)
        }
    }

    //This whole function interacts with the Google API in order to log in with a google account
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

    //this function uses a token that is given by the Google API to sign in with Google to firebase.
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
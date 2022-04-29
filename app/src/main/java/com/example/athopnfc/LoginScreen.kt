package com.example.athopnfc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//Here we can see the AppCompatActivity() extend
class LoginScreen : AppCompatActivity() {

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
        sgnUpBtn = findViewById(R.id.signUpButton)
        //Now that I have the button object I can access the action listener
        sgnUpBtn.setOnClickListener {
            //Making an intent object i think, well you just give it this class and the next one.
            val intent = Intent(this, CreateAccount::class.java)
            //don't forget this part which makes it run.
            //startActivity(intent)
        }

        googlSgnInBtn = findViewById(R.id.googleSignInButton)
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        myGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        myGoogleSignInClient = GoogleSignIn.getClient(this, myGoogleSignInOptions)
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

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
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
    // To navigate to the LoginScreen
    private fun updateUI(user: FirebaseUser?) {

        if (user == null) {
            Log.w(TAG, "User is null, not going to navigate")
        }

        startActivity(Intent(this, MainScreen::class.java))
        finish()
    }
}

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //In here make sure to set the content to the new XML screen.
        setContentView(R.layout.create_account_screen)
    }
}
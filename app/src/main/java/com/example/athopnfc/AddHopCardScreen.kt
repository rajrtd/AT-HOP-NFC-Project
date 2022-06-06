package com.example.athopnfc

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//This class is so that the user can add a hop card
class AddHopCardScreen : AppCompatActivity() {

    private lateinit var cardNameField : EditText
    private lateinit var cardNumberField : EditText
    private lateinit var cardDescription : EditText
    private lateinit var cardSaveBtn : Button

    private companion object {
        lateinit var auth: FirebaseAuth
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_athop_card)

        cardNameField = findViewById(R.id.hopCardNameTxtInpt)
        cardNumberField = findViewById(R.id.hopCardNoTxtInpt)
        cardDescription = findViewById(R.id.hopCardDescTxtInpt)
        cardSaveBtn = findViewById(R.id.saveAtHopCard)
        val db = Firebase.firestore

        cardSaveBtn.setOnClickListener{
            if(validateEmptyString(cardNameField, cardNumberField, cardDescription)){
                auth = FirebaseAuth.getInstance()

                val docCard = hashMapOf(
                    "cardName" to cardNameField.text.toString(),
                    "cardNumber" to cardNumberField.text.toString(),
                    "cardDesc" to cardDescription.text.toString()
                )
                val docWallet = hashMapOf(
                    "HopCard" to docCard,
                    "displayName" to auth.currentUser?.displayName.toString(),
                    "email" to auth.currentUser?.email.toString()
                )

                db.collection("users").document(auth.currentUser?.email.toString())
                    .set(docWallet)
                    .addOnSuccessListener { Log.d(TAG, "Success")
                        Toast.makeText(this@AddHopCardScreen, "Hop card successfully added.", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this, MainScreen::class.java))
                    }
                    .addOnFailureListener { e -> Log.w(TAG, "Error at uploading data", e)}
            }
        }
    }


    private fun validateEmptyString(cardName : EditText, cardNumber : EditText, cardDesc : EditText) : Boolean{
        return cardName.text.toString().isNotBlank() && cardNumber.text.toString()
            .isNotBlank() && cardDesc.text.toString().isNotBlank()
    }
}

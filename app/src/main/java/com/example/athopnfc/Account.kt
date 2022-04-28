package com.example.athopnfc

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Account(val accOwner: String, val pass: String) {
    val db = Firebase
}
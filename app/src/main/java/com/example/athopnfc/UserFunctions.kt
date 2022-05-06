package com.example.athopnfc

import android.widget.EditText

// This is just an interface to declare functions that are relevant to the user account.
// May be renamed to account functions.
interface UserFunctions {

    fun validateEmail(email: EditText): Boolean {
        if (email.text.isBlank()) {
            return false
        }
        return true
    }

    fun passwordsMatch(password: EditText, confirmPassword: EditText): Boolean {
        if (password.text.toString() != confirmPassword.text.toString()) {
            return false
        }
        if (password.text.toString().isBlank() || confirmPassword.text.toString().isBlank()) {
            return false
        }
        return true
    }

    fun validatePassword(password: EditText): Boolean {
        if (password.text.toString().isBlank()) {
            return false
        }
        return true
    }
}

package com.example.athopnfc

import android.widget.EditText

interface UserFunctions{

    fun validateEmail(email:EditText):Boolean{
        if (email.text.isBlank()){
            return false
        }
        return true
    }
    fun passwordsMatch(password:EditText, confirmPassword:EditText):Boolean{
        if (password.text.toString() != confirmPassword.text.toString()){
            return false
        }
        if (password.text.toString().isBlank() || confirmPassword.text.toString().isBlank()){
            return false
        }
        return true
    }
    fun validatePassword(password:EditText): Boolean{
        if (password.text.toString().isBlank()){
            return false
        }
        return true
    }

    fun saveToPreference(emailAddress: String?, password:String?):Boolean

    fun getAccountPreference(): Account
    /*
    fun userLogOut():Boolean{
        val sp : SharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
        val ed : SharedPreferences.Editor = sp.edit()
        ed.putString("email", null)
        ed.putString("Password", null)
        return ed.commit()
    }
     */

    //TODO: Function to write to sharedpreferences when the user logs in or when the user creates an account.
    //TODO: Function to write to database when the user logs in or when the user creates an account.
    //TODO: if user has logged in and not logged out, query the database for the user login and password to see if it matches.
}
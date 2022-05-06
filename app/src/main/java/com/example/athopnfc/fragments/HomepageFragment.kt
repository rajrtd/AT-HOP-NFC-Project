package com.example.athopnfc.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.athopnfc.LoginScreen
import com.example.athopnfc.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//Homepage fragment basically is for displaying purposes,
// it allows for us to swipe left and right in the screen without having to press a button.
//This one is specific to the section of the screen that displays the homepage information.
//It will eventually have the cards, but for now it has a logout button just for testing purposes.
class HomepageFragment : Fragment(){

    private lateinit var btnLogOut: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)
        auth = Firebase.auth
        btnLogOut = view.findViewById(R.id.logOutButton)
        btnLogOut.setOnClickListener {
            val logoutIntent = Intent(view.context, LoginScreen::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
        }
        return view
    }
}
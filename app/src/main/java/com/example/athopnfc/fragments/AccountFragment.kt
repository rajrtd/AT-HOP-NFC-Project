package com.example.athopnfc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.athopnfc.R

//Account fragment basically is for displaying purposes,
// it allows for us to swipe left and right in the screen without having to press a button.
//This one is specific to the section of the screen that displays the user/account information.
class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }
}

package com.example.athopnfc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.athopnfc.R

//Records fragment basically is for displaying purposes,
// it allows for us to swipe left and right in the screen without having to press a button.
//This one is specific to the section of the screen that displays the Records information.
//It will eventually have a record of the last time a card was used.
class RecordsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }
}
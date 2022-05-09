package com.example.athopnfc.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = tabCount
    //This is what allows a user to swipe left and right on the screen to move across screens.
    override fun createFragment(position: Int): Fragment
    {
        return when (position)
        {
            0 -> HomepageFragment()
            1 -> RecordsFragment()
            2 -> AccountFragment()
            else -> HomepageFragment()
        }
    }

}
package com.example.athopnfc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.athopnfc.fragments.AccountFragment
import com.example.athopnfc.fragments.HomepageFragment
import com.example.athopnfc.fragments.RecordsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homepageFragment = HomepageFragment()
    private val recordsFragment = RecordsFragment()
    private val accountFragment = AccountFragment()
    //val bottomnavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homepageFragment)


        /*bottomnavigation.setOnNavigationItemSelectedListener { it:MenuItem
            when(it.itemId){
                R.id.ic_cardpage -> replaceFragment(homepageFragment)
                R.id.ic_records -> replaceFragment(recordsFragment)
                R.id.ic_account -> replaceFragment(accountFragment)

            }
            true
        }*/
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
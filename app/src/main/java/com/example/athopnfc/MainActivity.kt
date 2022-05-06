package com.example.athopnfc

import android.os.Bundle

import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.athopnfc.fragments.AccountFragment
import com.example.athopnfc.fragments.HomepageFragment
import com.example.athopnfc.fragments.RecordsFragment
import com.example.athopnfc.fragments.TabPageAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.navigation_layout.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



//The whole purpose of this class is to find out if the user is already logged in.
class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //On creation of the screen it will display the main activity which is just a blank screen.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_layout)
        setUpTabBar()
    }
    override fun onStart(){
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser

        updateUI(currentUser)
    }

    //This function checks to see if the user is logged in already, if they are logged in then they get taken to the main screen
    //else they get taken to the login screen.
    private fun updateUI(user: FirebaseUser?){
        if (user == null){
            startActivity(Intent(this, LoginScreen::class.java))
        }else{
            startActivity(Intent(this, MainScreen::class.java))
        }
    }
}
// HELP ME


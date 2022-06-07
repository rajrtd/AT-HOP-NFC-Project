package com.example.athopnfc

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginScreenTest
{
    //Testing to see that the screen is displaying


    @Test
    fun isScreenDisplaying2() {
        val activityScenario = ActivityScenario.launch(MainScreen::class.java)

        onView(withId(R.id.Loginscreen)).check(matches(isDisplayed()))
    }

    @Test
    fun isScreenDisplaying() {
        val activityScenario = ActivityScenario.launch(MainScreen::class.java)

        onView(withId(R.id.Navigation)).check(matches(isDisplayed()))
    }
}
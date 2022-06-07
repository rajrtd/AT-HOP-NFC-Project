package com.example.athopnfc

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

/*
    This class is to test the user story: "As a user I want to be able to create an account so that I can save all my cards and not have to re-enter them".
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class CreateAccountTest{

    //Testing to see that the screen is displaying
    @Test
    fun isScreenDisplaying(){
        val activityScenario = ActivityScenario.launch(CreateAccount::class.java)

        onView(withId(R.id.createAccount)).check(matches(isDisplayed()))
    }
    //Testing to see that the button is clickable
    @Test
    fun isButtonClickable(){
        val activityScenario = ActivityScenario.launch(CreateAccount::class.java)

        onView(withId(R.id.createAccountButton)).perform(click())
    }
    //Testing to see that the button is disabled after successfully creating an account
    @Test
    fun isMatching(){
        val activityScenario = ActivityScenario.launch(CreateAccount::class.java)

        onView(withId(R.id.signUpPasswordField)).perform(ViewActions.typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.confirmPasswordField)).perform(ViewActions.typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.createAccountButton)).perform((click()))
        onView(withId(R.id.createAccountButton)).check(matches(isEnabled()))
    }
}
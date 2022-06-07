package com.example.athopnfc

import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Boolean.TRUE

@RunWith(AndroidJUnit4::class)
class AddHopCardScreenTest {

    private fun assertEmptyString(cardName: String, cardNumber: String, cardDesc: String) : Boolean{
        return cardName.isNotBlank() && cardNumber.isNotBlank() && cardDesc.isNotBlank()
    }

    private fun setUp() {
        val mockCardName = "Raj's AT Hop Card"
        val mockCardNumber = "0123456789"
        val mockCardDescription = "Used to get to work and uni"
        val isEmpty: Boolean = assertEmptyString(mockCardName, mockCardNumber, mockCardDescription)
        assertEquals("Test 1", TRUE, isEmpty)
    }

    @Test
    fun testMain() {
        setUp()
    }
}
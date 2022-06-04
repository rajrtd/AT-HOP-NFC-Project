package com.example.athopnfc.nfc

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log


//The purpose of this class is to send a NFC message.
class HostCardEmulatorService : HostApduService(){

    /*The object companion is for validating the NFC communication, CLA dictates the security level but we will leave it at 00. INS basically is the command that lets
    the reader know the intent of the connection, 'A4' selects a file.
     */
    companion object {
        val TAG = "Host Card Emulator"
        val STATUS_SUCCESS = "9000" //This will notify the receiver that a valid AID has been received by the phone.
        val STATUS_FAILED = "6F00" //This will notify the receiver that the AID received is invalid.
        val CLA_NOT_SUPPORTED = "6E00" //This is to reply to the reader that the CLA is not supported
        val INS_NOT_SUPPORTED = "6D00"//This is to reply to the reader that the INS is not supported.
        val AIDATHOP = "F0000004231001" //This will be for AT HOP Cards
        val AIDGYM = "F0000004443331" //This one for Gym tags
        val AIDLOYAL = "F0000005231001" //This one for loyalty cards
        val SELECT_INS = "A4"
        val DEFAULT_CLA = "00"
        val MIN_APDU_LENGTH = 12 //This is used to verify that the reader is sending a message that is at least 12 digits long.
    }

    override fun onDeactivated(reason: Int) {
        Log.d(TAG, "Deactivated: $reason")
    }


    /*
    This method gets called automatically when the phone receives any NFC message, and it takes care of only communicating with the specified AID.
    It also replies with the different error messages.
     */
    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        if (commandApdu == null) { //Her
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }
        val hexCommandApdu = Utils.toHex(commandApdu)
        if (hexCommandApdu.length < MIN_APDU_LENGTH) {
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }
        if (hexCommandApdu.substring(0, 2) != DEFAULT_CLA) {
            return Utils.hexStringToByteArray(CLA_NOT_SUPPORTED)
        }
        if (hexCommandApdu.substring(2, 4) != SELECT_INS) {
            return Utils.hexStringToByteArray(INS_NOT_SUPPORTED)
        }

        // For this section we have split the different AIDs into different if statements,
        // with the intent of also checking that a button is being clicked regarding the same card.
        return if (hexCommandApdu.substring(10, 24) == AIDATHOP)  { //AT HOP card AID.
            Utils.hexStringToByteArray(STATUS_SUCCESS)
        } else if ( hexCommandApdu.substring(10, 24) == AIDGYM){    //GYM tag AID.
            Utils.hexStringToByteArray(STATUS_SUCCESS)
        } else if (hexCommandApdu.substring(10, 24) == AIDLOYAL){   //Loyalty tag AID.
            Utils.hexStringToByteArray(STATUS_SUCCESS)
        } else {                                                    //Else return a failed status.
            Utils.hexStringToByteArray(STATUS_FAILED)
        }
    }


}
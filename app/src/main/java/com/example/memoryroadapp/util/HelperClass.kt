package com.example.memoryroadapp

import android.content.Context
import android.util.Log
import android.widget.Toast

class HelperClass {
    companion object{
        const val AUTH_TAG: String = "FirebaseAuthAppTag"
        const val TEST_TAG: String = "TestMemoryRoadAppTag"

        fun logErrorMessage(errorMessage: String?){
            Log.d(AUTH_TAG, errorMessage)
        }

        fun logTestMessage(testMessage: String?){
            Log.d(TEST_TAG, testMessage)
        }

    }
}
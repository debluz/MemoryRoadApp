package com.example.memoryroadapp.utils

import android.util.Log

class HelperClass {
    companion object{
        private const val AUTH_TAG: String = "FirebaseAuthAppTag"
        private const val TEST_TAG: String = "TestMemoryRoadAppTag"

        fun logErrorMessage(errorMessage: String?){
            Log.d(AUTH_TAG, errorMessage)
        }

        fun logTestMessage(testMessage: String?){
            Log.d(TEST_TAG, testMessage)
        }

    }
}
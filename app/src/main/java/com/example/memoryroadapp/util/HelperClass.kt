package com.example.memoryroadapp

import android.content.Context
import android.util.Log
import android.widget.Toast

class HelperClass {
    companion object{
        fun logErrorMessage(errorMessage: String?){
            Log.d(Constants.AUTH_TAG, errorMessage)
        }

        fun logTestMessage(testMessage: String?){
            Log.d(Constants.TEST_TAG, testMessage)
        }

    }
}
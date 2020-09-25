package com.example.memoryroadapp

import android.util.Log

class HelperClass {
    companion object{
        fun logErrorMessage(errorMessage: String?){
            Log.d(Constants.TAG, errorMessage)
        }
    }
}
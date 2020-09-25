package com.example.memoryroadapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SignUpViewModel(application: Application): AndroidViewModel(application) {
    private val authRepository = AuthRepository()
}
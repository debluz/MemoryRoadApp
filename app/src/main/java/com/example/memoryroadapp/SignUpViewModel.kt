package com.example.memoryroadapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class SignUpViewModel(application: Application): AndroidViewModel(application) {
    private val authRepository = AuthRepository()
    lateinit var createdUserLiveData: LiveData<User>

    fun createUserWithEmail(email: String, password: String, name: String){
        createdUserLiveData = authRepository.createUserWithEmail(email, password, name)
    }

    fun signOut(){
        authRepository.signOut()
    }
}
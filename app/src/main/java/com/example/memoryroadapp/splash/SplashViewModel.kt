package com.example.memoryroadapp.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.memoryroadapp.User
import com.example.memoryroadapp.repositories.AuthRepository

class SplashViewModel : ViewModel(){
    private val authRepository = AuthRepository()
    lateinit var isUserAuthenticatedLiveData: LiveData<User>


    fun checkIfUserIsAuthenticated(){
        isUserAuthenticatedLiveData = authRepository.checkIfUserIsAuthenticated()
    }
}
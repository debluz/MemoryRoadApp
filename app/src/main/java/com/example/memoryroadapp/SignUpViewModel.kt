package com.example.memoryroadapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application): AndroidViewModel(application) {
    private val authRepository = AuthRepository()
    lateinit var createdUserLiveData: LiveData<User>

    fun createUserWithEmail(email: String, password: String, name: String){
        createdUserLiveData = authRepository.createUserWithEmail(email, password, name)
    }

}

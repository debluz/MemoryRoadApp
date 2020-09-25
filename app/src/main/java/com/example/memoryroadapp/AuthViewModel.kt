package com.example.memoryroadapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthCredential

class AuthViewModel(application: Application): AndroidViewModel(application) {
    private val authRepository = AuthRepository()
    lateinit var authenticatedUserLiveData: LiveData<User>
    lateinit var createdUserLiveData: LiveData<User>

    fun signInWithEmail(email: String, password: String){
        authenticatedUserLiveData = authRepository.firebaseSignInWithEmail(email, password)
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential){
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
    }

    fun createUser(authenticatedUser: User){
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser)
    }
}
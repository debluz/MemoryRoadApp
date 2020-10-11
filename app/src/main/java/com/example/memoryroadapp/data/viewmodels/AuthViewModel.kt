package com.example.memoryroadapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memoryroadapp.User
import com.example.memoryroadapp.repositories.AuthRepository
import com.google.firebase.auth.AuthCredential

class AuthViewModel(application: Application): AndroidViewModel(application) {
    private val authRepository = AuthRepository()
    lateinit var authenticatedUserLiveData: LiveData<User>
    lateinit var createdUserLiveData: LiveData<User>
    val emailEditTextContent = MutableLiveData<String>()
    val passwordEditTextContent = MutableLiveData<String>()
    private var _eventCode = MutableLiveData<Int>()
    var eventCode: LiveData<Int> = _eventCode

    fun onSignInButtonClick(){
        if(emailEditTextContent.value == null || passwordEditTextContent.value == null){
            _eventCode.value = 1
        } else {
            signInWithEmail(emailEditTextContent.value.toString(), passwordEditTextContent.value.toString())
            _eventCode.value = 2
        }
    }

    fun onSignUpButtonClick(){
        _eventCode.value = 3
    }

    fun onGoogleSignInButtonClick(){
        _eventCode.value = 4
    }

    fun signInWithEmail(email: String, password: String){
        authenticatedUserLiveData = authRepository.firebaseSignInWithEmail(email, password)
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential){
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
    }

    fun createUser(authenticatedUser: User){
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser)
    }

    fun signOut(){
        authRepository.signOut()
    }
}
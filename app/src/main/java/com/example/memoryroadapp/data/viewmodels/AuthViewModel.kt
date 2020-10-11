package com.example.memoryroadapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.User
import com.example.memoryroadapp.repositories.AuthRepository
import com.google.firebase.auth.AuthCredential

class AuthViewModel: ViewModel() {
    private val authRepository = AuthRepository()
    lateinit var authenticatedUserLiveData: LiveData<User>
    lateinit var createdUserLiveData: LiveData<User>
    val emailEditTextContent = MutableLiveData<String>()
    val passwordEditTextContent = MutableLiveData<String>()
    private var _eventCode = MutableLiveData<Int>()
    var eventCode: LiveData<Int> = _eventCode
    val clickableContent: String = "Register here"

    fun onSignInButtonClick(){
        if(emailEditTextContent.value == null || passwordEditTextContent.value == null){
            _eventCode.value = Constants.EC_EMPTY_FIELDS
        } else {
            signInWithEmail(emailEditTextContent.value.toString(), passwordEditTextContent.value.toString())
            _eventCode.value = Constants.EC_SIGN_IN_WITH_EMAIL
        }
    }

    fun onGoogleSignInButtonClick(){
        _eventCode.value = Constants.EC_SIGN_IN_GOOGLE
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

}
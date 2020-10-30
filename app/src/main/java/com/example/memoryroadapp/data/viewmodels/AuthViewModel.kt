package com.example.memoryroadapp.data

import androidx.lifecycle.*
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.User
import com.example.memoryroadapp.data.repositories.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestoreException

class AuthViewModel: ViewModel() {
    private val authRepository = AuthRepository()
    lateinit var currentUser: LiveData<FirebaseUser?>
    lateinit var authenticatedUserLiveData: LiveData<User>
    lateinit var createdUserLiveData: LiveData<User>
    val emailEditTextContent = MutableLiveData<String>()
    val passwordEditTextContent = MutableLiveData<String>()
    private var _eventCode = MutableLiveData<Int>()
    var eventCode: LiveData<Int> = _eventCode
    val clickableContent: String = "Register here"

    fun onSignInButtonClick(){
        if(emailEditTextContent.value.toString().trim().isNullOrEmpty() || passwordEditTextContent.value.toString().trim().isNullOrEmpty()){
            _eventCode.value = Constants.EC_EMPTY_FIELDS
        } else {
            signInWithEmail(emailEditTextContent.value.toString(), passwordEditTextContent.value.toString())
            _eventCode.value = Constants.EC_SIGN_IN_WITH_EMAIL
        }
    }



    private fun signInWithEmail(email: String, password: String){
        authenticatedUserLiveData = liveData {
            try{
                val data = authRepository.firebaseSignInWithEmail(email, password)
                HelperClass.logTestMessage(Thread.currentThread().toString())
                emit(data)
            }catch (e: FirebaseFirestoreException){
                _eventCode.value = Constants.EC_SIGN_IN_FAIL
            }

        }
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential){
        authenticatedUserLiveData =  liveData {
            try {
                val data = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
                emit(data)
            } catch (e: FirebaseFirestoreException){
                _eventCode.value = Constants.EC_SIGN_IN_FAIL
            }

        }
    }

    fun createUser(authenticatedUser: User){
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser)
    }

    fun checkIfAnyoneIsAuthenticated(){
        currentUser = authRepository.getCurrentUser()
    }
}
package com.example.memoryroadapp.data

import androidx.lifecycle.*
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.User
import com.example.memoryroadapp.data.repositories.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.xml.transform.Result

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
        if(emailEditTextContent.value == null || passwordEditTextContent.value == null){
            _eventCode.value = Constants.EC_EMPTY_FIELDS
        } else {
            signInWithEmail(emailEditTextContent.value.toString(), passwordEditTextContent.value.toString())
            _eventCode.value = Constants.EC_SIGN_IN_WITH_EMAIL
        }
    }


    private fun signUserWithEmail(email: String, password: String){


    }

    private fun signInWithEmail(email: String, password: String){
        authenticatedUserLiveData = liveData {
            try{
                val data = authRepository.signUser(emailEditTextContent.value.toString(), passwordEditTextContent.value.toString())
                emit(data)
            }catch (e: Exception){
                _eventCode.value = Constants.EC_SIGN_IN_WITH_EMAIL_FAIL
                HelperClass.logTestMessage(e.message)
            }

        }
        //authenticatedUserLiveData = authRepository.firebaseSignInWithEmail(email, password)
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential){
        //authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
    }

    fun createUser(authenticatedUser: User){
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser)
    }

    fun checkIfAnyoneIsAuthenticated(){
        currentUser = authRepository.getCurrentUser()
    }
}
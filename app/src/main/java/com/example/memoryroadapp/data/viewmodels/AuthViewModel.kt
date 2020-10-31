package com.example.memoryroadapp.data


import androidx.lifecycle.*
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.User
import com.example.memoryroadapp.data.repositories.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

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
        if(emailEditTextContent.value.isNullOrEmpty() || passwordEditTextContent.value.isNullOrEmpty()){
            _eventCode.value = Constants.EC_EMPTY_FIELDS
        } else {
            signInWithEmail(emailEditTextContent.value.toString(), passwordEditTextContent.value.toString())
            _eventCode.value = Constants.EC_SIGN_IN_WITH_EMAIL
        }
    }


    private fun signInWithEmail(email: String, password: String){
        authenticatedUserLiveData = liveData(Dispatchers.IO) {
            try{
                val data = authRepository.firebaseSignInWithEmail(email, password)
                HelperClass.logTestMessage(Thread.currentThread().name)
                emit(data)
            } catch (e: FirebaseAuthException){
                HelperClass.logTestMessage("HERE \n $e ${e.message}")
                _eventCode.postValue(Constants.EC_SIGN_IN_FAIL)
            } catch (e: FirebaseFirestoreException){
                HelperClass.logTestMessage("HERE \n $e ${e.message}")
                _eventCode.postValue(Constants.EC_SIGN_IN_FAIL)
            }

        }
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential){
        authenticatedUserLiveData =  liveData(Dispatchers.IO) {
            try {
                val data = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
                emit(data)
            } catch (e: FirebaseAuthException){
                _eventCode.postValue(Constants.EC_AUTH_FAIL)
            }

        }
    }

    fun createUser(authenticatedUser: User){
        createdUserLiveData = liveData(Dispatchers.IO) {
            try {
                val data = authRepository.createUserInFirestoreIfNotExists(authenticatedUser)
                emit(data)
            } catch (e: FirebaseFirestoreException){
                _eventCode.postValue(Constants.EC_AUTH_FAIL)
            }
        }
    }

    fun checkIfAnyoneIsAuthenticated(){
        currentUser = authRepository.getCurrentUser()
    }
}
package com.example.memoryroadapp.data.viewmodels


import androidx.lifecycle.*
import com.example.memoryroadapp.User
import com.example.memoryroadapp.data.repositories.AuthRepository
import com.example.memoryroadapp.util.AuthenticationResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import java.util.*

class AuthViewModel: ViewModel() {
    private val authRepository = AuthRepository()
    lateinit var currentUser: LiveData<FirebaseUser?>
    lateinit var authenticatedUserLiveData: LiveData<User>
    lateinit var createdUserLiveData: LiveData<User>
    val emailEditTextContent = MutableLiveData<String>()
    val passwordEditTextContent = MutableLiveData<String>()
    val clickableContent: String = "Register here"
    private var _result = MutableLiveData<AuthenticationResult>()
    val result : LiveData<AuthenticationResult> = _result


    fun onSignInButtonClick(){
        if(emailEditTextContent.value.isNullOrEmpty() || passwordEditTextContent.value.isNullOrEmpty()){
            _result.value = AuthenticationResult.Error.EmptyRequiredFields(InputMismatchException())
        } else {
            signInWithEmail(emailEditTextContent.value.toString(), passwordEditTextContent.value.toString())
            _result.value = AuthenticationResult.Success
        }
    }


    private fun signInWithEmail(email: String, password: String){
        authenticatedUserLiveData = liveData(Dispatchers.IO) {
            try{
                val data = authRepository.firebaseSignInWithEmail(email, password)
                emit(data)
            } catch (e: FirebaseAuthException){
                _result.postValue(AuthenticationResult.Error.InvalidCredentials(e))
            } catch (e: FirebaseFirestoreException){
                _result.postValue(AuthenticationResult.Error.FirestoreError(e))
            }

        }
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential){
        authenticatedUserLiveData =  liveData(Dispatchers.IO) {
            try {
                val data = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
                emit(data)
            } catch (e: FirebaseAuthException){
                _result.postValue(AuthenticationResult.Error.FirebaseAuthError(e))
            }

        }
    }

    fun createUser(authenticatedUser: User){
        createdUserLiveData = liveData(Dispatchers.IO) {
            try {
                val data = authRepository.createUserInFirestoreIfNotExists(authenticatedUser)
                emit(data)
            } catch (e: FirebaseFirestoreException){
                _result.postValue(AuthenticationResult.Error.FirestoreError(e))
            }
        }
    }

    fun checkIfAnyoneIsAuthenticated(){
        currentUser = authRepository.getCurrentUser()
    }
}
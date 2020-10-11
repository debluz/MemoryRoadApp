package com.example.memoryroadapp.data

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.*
import com.example.memoryroadapp.User
import com.example.memoryroadapp.repositories.AuthRepository

class SignUpViewModel(application: Application): AndroidViewModel(application) {
    private val authRepository = AuthRepository()
    lateinit var createdUserLiveData: LiveData<User>
    val emailEditTextContent = MutableLiveData<String>()
    val passwordEditTextContent = MutableLiveData<String>()
    val firstNameEditTextContent = MutableLiveData<String>()
    val lastNameEditTextContent = MutableLiveData<String>()
    private var _eventCode = MutableLiveData<Int>()
    val eventCode: LiveData<Int> = _eventCode
    private var _enabled = MutableLiveData<Boolean>()
    val enabled: LiveData<Boolean> = _enabled

    //Validation
    private var _validEmail = MutableLiveData<Boolean>(true)
    val validEmail: LiveData<Boolean> = _validEmail
    private var isEmailEmpty = true
    private var _validPassword = MutableLiveData<Boolean>(true)
    val validPassword: LiveData<Boolean> = _validPassword
    private var isPasswordEmpty = true

    private val emailRegex: Regex = Regex("^[a-zA-Z]+[@][a-zA-Z]+[.][a-zA-Z]{2,}$")
    val errorEmail: String = "E.g. example@email.com, exa.mple@email.uk"
    private val passwordRegex: Regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()])[a-zA-Z\\d!@#\$%^&*()]{8,}$")
    val errorPassword: String = "Min. 8 characters, at least one digit, one special character, one uppercase letter"


    fun onSignUpButtonClick(){

    }

    val emailTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            checkIfCanBeEnabled()
            if(!emailEditTextContent.value.toString().trim().isNullOrEmpty()){
                _validEmail.value = emailRegex.matches(emailEditTextContent.value.toString().trim())
            } else {
                _validEmail.value = true
            }
            //HelperClass.logTestMessage("Email: ${emailEditTextContent.value.toString()} valid: ${_validEmail.value}")

        }
    }

    val passwordTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            checkIfCanBeEnabled()
            if(!passwordEditTextContent.value.toString().trim().isNullOrEmpty()){
                _validPassword.value = passwordRegex.matches(passwordEditTextContent.value.toString().trim())
            } else {
                _validPassword.value = true
            }
            //HelperClass.logTestMessage("Password: ${passwordEditTextContent.value.toString()} valid: ${_validPassword.value}")

        }

    }

    private fun checkIfCanBeEnabled(){
        _enabled.value =
                !passwordEditTextContent.value.toString().trim().isNullOrEmpty() &&
                !emailEditTextContent.value.toString().trim().isNullOrEmpty()
    }




    fun createUserWithEmail(email: String, password: String, name: String){
        createdUserLiveData = authRepository.createUserWithEmail(email, password, name)
    }

}

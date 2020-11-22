package com.example.memoryroadapp.viewmodels

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.*
import com.example.memoryroadapp.User
import com.example.memoryroadapp.repositories.AuthRepository
import com.example.memoryroadapp.results.RegistrationResult
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class SignUpViewModel(application: Application): AndroidViewModel(application) {
    private val authRepository = AuthRepository()
    lateinit var createdUserLiveData: LiveData<User>
    val emailEditTextContent = MutableLiveData<String>()
    val passwordEditTextContent = MutableLiveData<String>()
    val firstNameEditTextContent = MutableLiveData<String>()
    val lastNameEditTextContent = MutableLiveData<String>()
    private var _result = MutableLiveData<RegistrationResult>()
    val result : LiveData<RegistrationResult> = _result
    private var _enabled = MutableLiveData<Boolean>(false)
    val enabled: LiveData<Boolean> = _enabled

    //Validation
    private var _validEmail = MutableLiveData<Boolean>(true)
    val validEmail : LiveData<Boolean> = _validEmail
    private var _validPassword = MutableLiveData<Boolean>(true)
    val validPassword : LiveData<Boolean> = _validPassword
    private var _validFirstName = MutableLiveData<Boolean>(true)
    val validFirstName : LiveData<Boolean> = _validFirstName
    private var _validLastName = MutableLiveData<Boolean>(true)
    val validLastName : LiveData<Boolean> = _validLastName


    private val emailRegex: Regex = Regex("^[a-zA-Z\\d]+[@][a-zA-Z\\d]+[.][a-zA-Z]{2,}$")
    val errorEmail: String = "E.g. example@email.com, exa.mple@email.uk"
    private val passwordRegex: Regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()])[a-zA-Z\\d!@#\$%^&*()]{8,}$")
    val errorPassword: String = "Min. 8 characters, at least one digit, one special character, one uppercase letter"
    val errorName: String = "This field is required."


    fun onSignUpButtonClick(){
        if(_enabled.value!!){
            createUserWithEmail(
                emailEditTextContent.value.toString().trim(),
                passwordEditTextContent.value.toString().trim(),
                "${firstNameEditTextContent.value.toString().trim()} ${lastNameEditTextContent.value.toString().trim()}")
            _result.value = RegistrationResult.Success
        } else {
            _result.value = RegistrationResult.Error.SomethingWentWrong(Exception())
        }

    }

    val emailTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if(!emailEditTextContent.value.toString().trim().isNullOrEmpty()){
                _validEmail.value = emailRegex.matches(emailEditTextContent.value.toString().trim())
            } else {
                _validEmail.value = false
            }
            checkIfCanBeEnabled()
        }
    }

    val passwordTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if(!passwordEditTextContent.value.toString().trim().isNullOrEmpty()){
                _validPassword.value = passwordRegex.matches(passwordEditTextContent.value.toString().trim())
            } else {
                _validPassword.value = false
            }
            checkIfCanBeEnabled()
            //HelperClass.logTestMessage("Password: ${passwordEditTextContent.value.toString()} valid: ${_validPassword.value}")

        }

    }

    val firstNameTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            _validFirstName.value = !firstNameEditTextContent.value.toString().trim().isNullOrEmpty()
            checkIfCanBeEnabled()
        }

    }

    val lastNameTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            _validLastName.value = !lastNameEditTextContent.value.toString().trim().isNullOrEmpty()
            checkIfCanBeEnabled()
        }

    }

    private fun checkIfCanBeEnabled(){
        if(!passwordEditTextContent.value.isNullOrEmpty()
            && !emailEditTextContent.value.isNullOrEmpty()
            && !firstNameEditTextContent.value.isNullOrEmpty()
            && !lastNameEditTextContent.value.isNullOrEmpty()){
            if(_validEmail.value!!
                && _validPassword.value!!
                && _validFirstName.value!!
                && _validLastName.value!!)
            {
                _enabled.value = true
            }
        } else {
            _enabled.value = false
        }
    }

    private fun createUserWithEmail(email: String, password: String, name: String){
        createdUserLiveData = liveData(Dispatchers.IO) {
            try {
                val data = authRepository.createUserWithEmail(email, password, name)
                emit(data)
            } catch (e: FirebaseAuthException){
                _result.postValue(RegistrationResult.Error.EmailAlreadyInUse(e))
            }
        }
    }

}

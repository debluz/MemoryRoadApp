package com.example.memoryroadapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.SignUpViewModel
import com.example.memoryroadapp.databinding.ActivitySignUpBinding
import com.example.memoryroadapp.utils.results.RegistrationResult

class SignUpActivity : AppCompatActivity() {
    private val signUpViewModel: SignUpViewModel by lazy { ViewModelProvider(this).get(SignUpViewModel::class.java) }
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_sign_up
        )
        binding.lifecycleOwner = this
        binding.viewmodel = signUpViewModel
        observeRegistrationResult()



    }

    private fun observeRegistrationResult(){
        signUpViewModel.result.observe(this, Observer {regResult ->
            when(regResult){
                RegistrationResult.Success -> {
                    signUpViewModel.createdUserLiveData.observe(this, Observer {
                        Toast.makeText(this, resources.getString(R.string.registration_completed), Toast.LENGTH_SHORT).show()
                        finish()
                    })
                }
                else -> Toast.makeText(this, regResult.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}
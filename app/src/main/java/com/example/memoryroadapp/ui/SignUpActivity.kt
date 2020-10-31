package com.example.memoryroadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.Constants.Companion.EC_REGISTRATION_COMPLETED
import com.example.memoryroadapp.Constants.Companion.EC_REGISTRATION_FAILURE
import com.example.memoryroadapp.data.SignUpViewModel
import com.example.memoryroadapp.databinding.ActivityLoginBinding
import com.example.memoryroadapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val signUpViewModel: SignUpViewModel by lazy { ViewModelProvider(this).get(SignUpViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val binding = DataBindingUtil.setContentView<ActivitySignUpBinding>(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.viewmodel = signUpViewModel
        initSignUpProcess()



    }

    private fun initSignUpProcess(){
        signUpViewModel.eventCode.observe(this, Observer { eventCode ->
            when(eventCode){
                EC_REGISTRATION_COMPLETED -> {
                    signUpViewModel.createdUserLiveData.observe(this, Observer {createdUser ->
                        if(createdUser.isCreated!!){
                            Toast.makeText(this, resources.getString(R.string.registration_completed), Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, resources.getString(R.string.email_already_taken), Toast.LENGTH_SHORT).show()
                        }
                    })
                }
                EC_REGISTRATION_FAILURE -> Toast.makeText(this, resources.getString(R.string.registration_failure), Toast.LENGTH_SHORT).show()
            }
        })
    }






}
package com.example.memoryroadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.databinding.ActivityLoginBinding
import com.example.memoryroadapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initSignUpViewModel()
        val binding = DataBindingUtil.setContentView<ActivitySignUpBinding>(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.viewmodel = signUpViewModel
        //initSignUpButton()
        //checkIfValid()



    }

    private fun initSignUpButton(){


        /*sign_up_button.setOnClickListener {
            if(email_edit_text_sign_up.text.isNullOrEmpty()){
                email_edit_text_sign_up.setError("blabla")
                Toast.makeText(this, "Adasdasd", Toast.LENGTH_SHORT).show()
            } else {
                if(email_edit_text_sign_up.error == null){
                    val email = email_edit_text_sign_up.text.toString()
                    val password = password_edit_text_sign_up.text.toString()
                    val firstName = first_name_edit_text_sign_up.text.toString()
                    val lastName = last_name_edit_text_sign_up.text.toString()
                    //createNewUser(email, password, "$firstName $lastName")
                    Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
                }
            }


            *//*setResult(RESULT_OK)
            finish()*//*
        }*/

    }

    private fun initSignUpViewModel(){
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    private fun createNewUser(email: String, password: String, name: String){
        signUpViewModel.createUserWithEmail(email, password, name)
        signUpViewModel.createdUserLiveData.observe(this, Observer {createdUser ->
            if(createdUser.isCreated!!){
                setResult(RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, resources.getString(R.string.email_already_taken), Toast.LENGTH_SHORT).show()
            }
        })
    }





}
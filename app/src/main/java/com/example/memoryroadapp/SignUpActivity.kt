package com.example.memoryroadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        initSignUpButton()

       /* val rootRef = FirebaseFirestore.getInstance()
        val firebaseAuth = FirebaseAuth.getInstance()
        sign_up_button.setOnClickListener {
            val email = email_edit_text_sign_up.text.toString()
            val password = password_edit_text_sign_up.text.toString()
            val firstName = first_name_edit_text_sign_up.text.toString()
            val lastName = last_name_edit_text_sign_up.text.toString()
            val user = User("sadas1", email, "$firstName $lastName",)
            *//*rootRef.collection("users").document(user.uid).set(user)
                .addOnSuccessListener {
                    Log.v("Tamik", "New user added to db!")
                    //firebaseAuth.signOut()
                    goToLoginActivity()
                    finish()
                }*//**//*
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){authTask ->
                    if(authTask.isSuccessful){
                        Toast.makeText(this, firebaseAuth.currentUser?.uid, Toast.LENGTH_SHORT).show()
                        val currentUser = firebaseAuth.currentUser
                        if(currentUser != null){
                            Log.d("Tamik", "Before adding user to db")
                            val user = User(currentUser.uid, email, "$firstName $lastName",)
                            Log.v("Tamik", user.toString())

                            rootRef.collection("users").document(currentUser.uid).set(user)
                                .addOnSuccessListener {
                                    Log.v("Tamik", "New user added to db!")
                                    firebaseAuth.signOut()
                                    goToLoginActivity()
                                    finish()
                                }



                        }

                    } else {
                        Toast.makeText(this, authTask.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }*//*
        }*/

    }

    private fun initSignUpButton(){
        sign_up_button.setOnClickListener {
            val email = email_edit_text_sign_up.text.toString()
            val password = password_edit_text_sign_up.text.toString()
            val firstName = first_name_edit_text_sign_up.text.toString()
            val lastName = last_name_edit_text_sign_up.text.toString()
            createNewUser(email, password, "$firstName $lastName")
            setResult(RESULT_OK)
            finish()
        }

    }

    private fun initSignUpViewModel(){
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    private fun createNewUser(email: String, password: String, name: String){
        signUpViewModel.createUserWithEmail(email, password, name)
        signUpViewModel.createdUserLiveData.observe(this, Observer {
            Toast.makeText(this, "New user has been created!", Toast.LENGTH_SHORT).show()
            HelperClass.logErrorMessage("SignUpActivity: $it has been created")
        })
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }
}
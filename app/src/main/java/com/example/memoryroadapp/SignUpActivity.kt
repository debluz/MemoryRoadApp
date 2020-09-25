package com.example.memoryroadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        /*val newUser = User("tam@gmail.com", "Tam", "Mat")
        rootRef.collection("users").document().set(newUser)
        rootRef.collection("users").document("NewasCKz7qosDBaTOMVU").get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show()
                Log.v("Tamik", "asdasdasd")
            }*/


        sign_up_button.setOnClickListener {
            val email = email_edit_text_sign_up.text.toString()
            val password = password_edit_text_sign_up.text.toString()
            val firstName = first_name_edit_text_sign_up.text.toString()
            val lastName = last_name_edit_text_sign_up.text.toString()
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){authTask ->
                    if(authTask.isSuccessful){
                        Toast.makeText(this, firebaseAuth.currentUser?.uid, Toast.LENGTH_SHORT).show()
                        val currentUser = firebaseAuth.currentUser
                        if(currentUser != null){
                            Log.d("Tamik", "Before adding user to db")
                            val user = User(email, firstName, lastName)
                            Log.v("Tamik", user.toString())

                            rootRef.collection("users").document(currentUser.uid).set(user)
                                .addOnSuccessListener {
                                    Log.v("Tamik", "New user added to db!")
                                    firebaseAuth.signOut()
                                    goToLoginActivity()
                                    finish()
                                }


                            /*rootRef.collection("users").document(currentUser.uid).set(newUser)
                                .addOnSuccessListener{
                                    Toast.makeText(this, "New user has been added to database!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener{
                                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                                }*/
                        }

                    } else {
                        Toast.makeText(this, authTask.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

    private fun goToLoginActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun createNewUser(){

    }
}
package com.example.memoryroadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class AuthActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var authViewModel: AuthViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Google authentication
        initGoogleSignInButton()
        initAuthViewModel()
        initGoogleSignInClient()

        //Email authentication
        initSignInButton()


        sign_up_button_login.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }




    }

    private fun initSignInButton(){
        sign_in_button.setOnClickListener {
            val email = email_edit_text_login.text.toString()
            val password = password_edit_text_login.text.toString()
            signInWithEmail(email, password)
        }
    }

    private fun initGoogleSignInButton() {
        google_sign_in_button.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun initAuthViewModel() {
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    private fun signInWithGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, Constants.RC_SING_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.RC_SING_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val googleAccount = task.getResult(ApiException::class.java)
                if(googleAccount != null){
                    //firebaseAuthWithGoogle(googleAccount.idToken)
                    signInWithGoogleAuthCredential(googleAccount.idToken)
                }
            } catch (e: ApiException){
                HelperClass.logErrorMessage(e.message)
            }
        }
    }

    private fun signInWithGoogleAuthCredential(idToken: String?) {
        val googleAuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        authViewModel.signInWithGoogle(googleAuthCredential)
        authViewModel.authenticatedUserLiveData.observe(this, Observer {authenticatedUser ->
            if(authenticatedUser.isNew!!){
                HelperClass.logErrorMessage("signInWithGoogleAuthCredential: 1")
                createNewUser(authenticatedUser)
            } else {
                HelperClass.logErrorMessage("signInWithGoogleAuthCredential: 2")
                goToMainActivity()
            }

        })
    }

    private fun createNewUser(authenticatedUser: User) {
        authViewModel.createUser(authenticatedUser)
        authViewModel.createdUserLiveData.observe(this, Observer { user ->
            if(user.isCreated!!){
                HelperClass.logErrorMessage("createNewUser: 1")
                Toast.makeText(this, "Hi ${user.name}!\n Your account was succesfully created.", Toast.LENGTH_LONG).show()
            }
            HelperClass.logErrorMessage("createNewUser: 2")
            goToMainActivity()
        })
    }


    /*private fun firebaseAuthWithGoogle(idToken: String?) {
        val googleCredential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(googleCredential)
            .addOnCompleteListener(this){authTask ->
                if(authTask.isSuccessful){
                    Toast.makeText(this, "Login by google has been succesful", Toast.LENGTH_SHORT).show()
                    Log.d("Tamik", "signInWithCredential:succes")
                    goToMainActivity()
                    finish()
                } else {
                    Log.w("Tamik", "signInWithCredential:failure")
                    Toast.makeText(this, "Login by google was failure", Toast.LENGTH_SHORT).show()
                }
            }
    }*/

    private fun signInWithEmail(email: String, password: String){
        authViewModel.signInWithEmail(email, password)
        authViewModel.authenticatedUserLiveData.observe(this, Observer { user ->
            Toast.makeText(this, "Hi ${user.name}! You've been successfully logged in!", Toast.LENGTH_SHORT).show()
            goToMainActivity()
        })
    }


    /*private fun signInByEmail(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){authTask ->
                if(authTask.isSuccessful){
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    goToMainActivity()
                    finish()
                }
            }
            .addOnFailureListener(this){
                Toast.makeText(this, it.message , Toast.LENGTH_SHORT).show()
            }
    }*/

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            goToMainActivity()
            finish()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
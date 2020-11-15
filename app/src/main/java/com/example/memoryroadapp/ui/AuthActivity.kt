package com.example.memoryroadapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.*
import com.example.memoryroadapp.data.viewmodels.AuthViewModel
import com.example.memoryroadapp.databinding.ActivityLoginBinding
import com.example.memoryroadapp.utils.results.AuthenticationResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

class AuthActivity : AppCompatActivity() {
    companion object{
        const val REQUEST_CODE_SING_IN: Int = 1234
    }
    private lateinit var googleSignInClient: GoogleSignInClient
    private val authViewModel: AuthViewModel by lazy { ViewModelProvider(this).get(AuthViewModel::class.java) }
    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
        binding.lifecycleOwner = this
        binding.viewmodel = authViewModel


        initGoogleSignInClient()
        initSignInWithGoogleButton()
        observeAuthResult()
    }



    private fun observeAuthResult(){
        authViewModel.result.observe(this, Observer {authResult ->
            when(authResult){
                AuthenticationResult.Success -> {
                    authViewModel.authenticatedUserLiveData.observe(this, Observer { user ->
                        HelperClass.logErrorMessage("AuthActivity: authenticatedUserLiveData - $user")
                        if(user.isAuthenticated!!){
                            goToMainActivity()
                            finish()
                        }
                    })
                }
                else -> Toast.makeText(this, authResult.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    private fun initSignInWithGoogleButton(){
        binding.googleSignInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, REQUEST_CODE_SING_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_SING_IN){
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
                createNewUser(authenticatedUser)
            } else {
                goToMainActivity()
            }

        })
    }

    private fun createNewUser(authenticatedUser: User) {
        authViewModel.createUser(authenticatedUser)
        authViewModel.createdUserLiveData.observe(this, Observer { user ->
            if(user.isCreated!!){
                Toast.makeText(this, "Hi ${user.name}!\n Your account was succesfully created.", Toast.LENGTH_LONG).show()
            }
            goToMainActivity()
            finish()
        })
    }

    override fun onStart() {
        super.onStart()
        checkIfUserIsAlreadyAuthenticated()
    }

    private fun checkIfUserIsAlreadyAuthenticated(){
        authViewModel.checkIfAnyoneIsAuthenticated()
        authViewModel.currentUser.observe(this, Observer { currentUser ->
            if(currentUser != null){
                goToMainActivity()
                finish()
            }
        })
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}


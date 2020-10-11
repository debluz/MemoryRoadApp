package com.example.memoryroadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.data.AuthViewModel
import com.example.memoryroadapp.databinding.ActivityLoginBinding
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
    private val authViewModel: AuthViewModel by lazy { ViewModelProvider(this).get(AuthViewModel::class.java) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewmodel = authViewModel

        //Google authentication
        //initGoogleSignInButton()
        initGoogleSignInClient()

        //Email authentication
        initButtons()
    }


    private fun initButtons(){
        authViewModel.eventCode.observe(this, Observer { eventCode ->
            when(eventCode){
                Constants.EC_EMPTY_FIELDS -> Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show()
                Constants.EC_SIGN_IN_WITH_EMAIL -> {
                    authViewModel.authenticatedUserLiveData.observe(this, Observer { user ->
                        HelperClass.logErrorMessage("AuthActivity: authenticatedUserLiveData - $user")
                        if(user.isAuthenticated!!){
                            goToMainActivity()
                        } else {
                            Toast.makeText(this, "Email or password is invalid", Toast.LENGTH_LONG).show()
                        }
                    })
                }
                Constants.EC_SIGN_IN_GOOGLE -> signInWithGoogle()
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
        } else if (requestCode == Constants.RC_SIGN_UP && resultCode == RESULT_OK){
            Toast.makeText(this, "New user created", Toast.LENGTH_SHORT).show()
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


    private fun signInWithEmail(email: String, password: String){
        authViewModel.signInWithEmail(email, password)
        authViewModel.authenticatedUserLiveData.observe(this, Observer { user ->
            Toast.makeText(this, "Hi ${user.name}! You've been successfully logged in!", Toast.LENGTH_SHORT).show()
            goToMainActivity()
        })
    }


    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            goToMainActivity()
            finish()
        }
    }

    fun goToSignUpActivity(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivityForResult(intent, Constants.RC_SIGN_UP)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}


package com.example.memoryroadapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.R
import com.example.memoryroadapp.ui.AuthActivity
import com.example.memoryroadapp.ui.MainActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashViewModel()
        checkIfUserIsAuthenticated()
    }

    private fun initSplashViewModel(){
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun checkIfUserIsAuthenticated(){
        splashViewModel.checkIfUserIsAuthenticated()
        splashViewModel.isUserAuthenticatedLiveData.observe(this, androidx.lifecycle.Observer {user ->
            if(!user.isAuthenticated!!){
                goToAuthActivity()
            } else {
                goToMainActivity()
            }
        })
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
package com.example.memoryroadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)
        val currentUser = firebaseAuth.currentUser
        if(googleSignInAccount != null){
            val name = googleSignInAccount.displayName
            val surname = googleSignInAccount.familyName
            val email = googleSignInAccount.email
            welcome_message_text_view.text = "Welcome $name $surname $email!"
        } else {
            currentUser?.let {user ->
                welcome_message_text_view.text = "Welcome ${user.displayName}, ${user.email}!"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.sign_out_button){
            Toast.makeText(this, "Logout successful!", Toast.LENGTH_SHORT).show()
            goToLoginActivity()
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun goToLoginActivity(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()
            .addOnCompleteListener(this){signOutTask ->
                Log.d("Tamik", "googleSignInClient has been signed out")
            }
        firebaseAuth.signOut()
    }
}
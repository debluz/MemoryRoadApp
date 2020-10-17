package com.example.memoryroadapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.viewmodels.MainViewModel
import com.example.memoryroadapp.databinding.ActivityMainBinding
import com.example.memoryroadapp.ui.AddEditLocationActivity
import com.example.memoryroadapp.util.MyAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var viewAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = mainViewModel

        initButton()
        viewAdapter = MyAdapter()
        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = viewAdapter
        }
        mainViewModel.allLocations.observe(this, Observer {locations ->
            viewAdapter.setLocations(locations)
        })


    }


    private fun initButton() {
        mainViewModel.eventCode.observe(this, Observer { eventCode ->
            when(eventCode){
                Constants.EC_CREATE_LOCATION -> {
                    val intent = Intent(this, AddEditLocationActivity::class.java)
                    startActivityForResult(intent, Constants.REQ_C_ADD_LOCATION)
                }
            }
        })
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
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()
            .addOnCompleteListener(this){signOutTask ->
                Log.d("Tamik", "googleSignInClient has been signed out")
            }
        firebaseAuth.signOut()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }
}
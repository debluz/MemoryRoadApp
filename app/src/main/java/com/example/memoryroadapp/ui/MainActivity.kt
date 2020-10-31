package com.example.memoryroadapp.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.viewmodels.MainViewModel
import com.example.memoryroadapp.databinding.ActivityMainBinding
import com.example.memoryroadapp.util.MyAdapter
import com.example.memoryroadapp.util.OnItemListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemListener {
    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var viewAdapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private var isUsed : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "My Locations"
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = mainViewModel

        initAddLocationButton()
        initRecyclerView()
        initSwipeToDelete()
        initShowOnMapButton()
    }


    private fun initRecyclerView(){
        viewAdapter = MyAdapter(this@MainActivity)
        recyclerView.apply {
            this.adapter = viewAdapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }


        val storageRef = Firebase.storage.reference
        val imagesRef = storageRef.child("images").child(FirebaseAuth.getInstance().currentUser?.uid!!)

        mainViewModel.getAllLocations()
        mainViewModel.allLocations.observe(this, Observer {locations ->
            viewAdapter.setLocations(locations)
        })


    }


    private fun initAddLocationButton() {
        addLocationFAB.setOnClickListener {
            val intent = Intent(this, AddEditLocationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initShowOnMapButton(){
        showOnMapFAB.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putParcelableArrayListExtra(Constants.SELECTED_LOCATIONS, viewAdapter.getSelectedLocations())
            startActivity(intent)
        }
    }

    private fun switchButtons() {
        if(viewAdapter.getCheckBoxFlag()){
            addLocationFAB.visibility = View.GONE
            showOnMapFAB.visibility = View.VISIBLE
        } else {
            addLocationFAB.visibility = View.VISIBLE
            showOnMapFAB.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out_button -> {
                Toast.makeText(this, "Logout successful!", Toast.LENGTH_SHORT).show()
                goToLoginActivity()
                finish()
                return true
            }
            R.id.show_selected_on_map_option -> {
                isUsed = if(!isUsed){
                    viewAdapter.setCheckBoxFlag(true)
                    switchButtons()
                    true
                } else {
                    viewAdapter.setCheckBoxFlag(false)
                    switchButtons()
                    false
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity(){
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()
            .addOnCompleteListener(this){_ ->
                HelperClass.logTestMessage("googleSignInClient has been signed out")
            }
        mainViewModel.signOut()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onItemClickListener(location: MyLocation) {
        val intent = Intent(this, LocationInfoActivity::class.java)
        intent.apply {
            putExtra(Constants.EXTRA_ID, location.uid)
            putExtra(Constants.EXTRA_NAME, location.name)
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        if(viewAdapter.getCheckBoxFlag()){
            viewAdapter.setCheckBoxFlag(false)
            switchButtons()
        } else {
            super.onBackPressed()
        }
    }

    private fun initSwipeToDelete(){
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, swipeFlag){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val location = viewAdapter.getLocationAt(viewHolder.adapterPosition)
                mainViewModel.deleteLocation(location)
                Snackbar.make(recyclerView, "Location: ${location.uid} deleted.", Snackbar.LENGTH_SHORT)
                    .setAction("Undo", View.OnClickListener {
                        mainViewModel.undoDeletion(location)
                    })
                    .setActionTextColor(Color.CYAN)
                    .show()
            }
        }).attachToRecyclerView(recyclerView)
    }




}
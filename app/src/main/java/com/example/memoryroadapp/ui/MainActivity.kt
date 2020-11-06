package com.example.memoryroadapp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
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
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.viewmodels.MainViewModel
import com.example.memoryroadapp.databinding.ActivityMainBinding
import com.example.memoryroadapp.util.GeofenceUtil
import com.example.memoryroadapp.util.MyAdapter
import com.example.memoryroadapp.util.OnItemListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemListener {
    companion object{
        private const val EXTRA_ID = "com.example.memoryroadapp.ui.EXTRA_ID"
        private const val EXTRA_NAME = "com.example.memoryroadapp.ui.EXTRA_NAME"
        private const val SELECTED_LOCATIONS = "com.example.memoryroadapp.ui.SELECTED_LOCATIONS"
        private const val LOCATION_PERMISSIONS_REQUEST = 111
    }
    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var viewAdapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private var isOnMapCheckBoxActive : Boolean = false
    private lateinit var optionsMenu: Menu
    lateinit var geofencingClient: GeofencingClient
    lateinit var geofenceUtil: GeofenceUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "My Locations"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = mainViewModel

        initAddLocationButton()
        initRecyclerView()
        initSwipeToDelete()
        initShowOnMapButton()
        geofencingClient = LocationServices.getGeofencingClient(this)




    }


    private fun initRecyclerView(){
        viewAdapter = MyAdapter(this@MainActivity)
        binding.recyclerView.apply {
            this.adapter = viewAdapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.getAllLocations()
        mainViewModel.allLocations.observe(this, Observer {locations ->
            viewAdapter.setLocations(locations)
        })
    }

    private fun getGeofencingRequest(): GeofencingRequest{
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geofenceUtil.geofenceList)
        }.build()
    }

    private fun getCurrentLocationRequest(){
        val locationRequest = LocationRequest.create().apply {
            interval= 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())
            .addOnSuccessListener {
                
            }
            .addOnFailureListener {

            }
    }


    private fun initAddLocationButton() {
        binding.addLocationFAB.setOnClickListener {
            val intent = Intent(this, AddEditLocationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initShowOnMapButton(){
        binding.showOnMapFAB.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putParcelableArrayListExtra(SELECTED_LOCATIONS, viewAdapter.getSelectedLocations())
            startActivity(intent)
        }
    }

    private fun switchButtons() {
        if(viewAdapter.getCheckBoxFlag()){
            binding.addLocationFAB.visibility = View.GONE
            binding.showOnMapFAB.visibility = View.VISIBLE
        } else {
            binding.addLocationFAB.visibility = View.VISIBLE
            binding.showOnMapFAB.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        if (menu != null) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
                menu.findItem(R.id.geofence_option).isChecked = true
            }
            optionsMenu = menu
        }
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
                isOnMapCheckBoxActive = if(!isOnMapCheckBoxActive){
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
            R.id.geofence_option -> {
                checkLocationPermissions(item)
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
            putExtra(EXTRA_ID, location.uid)
            putExtra(EXTRA_NAME, location.name)
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        if(viewAdapter.getCheckBoxFlag()){
            viewAdapter.setCheckBoxFlag(false)
            isOnMapCheckBoxActive = false
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

    private fun checkLocationPermissions(item: MenuItem){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            if(item.isChecked){
                Toast.makeText(this, "To turn off Geofence, change location permission in your system settings.", Toast.LENGTH_SHORT).show()
            }
            item.isChecked = true
        } else {
            item.isChecked = false
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                LOCATION_PERMISSIONS_REQUEST
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSIONS_REQUEST
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            optionsMenu.findItem(R.id.geofence_option).apply {
                this.isChecked = true
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }


    }


}
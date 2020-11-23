package com.example.memoryroadapp.ui

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memoryroadapp.utils.HelperClass
import com.example.memoryroadapp.utils.GeofenceBroadcastReceiver
import com.example.memoryroadapp.R
import com.example.memoryroadapp.models.MyLocation
import com.example.memoryroadapp.viewmodels.MainViewModel
import com.example.memoryroadapp.databinding.ActivityMainBinding
import com.example.memoryroadapp.utils.MyAdapter
import com.example.memoryroadapp.utils.OnItemListener
import com.example.memoryroadapp.utils.createNotificationChannel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.google.rpc.Help
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity(), OnItemListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewAdapter: MyAdapter
    private lateinit var binding: ActivityMainBinding
    private var isOnMapCheckBoxActive: Boolean = false
    private lateinit var optionMenu: Menu
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var geofencingClient: GeofencingClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var geofencePendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "My Locations"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewmodel = mainViewModel

        initAddLocationButton()
        setAnimationForFAB()
        initRecyclerView()
        initSwipeToDelete()
        initShowOnMapButton()
        checkGeofence()

        geofencingClient = LocationServices.getGeofencingClient(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        /*locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                HelperClass.logTestMessage("Lat: ${locationResult.lastLocation.latitude} Lng: ${locationResult.lastLocation.longitude}")
            }
        }*/
        createNotificationChannel(this)
    }

    private fun createGeofencePendingIntent(locations: ArrayList<MyLocation>): PendingIntent{
        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        HelperClass.logTestMessage("$EXTRA_LOCATIONS: ${locations.size}")
        intent.action = ACTION_GEOFENCE_EVENT
        val bundle = Bundle()
        bundle.putParcelableArrayList(EXTRA_LOCATIONS, locations)
        intent.putExtra(EXTRA_BUNDLE, bundle)
        return PendingIntent.getBroadcast(this, REQUEST_PENDING_INTENT, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createGeofences(locations: ArrayList<MyLocation>): ArrayList<Geofence> {
        return  ArrayList(locations.map {location ->
            val geofence = Geofence.Builder()
                .setRequestId(location.uid)
                .setCircularRegion(location.latitude?.toDouble()!!, location.longitude?.toDouble()!!, location.diameter?.toFloat()!!)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build()
            geofence
        })
    }

    private fun createGeofencingRequest(locations: ArrayList<MyLocation>): GeofencingRequest {
        val geofenceList = createGeofences(locations)

        for(geofence in geofenceList){
            HelperClass.logTestMessage(geofence.toString())
        }
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geofenceList)
        }.build()
    }

    private fun addGeofencesToClient(geofenceRequest: GeofencingRequest){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            ACCESS_FINE_LOCATION_REQUEST)
            return
        }

        geofencePendingIntent = createGeofencePendingIntent(viewAdapter.locations)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                geofencingClient.addGeofences(geofenceRequest, geofencePendingIntent).await()
                HelperClass.logTestMessage("Geofences added")
            } catch (e: Exception){
                HelperClass.logTestMessage("Failed to add geofences... \n" +
                        "$e : ${e.message}"
                )
            }
        }
    }

    private fun removeGeofences(){
        if(this::geofencePendingIntent.isInitialized){
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    geofencingClient.removeGeofences(geofencePendingIntent).await()
                    HelperClass.logTestMessage("Geofences removed")
                } catch (e: java.lang.Exception){
                    HelperClass.logTestMessage("Failed to remove geofences... \n" +
                            "$e : ${e.message}")
                }
            }
        } else {
            geofencePendingIntent = createGeofencePendingIntent(viewAdapter.locations)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    geofencingClient.removeGeofences(geofencePendingIntent).await()
                    HelperClass.logTestMessage("Geofences removed")
                } catch (e: java.lang.Exception){
                    HelperClass.logTestMessage("Failed to remove geofences... \n" +
                            "$e : ${e.message}")
                }
            }
        }

    }

    private fun checkLocationRequestSettings() {
        val locationRequest = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(this)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val task = client.checkLocationSettings(builder.build()).await()
                HelperClass.logTestMessage("checkLocationRequestSettings: OK")
            } catch (e: Exception){
                if(e is ResolvableApiException){
                    try {
                        e.startResolutionForResult(this@MainActivity, REQUEST_TURN_DEVICE_LOCATION_ON)
                    } catch (sendEx: IntentSender.SendIntentException){
                        HelperClass.logTestMessage("Error getting location settings resolution: ${sendEx.message}")
                    }
                } else {
                    Snackbar.make(binding.recyclerView, "Location needs to be enabled to use geofence!", Snackbar.LENGTH_LONG)
                        .setAction("OK"){
                            checkLocationRequestSettings()
                        }
                        .show()
                }
            }
        }
    }

    private fun checkLocationPermissions() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            setGeofenceOption(optionMenu.findItem(R.id.geofence_option))
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                LOCATION_PERMISSIONS_REQUEST
            )
        }
    }

    private fun setGeofenceOption(item: MenuItem) {
        if (!item.isChecked) {
            checkLocationRequestSettings()
            val geofenceRequest = createGeofencingRequest(viewAdapter.locations)
            addGeofencesToClient(geofenceRequest)
            mainViewModel.setGeofenceActive(true)
            Toast.makeText(this, "Geofence starts working...", Toast.LENGTH_SHORT).show()
        } else {
            //stopLocationUpdates()
            removeGeofences()
            mainViewModel.setGeofenceActive(false)
            Toast.makeText(this, "Geofence has stopped working.", Toast.LENGTH_SHORT).show()
        }
        item.isChecked = !item.isChecked
    }

    private fun checkGeofence(){
        mainViewModel.checkGeofence()
        mainViewModel.isGeofenceActive.observe(this, Observer {
            HelperClass.logTestMessage("GeofenceActive: $it")
            optionMenu.findItem(R.id.geofence_option).isChecked = it
            if(it){
                checkLocationRequestSettings()
                val geofenceRequest = createGeofencingRequest(viewAdapter.locations)
                addGeofencesToClient(geofenceRequest)
            }
        })
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
            setGeofenceOption(optionMenu.findItem(R.id.geofence_option))
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }


    }

    private fun startLocationsUpdates(locationRequest: LocationRequest){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), ACCESS_FINE_LOCATION_REQUEST)
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun stopLocationUpdates(){
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun initRecyclerView() {
        viewAdapter = MyAdapter(this@MainActivity)
        binding.recyclerView.apply {
            this.adapter = viewAdapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        mainViewModel.getAllLocations()
        mainViewModel.allLocations.observe(this, Observer { locations ->
            viewAdapter.setLocations(locations)
            if(this::optionMenu.isInitialized){
                val geofenceMenuItem = optionMenu.findItem(R.id.geofence_option)
                if(geofenceMenuItem.isChecked){
                    val geofenceRequest = createGeofencingRequest(locations)
                    addGeofencesToClient(geofenceRequest)
                }
            }
        })
    }

    private fun initAddLocationButton() {
        binding.addLocationFAB.setOnClickListener {
            val intent = Intent(this, AddEditLocationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initShowOnMapButton() {
        binding.showOnMapFAB.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putParcelableArrayListExtra(
                SELECTED_LOCATIONS,
                viewAdapter.getSelectedLocations()
            )
            startActivity(intent)
        }
    }

    private fun switchButtons() {
        if (viewAdapter.getCheckBoxFlag()) {
            binding.addLocationFAB.hide()
            binding.showOnMapFAB.show()
        } else {
            binding.addLocationFAB.show()
            binding.showOnMapFAB.hide()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        if (menu != null) {
            optionMenu = menu
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out_button -> {
                Toast.makeText(this, "Logout successful!", Toast.LENGTH_SHORT).show()
                removeGeofences()
                goToLoginActivity()
                finish()
                return true
            }
            R.id.show_selected_on_map_option -> {
                isOnMapCheckBoxActive = if (!isOnMapCheckBoxActive) {
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
                checkLocationPermissions()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                googleSignInClient.signOut().await()
                HelperClass.logTestMessage("googleSignInClient has been signed out")
            } catch (e: Exception){
                HelperClass.logTestMessage("Failed to sign out from google client... \n " +
                        "$e: ${e.message}")
            }
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
        if (viewAdapter.getCheckBoxFlag()) {
            viewAdapter.setCheckBoxFlag(false)
            isOnMapCheckBoxActive = false
            switchButtons()
        } else {
            minimizeApp()
        }
    }

    private fun initSwipeToDelete() {
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val itemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, swipeFlag) {
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
                    Snackbar.make(
                        recyclerView,
                        "Location: ${location.uid} deleted.",
                        Snackbar.LENGTH_SHORT
                    )
                        .setAction("Undo", View.OnClickListener {
                            mainViewModel.undoDeletion(location)
                        })
                        .setActionTextColor(Color.CYAN)
                        .show()
                }
            }).attachToRecyclerView(recyclerView)
    }

    private fun setAnimationForFAB(){
        binding.recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY){
                binding.addLocationFAB.hide()
            } else {
                binding.addLocationFAB.show()
            }
        }
    }

    private fun minimizeApp(){
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }




    companion object {
        internal const val EXTRA_ID = "com.example.memoryroadapp.EXTRA_ID"
        internal const val EXTRA_NAME = "com.example.memoryroadapp.EXTRA_NAME"
        internal const val SELECTED_LOCATIONS = "com.example.memoryroadapp.SELECTED_LOCATIONS"
        internal const val ACTION_GEOFENCE_EVENT = "MainActivity.geofence.action.ACTION_GEOFENCE_EVENT"
        internal const val EXTRA_LOCATIONS = "com.example.memoryroadapp.EXTRA_LOCATIONS"
        internal const val EXTRA_BUNDLE = "com.example.memoryroadapp.EXTRA_BUNDLE"
        private const val LOCATION_PERMISSIONS_REQUEST = 111
        private const val ACCESS_FINE_LOCATION_REQUEST = 123
        private const val REQUEST_TURN_DEVICE_LOCATION_ON = 321
        private const val REQUEST_PENDING_INTENT = 1234
    }


}



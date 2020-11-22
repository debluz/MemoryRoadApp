package com.example.memoryroadapp.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.memoryroadapp.R
import com.example.memoryroadapp.models.MyLocation
import com.example.memoryroadapp.databinding.ActivityMapsBinding
import com.example.memoryroadapp.ui.LocationInfoActivity.Companion.EXTRA_LOCATION
import com.example.memoryroadapp.ui.MainActivity.Companion.SELECTED_LOCATIONS
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var location: MyLocation
    private lateinit var locations: ArrayList<MyLocation>
    private lateinit var currentLocation: Location
    private lateinit var selectedCoordinates: LatLng
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        when {
            intent.hasExtra(EXTRA_LOCATION) -> {
                location = intent.getParcelableExtra(EXTRA_LOCATION)
            }
            intent.hasExtra(SELECTED_LOCATIONS) -> {
                locations = intent.getParcelableArrayListExtra(SELECTED_LOCATIONS)
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        when {
            this::location.isInitialized -> {
                updateMapLocationUI()
            }
            this::locations.isInitialized -> {
                updateMapListLocationsUI()
            }
            else -> {
                updateMapSelectLocationUI()
            }
        }
    }



    private fun updateMapSelectLocationUI(){
        binding.showLocationsLayout.visibility = View.GONE
        binding.selectLocationConstraint.visibility = View.VISIBLE
        getCurrentLocation()
        initOnMapClick()
        initConfirmFAB()
        initCancelFAB()

    }

    private fun getCurrentLocation(){
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_FINE_LOCATION)
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            currentLocation= fusedLocationClient.lastLocation.await()
            selectedCoordinates = LatLng(currentLocation.latitude, currentLocation.longitude)
            goToCurrentLocation()
        }
    }

    private fun updateMapLocationUI(){
        binding.selectLocationConstraint.visibility = View.GONE
        binding.showLocationsLayout.visibility = View.GONE
        val coordinates = LatLng(location.latitude?.toDouble()!!, location.longitude?.toDouble()!!)
        map.addMarker(MarkerOptions().position(coordinates).title(location.name))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 15f))
        map.addCircle(CircleOptions()
            .center(coordinates)
            .radius(location.diameter!!)
            .fillColor(Color.parseColor("#221e88e5"))
            .strokeWidth(5.0F)
            .strokeColor(Color.parseColor("#0d47a1")))
    }

    private fun updateMapListLocationsUI(){
        binding.selectLocationConstraint.visibility = View.GONE
        binding.showLocationsLayout.visibility = View.VISIBLE
        val positions = ArrayList<LatLng>()
        for(location in locations){
            val coordinates = LatLng(location.latitude?.toDouble()!!, location.longitude?.toDouble()!!)
            positions.add(coordinates)
            map.addMarker(MarkerOptions().position(coordinates).title(location.name))
            map.addCircle(CircleOptions()
                .center(coordinates)
                .radius(location.diameter!!)
                .fillColor(Color.parseColor("#221e88e5"))
                .strokeWidth(5.0F)
                .strokeColor(Color.parseColor("#0d47a1")))
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positions[index], 15f))
        initLeftFAB(positions)
        initRightFAB(positions)
    }

    private fun initOnMapClick(){
        map.setOnMapClickListener {
            map.clear()
            map.addMarker(MarkerOptions().position(it).title(this.getString(R.string.selected_location)))
            selectedCoordinates = it
        }
    }

    private fun goToCurrentLocation(){
        map.addMarker(MarkerOptions().position(selectedCoordinates).title(this.getString(R.string.current_location)))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedCoordinates, 10f))
    }

    private fun initLeftFAB(positions: ArrayList<LatLng>){
        binding.leftFAB.setOnClickListener {
            if(index == 0){
                index = positions.size-1
            } else {
                index--
            }
            moveCamera(positions[index])
        }
    }

    private fun initRightFAB(positions: ArrayList<LatLng>){
        binding.rightFAB.setOnClickListener {
            if(index == positions.size-1){
                index = 0
            } else {
                index++
            }
            moveCamera(positions[index])
        }
    }

    private fun initConfirmFAB(){
        binding.confirmFAB.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_LATITUDE, selectedCoordinates.latitude)
            intent.putExtra(EXTRA_LONGITUDE, selectedCoordinates.longitude)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initCancelFAB(){
        binding.cancelFAB.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun moveCamera(target: LatLng){
        val cameraPosition = CameraPosition.Builder()
            .target(target)
            .zoom(15f)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    companion object{
        const val EXTRA_LATITUDE = "com.example.memoryroadapp.EXTRA_LATITUDE"
        const val EXTRA_LONGITUDE = "com.example.memoryroadapp.EXTRA_LONGITUDE"
    }

}

private const val REQUEST_FINE_LOCATION = 111
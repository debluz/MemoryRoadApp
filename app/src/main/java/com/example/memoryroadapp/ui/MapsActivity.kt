package com.example.memoryroadapp.ui

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.databinding.ActivityMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.zip.Inflater
import kotlin.properties.Delegates

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object{
        private const val SELECTED_LOCATIONS = "com.example.memoryroadapp.ui.SELECTED_LOCATIONS"
        private const val EXTRA_LOCATION = "com.example.memoryroadapp.ui.EXTRA_LOCATION"
    }
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var location: MyLocation
    private lateinit var locations: ArrayList<MyLocation>
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if(intent.hasExtra(EXTRA_LOCATION)){
            location = intent.getParcelableExtra(EXTRA_LOCATION)
        } else if(intent.hasExtra(SELECTED_LOCATIONS)){
            locations = intent.getParcelableArrayListExtra(SELECTED_LOCATIONS)
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        when {
            this::location.isInitialized -> {
                binding.leftFAB.visibility = View.GONE
                binding.rightFAB.visibility = View.GONE
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
            this::locations.isInitialized -> {
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
        }
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

    private fun moveCamera(target: LatLng){
        val cameraPosition = CameraPosition.Builder()
            .target(target)
            .zoom(15f)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}
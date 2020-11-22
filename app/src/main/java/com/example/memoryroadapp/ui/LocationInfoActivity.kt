package com.example.memoryroadapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.memoryroadapp.R
import com.example.memoryroadapp.viewmodels.LocationInfoViewModel
import com.example.memoryroadapp.databinding.ActivityLocationInfoBinding
import com.example.memoryroadapp.ui.MainActivity.Companion.EXTRA_ID
import com.example.memoryroadapp.ui.MainActivity.Companion.EXTRA_NAME

class LocationInfoActivity : AppCompatActivity() {
    companion object {
        internal const val EXTRA_LOCATION = "com.example.memoryroadapp.EXTRA_LOCATION"
    }

    private val locationInfoViewModel
            by lazy { ViewModelProvider(this).get(LocationInfoViewModel::class.java) }
    private val binding
            by lazy { DataBindingUtil.setContentView<ActivityLocationInfoBinding>(this, R.layout.activity_location_info) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)

        binding.lifecycleOwner = this
        binding.viewmodel = locationInfoViewModel

        if (intent.hasExtra(EXTRA_ID)) {
            val locationId = intent.getStringExtra(EXTRA_ID)
            title = intent.getStringExtra(EXTRA_NAME)
            locationInfoViewModel.getLocation(locationId)
            locationInfoViewModel.locationLiveData.observe(this, Observer { location ->
                binding.location = location
            })
        }

        initShowOnMapButton()
        setAnimationForFAB()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.location_info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.edit_option) {
            val location = binding?.location
            if (location != null) {
                val intent = Intent(this, AddEditLocationActivity::class.java)
                intent.apply {
                    putExtra(EXTRA_ID, location.uid)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Can't do it right now", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initShowOnMapButton() {
        binding.showOnMapButton.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra(EXTRA_LOCATION, binding.location)
            startActivity(intent)
        }
    }

    private fun setAnimationForFAB(){
        binding.nestedScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY){
                binding.showOnMapButton.hide()
            } else {
                binding.showOnMapButton.show()
            }
        }
    }


}
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
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.viewmodels.LocationInfoViewModel
import com.example.memoryroadapp.databinding.ActivityLocationInfoBinding

 class LocationInfoActivity : AppCompatActivity() {
     private val locationInfoViewModel
             by lazy { ViewModelProvider(this).get(LocationInfoViewModel::class.java) }
     private val binding
             by lazy { DataBindingUtil.setContentView<ActivityLocationInfoBinding>(this, R.layout.activity_location_info) }
     lateinit var location: MyLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)

        binding.lifecycleOwner = this
        binding.viewmodel = locationInfoViewModel

        if(intent.hasExtra(Constants.EXTRA_ID)){
            val locationId = intent.getStringExtra(Constants.EXTRA_ID)
            title = intent.getStringExtra(Constants.EXTRA_NAME)
            locationInfoViewModel.getLocation(locationId)
            locationInfoViewModel.locationLiveData.observe(this, Observer {location ->
                binding.location = location
                this.location = location
            })
        }


    }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.location_info_menu, menu)
         return true
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if(item.itemId == R.id.edit_option){
             if(this::location.isInitialized){
                 val intent = Intent(this, AddEditLocationActivity::class.java)
                 intent.apply {
                     putExtra(Constants.EXTRA_ID, location.uid)
                 }
                 startActivity(intent)
             } else {
                 Toast.makeText(this, "Can't do it right now", Toast.LENGTH_SHORT).show()
             }
             return true
         }
         return super.onOptionsItemSelected(item)
     }



}
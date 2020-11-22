package com.example.memoryroadapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.memoryroadapp.models.MyLocation
import com.example.memoryroadapp.repositories.LocationsRepository

class LocationInfoViewModel : ViewModel(){
    private val locationsRepository = LocationsRepository()
    lateinit var locationLiveData: LiveData<MyLocation>

    lateinit var coordinates : LiveData<String>

    fun getLocation(id: String){
        locationLiveData = locationsRepository.getLocationById(id)
        coordinates = Transformations.map(locationLiveData) { location ->
            "Lat: ${location.latitude} Lng: ${location.longitude}"
        }
    }
}
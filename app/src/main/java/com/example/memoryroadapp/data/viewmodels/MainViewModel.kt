package com.example.memoryroadapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.repositories.LocationsRepository


class MainViewModel: ViewModel(){
    private val locRepository = LocationsRepository()
    private var _eventCode = MutableLiveData<Int>()
    val eventCode = _eventCode
    val allLocations : LiveData<List<MyLocation>>

    init {
        allLocations = locRepository.getAllLocations()
    }

    fun onCreateLocationButtonClick(){
        HelperClass.logTestMessage("working in MainViewModel")
        _eventCode.value = Constants.EC_CREATE_LOCATION
    }

}

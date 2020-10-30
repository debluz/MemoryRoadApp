package com.example.memoryroadapp.data.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.repositories.LocationsRepository
import com.example.memoryroadapp.data.repositories.AuthRepository


class MainViewModel: ViewModel(){
    private val authRepository = AuthRepository()
    private val locRepository = LocationsRepository()
    private var _eventCode = MutableLiveData<Int>()
    val eventCode = _eventCode
    lateinit var allLocations : LiveData<ArrayList<MyLocation>>



    fun getAllLocations() {
        allLocations = locRepository.getAllLocations()
    }


    fun deleteLocation(location: MyLocation){
        locRepository.deleteLocation(location)
    }

    fun undoDeletion(location: MyLocation){
        locRepository.undoDeletion(location)
    }

    fun signOut(){
        authRepository.signOut()
    }

}

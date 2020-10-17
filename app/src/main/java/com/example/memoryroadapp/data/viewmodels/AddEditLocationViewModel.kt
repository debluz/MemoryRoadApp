package com.example.memoryroadapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memoryroadapp.data.repositories.LocationsRepository

class AddEditLocationViewModel : ViewModel(){
    private val locRepository = LocationsRepository()
    val nameEditTextContent = MutableLiveData<String>()
    val descriptionEditTextContent = MutableLiveData<String>()
    val latitudeEditTextContent = MutableLiveData<String>()
    val longitudeEditTextContent = MutableLiveData<String>()


    fun onSelectLocationButtonClick(){

    }

    fun onResetClick(){

    }

    fun onSaveClick(){

    }

}
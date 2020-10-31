package com.example.memoryroadapp.data.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.*
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.repositories.LocationsRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddEditLocationViewModel : ViewModel(){
    private val locRepository = LocationsRepository()
    lateinit var createdLocation: LiveData<MyLocation>
    val nameEditTextContent = MutableLiveData<String>()
    val descriptionEditTextContent = MutableLiveData<String>()
    val latitudeEditTextContent = MutableLiveData<String>()
    val longitudeEditTextContent = MutableLiveData<String>()
    val diameterEditTextContent = MutableLiveData<String>()
    private var _eventCode = MutableLiveData<Int>()
    val eventCode : LiveData<Int> = _eventCode
    lateinit var editedLocation: LiveData<MyLocation>
    private var isNewLocation: Boolean = false
    private val _imageBitmap = MutableLiveData<Bitmap>()
    val imageBitmap : LiveData<Bitmap> = _imageBitmap



    fun onSelectLocationButtonClick(){

    }

    fun onResetClick(){
        nameEditTextContent.value = null
        descriptionEditTextContent.value = null
        latitudeEditTextContent.value = null
        longitudeEditTextContent.value = null
        diameterEditTextContent.value = null
    }

    fun setIsNewLocation(isNew: Boolean){
        isNewLocation = isNew
    }

    fun onSaveClick() = viewModelScope.launch(Dispatchers.IO){
        if(!nameEditTextContent.value.isNullOrEmpty()
            && !descriptionEditTextContent.value.isNullOrEmpty() && !latitudeEditTextContent.value.isNullOrEmpty()
            && !longitudeEditTextContent.value.isNullOrEmpty() && !diameterEditTextContent.value.isNullOrEmpty()) {

            val name = nameEditTextContent.value.toString()
            val description = descriptionEditTextContent.value.toString()
            val latitude = latitudeEditTextContent.value.toString().toFloat()
            val longitude = longitudeEditTextContent.value.toString().toFloat()
            val diameter = diameterEditTextContent.value.toString().toDouble()
            if(isNewLocation) {
                createdLocation = liveData(Dispatchers.IO) {
                    HelperClass.logTestMessage(Thread.currentThread().name+" adding location")
                    val location = locRepository.addLocation(name, description, latitude, longitude, diameter, _imageBitmap.value)
                    emit(location)
                }
                _eventCode.postValue(Constants.EC_ADDED_LOCATION)
            } else {
                val location = MyLocation().apply {
                    this.userId = editedLocation.value?.userId
                    this.name = name.trim()
                    this.description = description.trim()
                    this.latitude = latitude
                    this.longitude = longitude
                    this.uid = editedLocation.value?.uid
                    this.diameter = diameter
                }
                try {
                    locRepository.updateLocation(location, _imageBitmap.value)
                    _eventCode.postValue(Constants.EC_UPDATED_LOCATION)
                } catch (e: FirebaseFirestoreException){
                    HelperClass.logTestMessage("$e ${e.message}")
                }
            }

        } else {
            _eventCode.postValue(Constants.EC_FAIL_ADD_EDIT_LOCATION)
        }

    }

    fun getLocationById(locationId: String){
        editedLocation = locRepository.getLocationById(locationId)
    }

    fun initFields(){
        editedLocation.value?.apply {
            nameEditTextContent.value = this.name
            descriptionEditTextContent.value = this.description
            latitudeEditTextContent.value = this.latitude.toString()
            longitudeEditTextContent.value = this.longitude.toString()
            diameterEditTextContent.value = this.diameter.toString()
        }
    }

    fun uploadImage(image: Bitmap){
        _imageBitmap.value = image
    }

    fun loadImage(){

    }

}
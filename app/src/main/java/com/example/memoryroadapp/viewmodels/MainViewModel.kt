package com.example.memoryroadapp.viewmodels



import androidx.lifecycle.*
import com.example.memoryroadapp.utils.HelperClass
import com.example.memoryroadapp.models.MyLocation
import com.example.memoryroadapp.repositories.LocationsRepository
import com.example.memoryroadapp.repositories.AuthRepository
import com.example.memoryroadapp.repositories.UsersRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val locRepository = LocationsRepository()
    private val usersRepository = UsersRepository()
    private var _isGeofenceActive = MutableLiveData<Boolean>()
    val isGeofenceActive : LiveData<Boolean> = _isGeofenceActive
    lateinit var allLocations: LiveData<ArrayList<MyLocation>>



    fun getAllLocations() {
        allLocations = locRepository.getAllLocations()
    }


    fun deleteLocation(location: MyLocation) = viewModelScope.launch(Dispatchers.IO){
        try {
            locRepository.deleteLocation(location)
        } catch (e: FirebaseFirestoreException){
            HelperClass.logTestMessage("$e ${e.message}")
        }
    }



    fun undoDeletion(location: MyLocation) = viewModelScope.launch(Dispatchers.IO){
        try {
            locRepository.undoDeletion(location)
        } catch (e: FirebaseFirestoreException){
            HelperClass.logTestMessage("$e ${e.message}")
        }

    }

    fun setGeofenceActive(isActive: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        try {
            usersRepository.setGeofenceActive(isActive)
        } catch (e: FirebaseFirestoreException){
            HelperClass.logTestMessage("$e ${e.message}")
        }
    }

    fun checkGeofence() = viewModelScope.launch(Dispatchers.IO) {
        try{
            _isGeofenceActive.postValue(usersRepository.checkGeofence())
        } catch (e: FirebaseFirestoreException){
            HelperClass.logTestMessage("$e ${e.message}")
        }
    }

    fun signOut() {
        authRepository.signOut()
    }

}

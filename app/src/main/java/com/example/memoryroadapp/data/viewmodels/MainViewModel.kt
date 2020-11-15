package com.example.memoryroadapp.data.viewmodels



import androidx.lifecycle.*
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.data.repositories.LocationsRepository
import com.example.memoryroadapp.data.repositories.AuthRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val locRepository = LocationsRepository()
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

    fun signOut() {
        authRepository.signOut()
    }

}

package com.example.memoryroadapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.data.models.MyLocation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class LocationsRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val rootRef = Firebase.firestore
    private val locationsRef = rootRef.collection(Constants.LOCATIONS)

    fun getAllLocations(): MutableLiveData<List<MyLocation>>{
        val newMutableLiveDataList = MutableLiveData<List<MyLocation>>()
        var newList = emptyList<MyLocation>()
        locationsRef.get()
            .addOnCompleteListener {queryTask ->
                if(queryTask.isSuccessful){
                    val documents = queryTask.result
                    documents?.let {
                        newList = documents.toObjects<MyLocation>()
                        newMutableLiveDataList.value = newList
                    }
                }
            }
            .addOnFailureListener {exception ->
                HelperClass.logTestMessage(exception.message)
            }

        return newMutableLiveDataList
    }

    fun addLocation(){

    }

    fun updateLocation(location: MyLocation){

    }

    fun deleteLocation(location: MyLocation){

    }
}
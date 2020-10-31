package com.example.memoryroadapp.data.repositories

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.data.models.MyLocation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList

class LocationsRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val rootRef = Firebase.firestore
    private val locationsRef = rootRef.collection(Constants.LOCATIONS)
    private val storage = Firebase.storage
    private val storageRef = storage.reference
    private val imagesRef = storageRef.child("images")

    fun getAllLocations(): MutableLiveData<ArrayList<MyLocation>>{
        val newMutableLiveDataList = MutableLiveData<ArrayList<MyLocation>>()

        val currentUser = firebaseAuth.currentUser
        val query = locationsRef.whereEqualTo("userId", currentUser?.uid).orderBy("name", Query.Direction.ASCENDING)
        query.addSnapshotListener { value, error ->
            if(error != null){
                HelperClass.logTestMessage("SnapshotListener failed: ${error.message}")
                return@addSnapshotListener
            }


            val uid = UUID.randomUUID()

            if (value != null) {
                /*//Checking changes
                for(change in value.documentChanges){
                    when(change.type){
                        DocumentChange.Type.ADDED -> HelperClass.logTestMessage("Document added: ${change.document.toObject(MyLocation::class.java)}")
                        DocumentChange.Type.MODIFIED -> HelperClass.logTestMessage("Document modified: ${change.document.toObject(MyLocation::class.java)}")
                        DocumentChange.Type.REMOVED -> HelperClass.logTestMessage("Document removed: ${change.document.toObject(MyLocation::class.java)}")
                    }
                }*/

                val locations = ArrayList<MyLocation>()
                for (doc in value){
                    val location = doc.toObject(MyLocation::class.java)
                    locations.add(location)
                }
                newMutableLiveDataList.value = locations
            }
        }
        return newMutableLiveDataList
    }

    fun getLocationById(id: String): MutableLiveData<MyLocation>{
        val locationMutableLiveData = MutableLiveData<MyLocation>()
        val docRef = locationsRef.document(id)
        docRef.addSnapshotListener { snapshot, error ->
            if(error != null){
                HelperClass.logTestMessage(error.message)
                return@addSnapshotListener
            }

            if(snapshot != null && snapshot.exists()){
                locationMutableLiveData.value = snapshot.toObject(MyLocation::class.java)
            } else {
                HelperClass.logTestMessage("getLocationById: Current data is null")
            }
        }
        return locationMutableLiveData
    }

    suspend fun addLocation(name: String, description: String, latitude: Float, longitude: Float, diameter: Double, imageBitmap: Bitmap?): MutableLiveData<MyLocation>{
        val newLocationMutableLiveData = MutableLiveData<MyLocation>()
        val imageName: String = uploadImage("", imageBitmap, firebaseAuth.currentUser?.uid!!).toString()
        val locationId = UUID.randomUUID().toString()
        val location = if(imageName != null){
            MyLocation(firebaseAuth.currentUser?.uid, name, longitude, latitude, diameter, description, locationId, imageName)
        } else {
            MyLocation(firebaseAuth.currentUser?.uid, name, longitude, latitude, diameter, description, locationId)
        }
        locationsRef.document(locationId).set(location)
            .addOnCompleteListener {creationTask ->
                HelperClass.logTestMessage("add location:"+Thread.currentThread().name)
                if(creationTask.isSuccessful){
                    location.isCreated = true
                    newLocationMutableLiveData.value = location
                } else {
                    HelperClass.logTestMessage("CreationTask (Location): ${creationTask.exception?.message}")
                    newLocationMutableLiveData.value = location
                }
            }
        return newLocationMutableLiveData
    }

    suspend fun updateLocation(location: MyLocation, imageBitmap: Bitmap?){
        val snapshot = locationsRef.document(location.uid!!).get().await()
        val temp = snapshot?.toObject<MyLocation>()
        val imageName: String = uploadImage(temp?.imageName, imageBitmap, firebaseAuth.currentUser?.uid!!).toString()
        location.imageName = imageName
        locationsRef.document(location.uid.toString()).set(location, SetOptions.merge()).await()

    }

    suspend fun deleteLocation(location: MyLocation){
        locationsRef.document(location.uid.toString()).delete().await()

    }

    suspend fun undoDeletion(location: MyLocation) {
        locationsRef.document(location.uid.toString()).set(location).await()
    }



    private suspend fun uploadImage(currentImage: String?, imageBitmap: Bitmap?, userId: String): String? {
        if(currentImage != null){
            val currentImageRef = imagesRef.child(userId).child(currentImage)
            currentImageRef.delete().await()
        }

        if(imageBitmap == null){
            return null
        } else {
            val imageName = "${UUID.randomUUID()}.jpg"
            val imageRef = imagesRef.child(userId).child(imageName)
            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val uploadTask = imageRef.putBytes(data).await()
            if(uploadTask.error != null){
                HelperClass.logTestMessage(uploadTask.error!!.message)
            }
            return imageName
        }
    }
}
package com.example.memoryroadapp.repositories

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.example.memoryroadapp.utils.HelperClass
import com.example.memoryroadapp.models.MyLocation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList


class LocationsRepository {
    companion object {
        const val LOCATIONS: String = "locations"
        const val IMAGES: String = "images"
    }

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val rootRef = Firebase.firestore
    private val locationsRef = rootRef.collection(LOCATIONS)
    private val storage = Firebase.storage
    private val storageRef = storage.reference
    private val imagesRef = storageRef.child(IMAGES)

    fun getAllLocations(): MutableLiveData<ArrayList<MyLocation>> {
        val newMutableLiveDataList = MutableLiveData<ArrayList<MyLocation>>()

        val currentUser = firebaseAuth.currentUser
        val query = locationsRef.whereEqualTo("userId", currentUser?.uid)
            .orderBy("name", Query.Direction.ASCENDING)
        query.addSnapshotListener { value, error ->
            if (error != null) {
                HelperClass.logTestMessage("SnapshotListener failed: ${error.message}")
                return@addSnapshotListener
            }

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
                for (doc in value) {
                    val location = doc.toObject(MyLocation::class.java)
                    locations.add(location)
                }
                newMutableLiveDataList.value = locations
            }
        }
        return newMutableLiveDataList
    }

    fun getLocationById(id: String): MutableLiveData<MyLocation> {
        val locationMutableLiveData = MutableLiveData<MyLocation>()
        val docRef = locationsRef.document(id)
        docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                HelperClass.logTestMessage(error.message)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                locationMutableLiveData.value = snapshot.toObject(MyLocation::class.java)
            } else {
                HelperClass.logTestMessage("getLocationById: Current data is null")
            }
        }
        return locationMutableLiveData
    }

    suspend fun addLocation(
        name: String,
        description: String,
        latitude: Float,
        longitude: Float,
        diameter: Double,
        imageBitmap: Bitmap?
    ): MyLocation {
        val location = MyLocation(firebaseAuth.currentUser?.uid, name, longitude, latitude, diameter, description)
        val newImageName = if (imageBitmap != null) {
            UUID.randomUUID().toString()
        } else {
            ""
        }
        val imageUrl = uploadImage(location, newImageName, imageBitmap, firebaseAuth.currentUser?.uid!!)
        val locationRef = locationsRef.add(location).await()
        location.apply {
            uid = locationRef.id
            this.imageUrl = imageUrl ?: ""
            imageName = newImageName
        }
        locationsRef.document(locationRef.id).set(location).await()

        location.isCreated = true
        return location
    }

    suspend fun updateLocation(location: MyLocation, imageBitmap: Bitmap?) {
        val snapshot = locationsRef.document(location.uid!!).get().await()
        val tempLocation = snapshot?.toObject<MyLocation>()
        val newImageName = if (imageBitmap != null) {
            UUID.randomUUID().toString()
        } else {
            location.imageName!!
        }
        val imageUrl = uploadImage(
            tempLocation!!,
            newImageName,
            imageBitmap,
            firebaseAuth.currentUser?.uid!!
        )
        location.imageUrl = imageUrl ?: ""
        location.imageName = newImageName
        locationsRef.document(location.uid!!).set(location, SetOptions.merge()).await()
    }

    suspend fun deleteLocation(location: MyLocation) {
        locationsRef.document(location.uid!!).delete().await()
    }

    suspend fun undoDeletion(location: MyLocation) {
        locationsRef.document(location.uid!!).set(location).await()
    }


    private suspend fun uploadImage(
        location: MyLocation,
        newImageName: String,
        imageBitmap: Bitmap?,
        userId: String
    ): String? {
        return if (imageBitmap == null && location.imageUrl.isNullOrEmpty()) {
            null
        }else if (imageBitmap == null && !location.imageUrl.isNullOrEmpty()){
            location.imageUrl
        } else {
            val imageRef = imagesRef.child(userId).child(newImageName)
            val baos = ByteArrayOutputStream()
            imageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            val uploadTask = imageRef.putBytes(data)
            val imageUrl = uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                imageRef.downloadUrl
            }.await()

            val currentImageName = location.imageName
            if (!currentImageName.isNullOrEmpty()) {
                val currentImageRef = imagesRef.child(userId).child(currentImageName)
                currentImageRef.delete().await()
            }

            imageUrl.toString()
        }
    }
}
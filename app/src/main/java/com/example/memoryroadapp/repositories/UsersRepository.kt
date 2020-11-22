package com.example.memoryroadapp.repositories

import com.example.memoryroadapp.User
import com.example.memoryroadapp.utils.HelperClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UsersRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef = Firebase.firestore
    private val usersRef: CollectionReference = rootRef.collection(AuthRepository.USERS)

    suspend fun setGeofenceActive(isActive: Boolean){
        val userUid = firebaseAuth.currentUser?.uid
        if(userUid != null){
            val userRef = usersRef.document(userUid)
            val snaphot = userRef.get().await()
            val tempUser = snaphot?.toObject<User>()
            tempUser?.let {
                tempUser.isGeofenceActive = isActive
                userRef.set(tempUser, SetOptions.merge()).await()
            }
        }
    }

    suspend fun checkGeofence(): Boolean{
        val userUid = firebaseAuth.currentUser?.uid
        val userRef = usersRef.document(userUid!!)
        val snaphot = userRef?.get()?.await()
        val tempUser = snaphot?.toObject<User>()
        val boolean = tempUser?.isGeofenceActive
        return boolean!!
    }



}
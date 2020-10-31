package com.example.memoryroadapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.memoryroadapp.Constants
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = Firebase.firestore
    private val usersRef: CollectionReference = rootRef.collection(Constants.USERS)



    suspend fun firebaseSignInWithEmail(email: String, password: String): User{
        val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val uid = authResult.user?.uid

        val userSnapshot = uid?.let { usersRef.document(it).get().await() }
        val user = userSnapshot?.toObject<User>()!!
        user.isAuthenticated = true

        return user
    }

    suspend fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): User {
        val authResult = firebaseAuth.signInWithCredential(googleAuthCredential).await()
        val isNewUser = authResult.additionalUserInfo?.isNewUser
        val user = User()
        user.isNew = isNewUser

        return user
    }

    suspend fun createUserInFirestoreIfNotExists(authenticatedUser: User): User{
        val userRef = usersRef.document(authenticatedUser.uid)
        val snapshot = userRef.get().await()
        if(!snapshot.exists()){
            userRef.set(authenticatedUser).await()
            authenticatedUser.isCreated = true
        }

        return authenticatedUser
    }

    suspend fun createUserWithEmail(email: String, password: String, name: String): User{
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        } catch (e: FirebaseFirestoreException){
            HelperClass.logTestMessage(e.message)
        }

        val currentUser = firebaseAuth.currentUser
        val user = User(currentUser?.uid!!, email, name)
        usersRef.document(currentUser.uid).set(user)
        user.isCreated = true
        signOut()

        return user
    }

    fun getCurrentUser(): MutableLiveData<FirebaseUser> {
        val userMutableLiveData = MutableLiveData<FirebaseUser>()
        userMutableLiveData.value = firebaseAuth.currentUser
        return  userMutableLiveData
    }

    fun signOut(){
        firebaseAuth.signOut()
    }


}
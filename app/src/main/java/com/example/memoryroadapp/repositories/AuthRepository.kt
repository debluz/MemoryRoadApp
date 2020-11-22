package com.example.memoryroadapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.memoryroadapp.utils.HelperClass
import com.example.memoryroadapp.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepository {
    companion object{
        const val USER: String = "user"
        const val USERS: String = "users"
    }
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = Firebase.firestore
    private val usersRef: CollectionReference = rootRef.collection(USERS)



    suspend fun firebaseSignInWithEmail(email: String, password: String): User {
        val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val uid = authResult.user?.uid
        val userSnapshot = usersRef.document(uid!!).get().await()
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
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val currentUser = firebaseAuth.currentUser
        val user = User(currentUser?.uid!!, email, name)
        usersRef.document(currentUser.uid).set(user)
        user.isCreated = true
        signOut()

        return user
    }

    fun checkIfUserIsAuthenticated(): MutableLiveData<User> {
        val userMutableLiveData = MutableLiveData<User>()
        val user = User()
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            user.isAuthenticated = false
            userMutableLiveData.value = user
        } else {
            user.isAuthenticated = true
            userMutableLiveData.value = user
        }
        return  userMutableLiveData
    }

    fun signOut(){
        firebaseAuth.signOut()
    }


}
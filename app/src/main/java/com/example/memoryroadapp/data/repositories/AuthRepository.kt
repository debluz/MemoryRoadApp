package com.example.memoryroadapp.data.repositories

import androidx.lifecycle.LiveData
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
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = Firebase.firestore
    private val usersRef: CollectionReference = rootRef.collection(Constants.USERS)

    suspend fun firebaseSignInWithEmail(email: String, password: String): User{
        val snapshot = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val uid = snapshot.user?.uid

        val userSnapshot = uid?.let { usersRef.document(it).get().await() }
        val user = userSnapshot?.toObject<User>()!!
        user.isAuthenticated = true

        return user
    }

    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<User> {
        val authenticatedUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.signInWithCredential(googleAuthCredential)
            .addOnCompleteListener{authTask ->
                if(authTask.isSuccessful){
                    val isNewUser: Boolean? = authTask.result?.additionalUserInfo?.isNewUser
                    val firebaseUser = firebaseAuth.currentUser
                    firebaseUser?.let {
                        var uid = firebaseUser.uid
                        var name = firebaseUser.displayName
                        var email = firebaseUser.email
                        val user = User(uid, email, name)
                        user.isNew = isNewUser
                        authenticatedUserMutableLiveData.value = user
                    }
                } else {
                    HelperClass.logErrorMessage(authTask.exception?.message)
                }
            }
        return authenticatedUserMutableLiveData
    }

    fun createUserInFirestoreIfNotExists(authenticatedUser: User): MutableLiveData<User>{
        val newUserMutableLiveData = MutableLiveData<User>()
        val uidRef = usersRef.document(authenticatedUser.uid)
        uidRef.get()
            .addOnCompleteListener {uidTask ->
                if(uidTask.isSuccessful){
                    val document = uidTask.result
                    if(!document?.exists()!!){
                        uidRef.set(authenticatedUser)
                            .addOnCompleteListener { userCreationTask ->
                                if(userCreationTask.isSuccessful){
                                    authenticatedUser.isCreated = true
                                    newUserMutableLiveData.value = authenticatedUser
                                } else {
                                    HelperClass.logErrorMessage(userCreationTask.exception?.message)
                                }
                            }
                    } else {
                        newUserMutableLiveData.value = authenticatedUser
                    }
                } else {
                    HelperClass.logErrorMessage(uidTask.exception?.message)
                }
            }
        return newUserMutableLiveData
    }

    fun createUserWithEmail(email: String, password: String, name: String): MutableLiveData<User> {
        val newUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val currentUser = firebaseAuth.currentUser
                    if (currentUser != null) {
                        val user = User(currentUser.uid, email, name)
                        usersRef.document(currentUser.uid).set(user)
                            .addOnCompleteListener { userCreationTask ->
                                if(userCreationTask.isSuccessful){
                                    user.isCreated = true
                                    newUserMutableLiveData.value = user
                                    signOut()
                                } else {
                                    HelperClass.logErrorMessage(userCreationTask.exception?.message)
                                }
                            }
                    }
                } else {
                    HelperClass.logErrorMessage(authTask.exception?.message)
                    newUserMutableLiveData.value = User()
                }
            }
        return newUserMutableLiveData
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
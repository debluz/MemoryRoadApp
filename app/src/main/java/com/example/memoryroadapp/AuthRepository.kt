package com.example.memoryroadapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val usersRef: CollectionReference = rootRef.collection(Constants.USERS)

    fun firebaseSignInWithEmail(email: String, password: String): MutableLiveData<User>{
        val authenticatedUser = MutableLiveData<User>()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {authTask ->
                if(authTask.isSuccessful){
                    val firebaseUser = firebaseAuth.currentUser

                }
            }
        return authenticatedUser
    }

    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<User> {
        val authenticatedUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.signInWithCredential(googleAuthCredential)
            .addOnCompleteListener{authTask ->
                if(authTask.isSuccessful){
                    val isNewUser: Boolean? = authTask.result?.additionalUserInfo?.isNewUser
                    HelperClass.logErrorMessage("firebaseSignInWithGoogle: 1 - $isNewUser")
                    val firebaseUser = firebaseAuth.currentUser
                    firebaseUser?.let {
                        var uid = firebaseUser.uid
                        var name = firebaseUser.displayName
                        var email = firebaseUser.email
                        val user = User(uid, email, name)
                        HelperClass.logErrorMessage("firebaseSignInWithGoogle: 2 - $uid, $name, $email")
                        user.isNew = isNewUser
                        HelperClass.logErrorMessage("firebaseSignInWithGoogle: 3 - ${user.isNew}")
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
                                    HelperClass.logErrorMessage("createUserInFirestoreIfNotExists: 1")
                                    authenticatedUser.isCreated = true
                                    newUserMutableLiveData.value = authenticatedUser
                                } else {
                                    HelperClass.logErrorMessage(userCreationTask.exception?.message)
                                }
                            }
                    } else {
                        HelperClass.logErrorMessage("createUserInFirestoreIfNotExists: 2")
                        newUserMutableLiveData.value = authenticatedUser
                    }
                } else {
                    HelperClass.logErrorMessage(uidTask.exception?.message)
                }
            }
        return newUserMutableLiveData
    }

}
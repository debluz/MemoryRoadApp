package com.example.memoryroadapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.memoryroadapp.util.Constants
import com.example.memoryroadapp.util.HelperClass
import com.example.memoryroadapp.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val usersRef: CollectionReference = rootRef.collection(Constants.USERS)

    fun firebaseSignInWithEmail(email: String, password: String): MutableLiveData<User>{
        val authenticatedUserMutableLiveData = MutableLiveData<User>()
        var user: User? = User()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {authTask ->
                if(authTask.isSuccessful){
                    HelperClass.logErrorMessage("firebaseSignInWithEmail: success")
                    var uid = firebaseAuth.currentUser!!.uid
                    val uidRef = usersRef.document(uid)
                    uidRef.get()
                        .addOnSuccessListener {foundUser ->
                            if(foundUser != null){
                                user = foundUser.toObject<User>()
                                user?.let {
                                    it.isAuthenticated = true
                                }
                                authenticatedUserMutableLiveData.value = user
                                HelperClass.logErrorMessage("firebaseSignInWithEmail: 1")
                            } else {
                                HelperClass.logErrorMessage("firebaseSignInWithEmail: 2")
                            }
                        }
                } else {
                    HelperClass.logErrorMessage("${authenticatedUserMutableLiveData.value} :" + authTask.exception?.message)
                    authenticatedUserMutableLiveData.value = user
                }
            }
        return authenticatedUserMutableLiveData
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

    fun createUserWithEmail(email: String, password: String, name: String): MutableLiveData<User> {
        val newUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val currentUser = firebaseAuth.currentUser
                    HelperClass.logErrorMessage("createUserWithEmail: 2 - $currentUser")
                    HelperClass.logErrorMessage("createUserWithEmail: success")
                    HelperClass.logErrorMessage("createUserWithEmail: ${currentUser?.uid}")
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
                            /*.addOnSuccessListener {
                                HelperClass.logErrorMessage("New user: $user has been added to Firestore")
                                signOut()
                            }
                            .addOnFailureListener {
                                HelperClass.logErrorMessage("createUserWithEmail: ${it.message}")
                            }*/
                    }
                } else {
                    HelperClass.logErrorMessage(authTask.exception?.message)
                    newUserMutableLiveData.value = User()
                }
            }
        return newUserMutableLiveData
    }

    fun signOut(){
        firebaseAuth.signOut()
    }
}
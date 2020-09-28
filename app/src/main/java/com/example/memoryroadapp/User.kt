package com.example.memoryroadapp

import com.google.firebase.firestore.Exclude

data class User(
    var uid: String,
    var email: String? = null,
    var name: String? = null,
    @get:Exclude var isAuthenticated: Boolean? = false,
    @get:Exclude var isNew: Boolean? = false,
    @get:Exclude var isCreated: Boolean? = false
){
}
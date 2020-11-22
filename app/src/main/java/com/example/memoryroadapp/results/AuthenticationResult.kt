package com.example.memoryroadapp.results

import java.lang.Exception

sealed class AuthenticationResult {
    object Success : AuthenticationResult()
    sealed class Error(private val exception: Exception) : AuthenticationResult(){
        override fun toString(): String {
            return "${exception.message.toString()}"
        }
        class EmptyRequiredFields(exception: Exception) : Error(exception){
            override fun toString(): String {
                return "Email and password are required."
            }
        }
        class InvalidCredentials(exception: Exception) : Error(exception)
        class FirestoreError(exception: Exception) : Error(exception)
        class FirebaseAuthError(exception: Exception) : Error(exception)
    }
}
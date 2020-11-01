package com.example.memoryroadapp.util

import java.lang.Error
import java.lang.Exception

sealed class LocationResult {
    sealed class Success(val name: String) : LocationResult() {
        class Added(name: String) : Success(name) {
            override fun toString(): String {
                return "Location $name has been added."
            }
        }
        class Updated(name: String) : Success(name) {
            override fun toString(): String {
                return "Location $name has been updated."
            }
        }
    }
    sealed class Error(private val exception: Exception) : LocationResult() {
        override fun toString(): String {
            return "${exception.message}"
        }
        class FirestoreError(exception: Exception) : Error(exception)
        class EmptyRequiredFields(exception: Exception) : Error(exception){
            override fun toString(): String {
                return "Fields cannot be empty."
            }
        }
    }
}
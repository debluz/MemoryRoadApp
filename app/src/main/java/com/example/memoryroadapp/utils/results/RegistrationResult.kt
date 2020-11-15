package com.example.memoryroadapp.utils.results

import java.lang.Exception

sealed class RegistrationResult {
    object Success : RegistrationResult()
    sealed class Error(private val exception: Exception) : RegistrationResult() {
        override fun toString(): String {
            return "${exception.message.toString()}"
        }
        class EmailAlreadyInUse(exception: Exception) : Error(exception)
        class SomethingWentWrong(exception: Exception) : Error(exception){
            override fun toString(): String {
                return "Something went wrong"
            }
        }
    }
}
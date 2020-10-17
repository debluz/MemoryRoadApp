package com.example.memoryroadapp.data.models

data class MyLocation(
    var uid: String?,
    val userId: String,
    var name: String,
    var longitude: Float,
    var latitude: Float,
    var diameter: Double,
    var description: String?
) {
}
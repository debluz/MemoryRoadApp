package com.example.memoryroadapp.util

import com.example.memoryroadapp.data.models.MyLocation
import com.google.android.gms.location.Geofence

class GeofenceUtil(private val locations: ArrayList<MyLocation>) {
    val geofenceList = ArrayList<Geofence>()

    init {
        for(location in locations){
            val geofence = Geofence.Builder()
                .setRequestId(location.uid)
                .setCircularRegion(location.latitude?.toDouble()!!, location.longitude?.toDouble()!!, location.diameter?.toFloat()!!)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
            geofenceList.add(geofence)
        }
    }
}
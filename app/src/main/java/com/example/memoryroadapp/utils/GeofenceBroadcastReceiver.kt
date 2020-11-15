package com.example.memoryroadapp.utils

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.data.models.MyLocation
import com.example.memoryroadapp.ui.MainActivity.Companion.ACTION_GEOFENCE_EVENT
import com.example.memoryroadapp.ui.MainActivity.Companion.EXTRA_LOCATIONS
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    private lateinit var locations: ArrayList<MyLocation>


    override fun onReceive(context: Context, intent: Intent?) {
        if(intent?.action == ACTION_GEOFENCE_EVENT){
            val geofencingEvent = GeofencingEvent.fromIntent(intent)
            if(geofencingEvent.hasError()) {
                val errorMessage = GeofenceStatusCodes
                    .getStatusCodeString(geofencingEvent.errorCode)
                HelperClass.logTestMessage("GeofenceBroadcastReceiver: $errorMessage")
                return
            }

            // Get the transition type.
            val geofenceTransition = geofencingEvent.geofenceTransition

            // Test that the reported transition was of interest.
            if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {



                // Get the geofences that were triggered. A single event can trigger
                // multiple geofences.
                val triggeringGeofences = geofencingEvent.triggeringGeofences
                if(triggeringGeofences.isNotEmpty()){
                    for((index, geofence) in triggeringGeofences.withIndex()){
                        HelperClass.logTestMessage("triggeringGeofences $index: $geofence")
                    }
                }
                val foundId = triggeringGeofences[0].requestId

                // Get the transition details as a String.
                when(geofenceTransition){
                    Geofence.GEOFENCE_TRANSITION_ENTER -> HelperClass.logTestMessage("geofenceTransition: Entered")
                    Geofence.GEOFENCE_TRANSITION_EXIT -> HelperClass.logTestMessage("geofenceTransition: Exited")
                }

                val notificationManager = ContextCompat.getSystemService(
                    context,
                    NotificationManager::class.java
                ) as NotificationManager

                notificationManager.sendGeofenceEnteredNotification(
                    context, foundId, geofenceTransition
                )
            }
        }
    }

    /*override fun onReceive(context: Context, intent: Intent?) {
        if(intent?.action == ACTION_GEOFENCE_EVENT){
            if(intent.hasExtra("test")){
                locations = intent.getParcelableArrayListExtra(EXTRA_LOCATIONS)
                HelperClass.logTestMessage("GeofenceBroadcastReceiver - Locations size:${locations.size}")
            } else {
                HelperClass.logTestMessage("GeofenceBroadcastReceiver - Locations size: no locations received")
            }
            val geofencingEvent = GeofencingEvent.fromIntent(intent)
            if(geofencingEvent.hasError()) {
                val errorMessage = GeofenceStatusCodes
                    .getStatusCodeString(geofencingEvent.errorCode)
                HelperClass.logTestMessage("GeofenceBroadcastReceiver: $errorMessage")
                return
            }

            // Get the transition type.
            val geofenceTransition = geofencingEvent.geofenceTransition

            // Test that the reported transition was of interest.
            if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

                val notificationManager = ContextCompat.getSystemService(
                    context,
                    NotificationManager::class.java
                ) as NotificationManager

                // Get the geofences that were triggered. A single event can trigger
                // multiple geofences.
                val triggeringGeofences = geofencingEvent.triggeringGeofences
                if(triggeringGeofences.isNotEmpty() && triggeringGeofences.size == 1){
                    val fenceId = triggeringGeofences[0].requestId
                    val foundLocationIndex = locations.indexOfFirst {location ->
                        location.uid == fenceId
                    }
                    if(-1 == foundLocationIndex){
                        HelperClass.logTestMessage("Unknown Geofence: $fenceId")
                        return
                    }
                    notificationManager.sendGeofenceEnteredNotification(
                        context, locations[foundLocationIndex], geofenceTransition
                    )
                } else if (triggeringGeofences.isNotEmpty() && triggeringGeofences.size > 1){
                    for ((index, geofence) in triggeringGeofences.withIndex()){
                        HelperClass.logTestMessage("triggeringGeofences $index: $geofence")
                    }
                }

                // Get the transition details as a String.
                when(geofenceTransition){
                    Geofence.GEOFENCE_TRANSITION_ENTER -> HelperClass.logTestMessage("geofenceTransition: Entered")
                    Geofence.GEOFENCE_TRANSITION_EXIT -> HelperClass.logTestMessage("geofenceTransition: Exited")
                }

            }
        }
    }*/


}
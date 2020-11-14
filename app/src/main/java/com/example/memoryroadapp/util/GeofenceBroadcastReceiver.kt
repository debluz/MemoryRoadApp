package com.example.memoryroadapp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.memoryroadapp.HelperClass
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        HelperClass.logTestMessage("GeofenceBroadcastReceiver: 0")
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
            HelperClass.logTestMessage("GeofenceBroadcastReceiver: 1")
            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            val triggeringGeofences = geofencingEvent.triggeringGeofences

            // Get the transition details as a String.
            HelperClass.logTestMessage("geofenceTransition: $geofenceTransition \n trigerringGeofences: $triggeringGeofences")
        } else {
            HelperClass.logTestMessage("GeofenceBroadcastReceiver: Error?")
        }
    }


}
package com.example.memoryroadapp.utils

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.memoryroadapp.models.MyLocation
import com.example.memoryroadapp.ui.MainActivity.Companion.ACTION_GEOFENCE_EVENT
import com.example.memoryroadapp.ui.MainActivity.Companion.EXTRA_BUNDLE
import com.example.memoryroadapp.ui.MainActivity.Companion.EXTRA_LOCATIONS
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    lateinit var locations: ArrayList<MyLocation>

    override fun onReceive(context: Context, intent: Intent?) {
        if(intent?.action == ACTION_GEOFENCE_EVENT){
            if(intent.hasExtra(EXTRA_BUNDLE)){
                val bundle = intent.getBundleExtra(EXTRA_BUNDLE)
                locations = bundle.getParcelableArrayList(EXTRA_LOCATIONS)!!
            }
            val geofencingEvent = GeofencingEvent.fromIntent(intent)
            if(geofencingEvent.hasError()) {
                val errorMessage = GeofenceStatusCodes
                    .getStatusCodeString(geofencingEvent.errorCode)
                return
            }

            val geofenceTransition = geofencingEvent.geofenceTransition

            if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

                val notificationManager = ContextCompat.getSystemService(
                    context,
                    NotificationManager::class.java
                ) as NotificationManager

                val triggeringGeofences = geofencingEvent.triggeringGeofences
                if(triggeringGeofences.isNotEmpty() && triggeringGeofences.size == 1){
                    HelperClass.logTestMessage("triggeringGeofences ${triggeringGeofences.size-1}: ${triggeringGeofences[0]}")
                    val fenceId = triggeringGeofences[0].requestId
                    val foundLocationIndex = locations.indexOfFirst {location ->
                        location.uid == fenceId
                    }
                    if(-1 == foundLocationIndex){
                        HelperClass.logTestMessage("Unknown Geofence: $fenceId")
                        return
                    }
                    if(this::locations.isInitialized){
                        val uri = Uri.parse(locations[foundLocationIndex].imageUrl)
                        val futureTarget = Glide.with(context)
                            .asBitmap()
                            .load(uri)
                            .submit()
                        CoroutineScope(Dispatchers.IO).launch {
                            val locationImage = futureTarget.get()
                            notificationManager.sendGeofenceEnteredNotification(
                                context,
                                locations[foundLocationIndex],
                                geofenceTransition,
                                locationImage
                            )
                        }
                    }
                } else if (triggeringGeofences.isNotEmpty() && triggeringGeofences.size > 1){
                    for ((index, geofence) in triggeringGeofences.withIndex()){
                        HelperClass.logTestMessage("triggeringGeofences $index: $geofence")
                    }
                }

                when(geofenceTransition){
                    Geofence.GEOFENCE_TRANSITION_ENTER -> HelperClass.logTestMessage("geofenceTransition: Entered")
                    Geofence.GEOFENCE_TRANSITION_EXIT -> HelperClass.logTestMessage("geofenceTransition: Exited")
                }

            }
        }
    }


}
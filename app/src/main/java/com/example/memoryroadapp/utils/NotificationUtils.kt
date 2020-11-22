package com.example.memoryroadapp.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.memoryroadapp.R
import com.example.memoryroadapp.models.MyLocation
import com.example.memoryroadapp.ui.MainActivity
import com.google.android.gms.location.Geofence

fun createNotificationChannel(context: Context){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
            .apply {
                setShowBadge(true)
            }

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = context.getString(R.string.channel_description)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)

    }
}

fun NotificationManager.sendGeofenceEnteredNotification(context: Context, location: MyLocation, geofenceTransition: Int, locationImage: Bitmap){
    val contentIntent = Intent(context, MainActivity::class.java)
    contentIntent.putExtra(GeofenceConstants.EXTRA_GEOFENCE_ID, location.uid)
    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(context.getString(R.string.app_name))
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(contentPendingIntent)
        .setSmallIcon(R.drawable.ic_baseline_map_24)
    when(geofenceTransition){
        Geofence.GEOFENCE_TRANSITION_ENTER -> {
            val contentText = "${context.getString(R.string.arriving_at_text)} " +
                    "${location.name} " +
                    "${context.getString(R.string.suffix_text)}"
            builder.setContentText(contentText)
            builder.setStyle(NotificationCompat.BigTextStyle()
                .bigText(contentText)
            )
            builder.setLargeIcon(locationImage)
            builder.setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(locationImage)
                .bigLargeIcon(null)
            )
        }
        Geofence.GEOFENCE_TRANSITION_EXIT -> {
            val contentText = "${context.getString(R.string.leaving_from_text)} " +
                    "${location.name} " +
                    "${context.getString(R.string.suffix_text)}"
            builder.setContentText(contentText)
            builder.setStyle(NotificationCompat.BigTextStyle()
                .bigText(contentText))
        }
    }

    notify(NOTIFICATION_ID, builder.build())
}


private const val NOTIFICATION_ID = 333
private const val CHANNEL_ID = "GeofenceNotificationChannel"
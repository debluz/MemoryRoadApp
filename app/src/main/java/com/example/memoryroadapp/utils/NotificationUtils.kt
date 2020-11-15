package com.example.memoryroadapp.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.memoryroadapp.HelperClass
import com.example.memoryroadapp.R
import com.example.memoryroadapp.data.models.MyLocation
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
                setShowBadge(false)
            }

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = context.getString(R.string.channel_description)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)

    }
}

/*fun NotificationManager.sendGeofenceEnteredNotification(context: Context, location: MyLocation, geofenceTransition: Int){
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
    when(geofenceTransition){
        Geofence.GEOFENCE_TRANSITION_ENTER -> builder.setContentText("You've just arrived at ${location.name} from you MemoryRoad.")
        Geofence.GEOFENCE_TRANSITION_EXIT -> builder.setContentText("You've just left ${location.name} from you MemoryRoad.")
    }

    if (!location.imageUrl.isNullOrEmpty()) {
        val futureTarget = Glide.with(context)
            .asBitmap()
            .load(location.imageUrl)
            .submit()

        val locationImage = futureTarget.get()
        val bigPicStyle = NotificationCompat.BigPictureStyle()
            .bigPicture(locationImage)
            .bigLargeIcon(null)
        builder.setLargeIcon(locationImage)
        Glide.with(context).clear(futureTarget)
        HelperClass.logTestMessage("NotificationUtils: 1")
    }

    HelperClass.logTestMessage("NotificationUtils: 2")

    notify(NOTIFICATION_ID, builder.build())
}*/

fun NotificationManager.sendGeofenceEnteredNotification(context: Context, foundId: String, geofenceTransition: Int){
    val contentIntent = Intent(context, MainActivity::class.java)

    val contentPendingIntent = PendingIntent.getActivity(
        context,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle(context.getString(R.string.app_name))
        .setContentText("You've just arrived at $foundId from you MemoryRoad.")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(contentPendingIntent)
        .setSmallIcon(R.drawable.ic_baseline_map_24)

    /*when(geofenceTransition){
        Geofence.GEOFENCE_TRANSITION_ENTER -> builder.setContentText("You've just arrived at $foundId from you MemoryRoad.")
        Geofence.GEOFENCE_TRANSITION_EXIT -> builder.setContentText("You've just left $foundId from you MemoryRoad.")
    }*/

    notify(NOTIFICATION_ID, builder.build())
}


private const val NOTIFICATION_ID = 333
private const val CHANNEL_ID = "GeofenceNotificationChannel"
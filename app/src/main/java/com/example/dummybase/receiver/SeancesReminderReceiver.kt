package com.example.dummybase.receiver;

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import com.example.dummybase.R
import com.example.dummybase.data.model.Seance

class SeancesReminderReceiver : BroadcastReceiver() {

    lateinit var notificationManager : NotificationManager;
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "crossfit.seances.notifications"
    private val description = "Séances à venir"

    override
    fun onReceive( context : Context, intent: Intent) {

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.GREEN
        notificationChannel.enableVibration(false)
        notificationManager.createNotificationChannel(notificationChannel)

        builder = Notification.Builder( context, channelId)
            .setSmallIcon(R.drawable.haltero_icone_active)
            .setLargeIcon(BitmapFactory.decodeResource( Resources.getSystem() , R.drawable.ic_subscribe))
            .setContentTitle( intent.extras?.get("SEANCE_NAME").toString() )
            .setContentText( intent.extras?.get("SEANCE_DATE").toString() )

        notificationManager.notify( intent.extras?.get("SEANCE_ID") as Int , builder.build() )

        println( " 2 " + intent.extras?.get("SEANCE_NAME") )
        println( " 3 " + intent.extras?.get("SEANCE_DATE") )
        println( " 4 " + intent.extras?.keySet());
        println( " 5 " + intent.extras);
    }
}

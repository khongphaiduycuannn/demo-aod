package com.ndmquan.base.demoaod

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class AodService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
    }

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                Intent.ACTION_SCREEN_ON -> {
                    Log.d("thuongngok", "screen on")
                    try {
                        val activityIntent =
                            Intent(this@AodService, AodActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                        Intent.FLAG_ACTIVITY_SINGLE_TOP or
                                        Intent.FLAG_ACTIVITY_NO_ANIMATION
                                putExtra("SHOW_ON_LOCK_SCREEN", true)
                            }
                        startActivity(activityIntent)
                    } catch (ex: Exception) {
                        Log.e("thuongngok", "ex: ${ex.message}")
                    }
                }

                Intent.ACTION_SCREEN_OFF -> {
                    Log.d("thuongngok", "screen off")
                }

                Intent.ACTION_USER_PRESENT -> {
                    Log.d("thuongngok", "user unlocked device")
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notificationManager?.let {
            createNotificationChannel(this, it)
            val notificationBuilder = NotificationCompat.Builder(this, "SCREEN_TRACKING_SERVICE")
            val notification = notificationBuilder
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setOngoing(true)
                .setShowWhen(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("Screen tracking service")
                .build()
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                startForeground(1010, notification)
            } else {
                startForeground(1010, notification, FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
            }
        }

        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_USER_PRESENT)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, intentFilter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(receiver, intentFilter)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    private fun createNotificationChannel(
        context: Context,
        notificationManager: NotificationManager,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.app_name)
            val descriptionText = context.getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                "SCREEN_TRACKING_SERVICE",
                name,
                importance
            ).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}
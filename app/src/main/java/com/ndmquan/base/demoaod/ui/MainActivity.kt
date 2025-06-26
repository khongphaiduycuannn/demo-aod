package com.ndmquan.base.demoaod.ui

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ndmquan.base.demoaod.AodService
import com.ndmquan.base.demoaod.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            startService(this, AodService::class.java)
        }
    }


    private fun startService(context: Context, serviceClass: Class<*>) {
        if (!isRunning(context, serviceClass)) {
            val intentService = Intent(context, serviceClass)
            ContextCompat.startForegroundService(context, intentService)

            Toast.makeText(this, "Start service success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Service is already running", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isRunning(mContext: Context, serviceClass: Class<*>): Boolean {
        val manager = mContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}
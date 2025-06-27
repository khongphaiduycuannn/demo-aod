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
import com.ndmquan.base.demoaod.ui.clock.adapter.AodAdapter
import com.ndmquan.base.demoaod.ui.clock.data.source.AodSource

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    private val aodAdapter by lazy {
        AodAdapter {
            val prefs = getSharedPreferences("aod", Context.MODE_PRIVATE)
            prefs.edit().putInt("AOD_LAYOUT", it.layout).commit()
            startService(this, AodService::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rcThemes.adapter = aodAdapter
        aodAdapter.setData(AodSource.themes)
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
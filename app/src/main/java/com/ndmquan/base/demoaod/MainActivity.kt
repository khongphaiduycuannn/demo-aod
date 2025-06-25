package com.ndmquan.base.demoaod

import android.app.ActivityManager
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ndmquan.base.demoaod.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val showOnLockScreen = intent.getBooleanExtra("SHOW_ON_LOCK_SCREEN", false)

        if (showOnLockScreen) {
            setupLockScreenDisplay()
        }
        setContentView(binding.root)

        overridePendingTransition(0, 0)

        binding.textView.setOnClickListener {
            startService(this, ScreenTrackingService::class.java)
        }
    }

    private fun setupLockScreenDisplay() {
        // Set flags để hiển thị trên lock screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }

        // Thêm các flags khác cần thiết
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )

        // Tắt thanh status và navigation để fullscreen như AOD
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }

        // Giảm độ sáng màn hình để tiết kiệm pin như AOD thật
        val layoutParams = window.attributes
        layoutParams.screenBrightness = 0.1f // 10% brightness
        window.attributes = layoutParams
    }

    override fun onResume() {
        super.onResume()

        // Kiểm tra xem device có bị khóa không
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val isLocked = keyguardManager.isKeyguardLocked

        if (!isLocked && intent.getBooleanExtra("SHOW_ON_LOCK_SCREEN", false)) {
            // Nếu device đã unlock và đây là AOD mode, có thể finish activity
            finish()
        }
    }

    override fun onBackPressed() {
        // Ngăn user thoát khỏi AOD bằng back button
        val showOnLockScreen = intent.getBooleanExtra("SHOW_ON_LOCK_SCREEN", false)
        if (!showOnLockScreen) {
            super.onBackPressed()
            overridePendingTransition(0, 0)
        }
        // Nếu đang ở AOD mode, không làm gì cả
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
package com.ndmquan.base.demoaod.ui

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.ndmquan.base.demoaod.databinding.ActivityAodBinding
import com.ndmquan.base.demoaod.ui.clock.data.ClockData
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AodActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAodBinding.inflate(layoutInflater) }

    private val clockFragment by lazy { ClockData.ThreeDimensClock }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup fullscreen and hide navigation bar
        setupFullScreenMode()

        val showOnLockScreen = intent.getBooleanExtra("SHOW_ON_LOCK_SCREEN", false)
        if (showOnLockScreen) {
            setupLockScreenDisplay()
        }
        setContentView(binding.root)
        overridePendingTransition(0, 0)


        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, clockFragment)
            .commit()


        lifecycleScope.launch {
            while (isActive) {
                delay(1000)
                clockFragment.notifyDateTimeChanged(System.currentTimeMillis())
            }
        }
    }

    private fun setupFullScreenMode() {
        // Make activity fullscreen and hide navigation bar
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android 11 and above
            window.setDecorFitsSystemWindows(false)
            try {
                val controller = window.insetsController
                if (controller != null) {
                    controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    controller.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                } else {
                    // Fallback to older method if controller is null
                    useLegacyFullscreenMode()
                }
            } catch (e: Exception) {
                // Fallback in case of any exception
                useLegacyFullscreenMode()
            }
        } else {
            useLegacyFullscreenMode()
        }
    }

    private fun useLegacyFullscreenMode() {
        // Use WindowInsetsControllerCompat for better compatibility
        try {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val controller = WindowInsetsControllerCompat(window, window.decorView)
            controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } catch (e: Exception) {
            // Final fallback using deprecated but reliable method
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }
    }

    private fun setupLockScreenDisplay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }

        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )

        val layoutParams = window.attributes
        layoutParams.screenBrightness = 0.1f
        window.attributes = layoutParams
    }

    override fun onResume() {
        super.onResume()

        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val isLocked = keyguardManager.isKeyguardLocked

        if (!isLocked && intent.getBooleanExtra("SHOW_ON_LOCK_SCREEN", false)) {
            finish()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // Ensure fullscreen mode is maintained when window regains focus
            setupFullScreenMode()
        }
    }

    override fun onBackPressed() {
        val showOnLockScreen = intent.getBooleanExtra("SHOW_ON_LOCK_SCREEN", false)
        if (!showOnLockScreen) {
            super.onBackPressed()
            overridePendingTransition(0, 0)
        }
    }
}
package com.ndmquan.base.demoaod.ui.clock.base

import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.utils.tryToFindViewById

class BaseAnalogClockFragment : BaseClockFragment() {

    val ivHourHand by lazy { tryToFindViewById<ImageView>(R.id.ivHourHand) }
    val ivMinuteHand by lazy { tryToFindViewById<ImageView>(R.id.ivMinuteHand) }
    val ivSecondHand by lazy { tryToFindViewById<ImageView>(R.id.ivSecondHand) }

    private var isPivotSet = false
    private val angleThreshold = 10f


    override var layoutId: Int = R.layout.layout_clock_base_analog


    override fun onViewCreated(view: android.view.View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPivotWhenReady()
    }

    private fun setupPivotWhenReady() {
        ivSecondHand?.viewTreeObserver?.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                ivSecondHand?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                setupPivotPoints()
                isPivotSet = true
            }
        })
    }

    private fun setupPivotPoints() {
        ivSecondHand?.let { view ->
            view.pivotX = view.width.toFloat() / 2
            view.pivotY = view.height.toFloat()
        }

        ivMinuteHand?.let { view ->
            view.pivotX = view.width.toFloat() / 2
            view.pivotY = view.height.toFloat()
        }

        ivHourHand?.let { view ->
            view.pivotX = view.width.toFloat() / 2
            view.pivotY = view.height.toFloat()
        }
    }

    override fun onTimeChange(
        hourFirst: Int,
        hourSecond: Int,
        minuteFirst: Int,
        minuteSecond: Int,
        secondFirst: Int,
        secondSecond: Int
    ) {
        val second = secondFirst * 10 + secondSecond
        val minute = minuteFirst * 10 + minuteSecond
        val hour = hourFirst * 10 + hourSecond

        val secondDegrees = (second + minute * 60 + hour / 12 * 3600) / 60f * 360f
        val minuteDegrees = (minute + hour / 12 * 60) / 60f * 360f + second / 3600f * 360f
        val hourDegrees = (hour % 12) / 12f * 360f + minute / 720f * 360f

        if (!isPivotSet) {
            post { setupPivotIfNeeded() }
        }

        rotateHand(ivSecondHand, secondDegrees)
        rotateHand(
            ivMinuteHand,
            minuteDegrees,
            if (secondFirst == 0 && secondSecond == 0) 1000 else 0
        )
        rotateHand(ivHourHand, hourDegrees, if (minuteFirst == 0 && minuteSecond == 0) 1000 else 0)
    }

    private fun setupPivotIfNeeded() {
        ivSecondHand?.let { view ->
            if (view.width > 0 && view.height > 0) {
                setupPivotPoints()
                isPivotSet = true
            }
        }
    }

    private fun rotateHand(handView: ImageView?, degrees: Float, duration: Long = 1000) {
        handView?.let { view ->
            if (view.width > 0 && view.height > 0) {
                view.pivotX = view.width.toFloat() / 2
                view.pivotY = view.height.toFloat()
            }

            val currentRotation = view.rotation
            val angleDifference = degrees - currentRotation

            if (kotlin.math.abs(angleDifference) > angleThreshold || duration == 0L) {
                view.rotation = degrees
            } else if (duration > 0) {
                view.animate()
                    .rotation(degrees)
                    .setDuration(duration)
                    .setInterpolator(LinearInterpolator())
                    .start()
            }
        }
    }

    private fun post(action: () -> Unit) {
        ivSecondHand?.post(action)
    }
}
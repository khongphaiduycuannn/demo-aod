package com.ndmquan.base.demoaod.ui.clock

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.adapter.Notification
import com.ndmquan.base.demoaod.ui.clock.adapter.NotificationAdapter
import com.ndmquan.base.demoaod.ui.clock.utils.tryToFindViewById
import com.ndmquan.base.demoaod.ui.clock.view.ScalableClockLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

abstract class BaseClockFragment : Fragment() {

    val tvDayWeek by lazy { tryToFindViewById<TextView>(R.id.tvDayWeek) }
    val tvDateTime by lazy { tryToFindViewById<TextView>(R.id.tvDateTime) }

    val tvBattery by lazy { tryToFindViewById<TextView>(R.id.tvBattery) }

    val ivBackground by lazy { tryToFindViewById<ImageView>(R.id.ivBackground) }

    val rvNotifications by lazy { tryToFindViewById<RecyclerView>(R.id.rvNotifications) }
    val notificationsAdapter by lazy { NotificationAdapter() }


    open var layoutId: Int = R.layout.layout_clock_base


    private lateinit var gestureDetector: GestureDetector
    private val minSwipeDistance = 150
    private val swipeThreshold = 100


    private var showBattery = true
    private var showDate = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments ?: return

        val layoutId = args.getInt(KEY_LAYOUT_ID, -1)
        if (layoutId != -1) {
            this.layoutId = layoutId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val date = Date()
        onDateTimeChanged(date.time)
        onBatteryChanged(100)

        tvBattery?.visibility = if (showBattery) View.VISIBLE else View.GONE
        tvDayWeek?.visibility = if (showDate) View.VISIBLE else View.GONE
        tvDateTime?.visibility = if (showDate) View.VISIBLE else View.GONE

        tvBattery?.let { (view as? ScalableClockLayout)?.disableScaleView(it) }
        tvDayWeek?.let { (view as? ScalableClockLayout)?.disableScaleView(it) }
        tvDateTime?.let { (view as? ScalableClockLayout)?.disableScaleView(it) }
        ivBackground?.let { (view as? ScalableClockLayout)?.disableScaleView(it) }

        rvNotifications?.adapter = notificationsAdapter

        setupGestureDetection()
    }


    fun notifyDateTimeChanged(timeInMillis: Long) {
        onDateTimeChanged(timeInMillis)
    }

    fun notifyBatteryChanged(batteryPercent: Int) {
        onBatteryChanged(batteryPercent)
    }

    fun notifyNotificationChanged(notifications: List<Notification>) {
        notificationsAdapter.submitList(notifications)
    }

    fun setDateTimeTextColor(color: Int) {
        tvDayWeek?.setTextColor(color)
        tvDateTime?.setTextColor(color)
    }

    fun getDateTimeTextColor(): Int {
        return tvDayWeek?.currentTextColor ?: DEFAULT_TEXT_COLOR
    }

    fun setBatteryTextColor(color: Int) {
        tvBattery?.setTextColor(color)
    }

    fun getBatteryTextColor(): Int {
        return tvBattery?.currentTextColor ?: DEFAULT_TEXT_COLOR
    }

    fun setBackgroundImage(any: Any) {
        context?.let { context ->
            ivBackground?.let { imageView -> Glide.with(context).load(any).into(imageView) }
        }
    }

    fun setScale(scale: Float) {
        val targetScale = min(max(scale, MIN_SCALE), MAX_SCALE)
        (view as? ScalableClockLayout)?.setScale(targetScale)
    }

    fun showBattery(show: Boolean) {
        showBattery = show
        tvBattery?.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showDate(show: Boolean) {
        showDate = show
        tvDayWeek?.visibility = if (show) View.VISIBLE else View.GONE
        tvDateTime?.visibility = if (show) View.VISIBLE else View.GONE
    }


    private fun onDateTimeChanged(timeInMillis: Long) {
        val calendar = Calendar.getInstance().apply {
            time = Date(timeInMillis)
        }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        val hourFirst = hour / 10
        val hourSecond = hour % 10
        val minuteFirst = minute / 10
        val minuteSecond = minute % 10
        val secondFirst = second / 10
        val secondSecond = second % 10
        onDateChange(timeInMillis)
        onTimeChange(hourFirst, hourSecond, minuteFirst, minuteSecond, secondFirst, secondSecond)
    }


    open fun onBatteryChanged(batteryPercent: Int) {
        val percent = "$batteryPercent%"
        tvBattery?.text = percent
    }

    open fun onDateChange(timeInMillis: Long) {
        val date = Date(timeInMillis)
        val dayWeekFormat = SimpleDateFormat("EEEE,", Locale.getDefault())
        val dateTimeFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        val dayWeek = dayWeekFormat.format(date)
        val dateTime = dateTimeFormat.format(date)

        tvDayWeek?.text = dayWeek
        tvDateTime?.text = dateTime
    }

    abstract fun onTimeChange(
        hourFirst: Int,
        hourSecond: Int,
        minuteFirst: Int,
        minuteSecond: Int,
        secondFirst: Int,
        secondSecond: Int
    )


    @SuppressLint("ClickableViewAccessibility")
    private fun setupGestureDetection() {
        gestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    activity?.finish()
                    return true
                }

                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    if (e1 == null) return false

                    val diffX = e2.x - e1.x
                    val diffY = e2.y - e1.y

                    if (abs(diffY) > abs(diffX)) {
                        if (diffY < -minSwipeDistance &&
                            abs(velocityY) > swipeThreshold &&
                            e1.y > (view?.height ?: 0) * 0.6f
                        ) {
                            activity?.finish()
                            return true
                        }
                    }
                    return false
                }
            })

        val touchView = ivBackground ?: view
        touchView?.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }


    companion object {
        const val KEY_LAYOUT_ID = "key_layout_id"
        const val KEY_CHAR_LIST = "key_char_list"

        const val DEFAULT_TEXT_COLOR = Color.WHITE

        const val MAX_SCALE = 5f
        const val MIN_SCALE = 0.5f
    }
}
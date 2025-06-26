package com.ndmquan.base.demoaod.ui.clock

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
import com.ndmquan.base.demoaod.ui.clock.data.Character
import com.ndmquan.base.demoaod.ui.clock.data.ImageCharacter
import com.ndmquan.base.demoaod.ui.clock.data.TextCharacter
import com.ndmquan.base.demoaod.ui.clock.view.ScalableClockLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

open class BaseClockFragment : Fragment() {

    protected val tvHourFirstChar by lazy { tryToFindViewById<TextView>(R.id.tvHourFirstChar) }
    protected val tvHourSecondChar by lazy { tryToFindViewById<TextView>(R.id.tvHourSecondChar) }
    protected val tvMinuteFirstChar by lazy { tryToFindViewById<TextView>(R.id.tvMinuteFirstChar) }
    protected val tvMinuteSecondChar by lazy { tryToFindViewById<TextView>(R.id.tvMinuteSecondChar) }

    protected val ivHourFirstChar by lazy { tryToFindViewById<ImageView>(R.id.ivHourFirstChar) }
    protected val ivHourSecondChar by lazy { tryToFindViewById<ImageView>(R.id.ivHourSecondChar) }
    protected val ivMinuteFirstChar by lazy { tryToFindViewById<ImageView>(R.id.ivMinuteFirstChar) }
    protected val ivMinuteSecondChar by lazy { tryToFindViewById<ImageView>(R.id.ivMinuteSecondChar) }

    protected val tvDayWeek by lazy { tryToFindViewById<TextView>(R.id.tvDayWeek) }
    protected val tvDateTime by lazy { tryToFindViewById<TextView>(R.id.tvDateTime) }

    protected val tvBattery by lazy { tryToFindViewById<TextView>(R.id.tvBattery) }

    protected val ivBackground by lazy { tryToFindViewById<ImageView>(R.id.ivBackground) }

    protected val rvNotifications by lazy { tryToFindViewById<RecyclerView>(R.id.rvNotifications) }
    protected val notificationsAdapter by lazy { NotificationAdapter() }


    private lateinit var gestureDetector: GestureDetector
    private val minSwipeDistance = 150
    private val swipeThreshold = 100


    protected open var layoutId: Int = R.layout.layout_clock_base

    protected open val charList = mutableListOf<Character>().apply {
        addAll(DEFAULT_CHAR_LIST)
    }


    private var showBattery = true
    private var showDate = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments ?: return

        val layoutId = args.getInt(KEY_LAYOUT_ID, -1)
        if (layoutId != -1) {
            this.layoutId = layoutId
        }

        val charList = args
            .getParcelableArray(KEY_CHAR_LIST)
            ?.toList()
            ?.filterIsInstance<Character>()
        if (charList != null) {
            this.charList.clear()
            this.charList.addAll(charList)
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


    fun showBattery(show: Boolean) {
        showBattery = show
        tvBattery?.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showDate(show: Boolean) {
        showDate = show
        tvDayWeek?.visibility = if (show) View.VISIBLE else View.GONE
        tvDateTime?.visibility = if (show) View.VISIBLE else View.GONE
    }


    fun setHourMinuteTextColor(color: Int) {
        tvHourFirstChar?.setTextColor(color)
        tvHourSecondChar?.setTextColor(color)
        tvMinuteFirstChar?.setTextColor(color)
        tvMinuteSecondChar?.setTextColor(color)
    }

    fun getHourMinuteTextColor(): Int {
        return tvHourFirstChar?.currentTextColor ?: DEFAULT_TEXT_COLOR
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


    private fun onDateTimeChanged(timeInMillis: Long) {
        onTimeChange(timeInMillis)
        onDateChange(timeInMillis)
    }

    private fun onBatteryChanged(batteryPercent: Int) {
        val percent = "$batteryPercent%"
        tvBattery?.text = percent
    }

    private fun onTimeChange(timeInMillis: Long) {
        val calendar = Calendar.getInstance().apply {
            time = Date(timeInMillis)
        }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val hourFirst = hour / 10
        val hourSecond = hour % 10
        val minuteFirst = minute / 10
        val minuteSecond = minute % 10

        val hourFirstChar = charList[hourFirst]
        val hourSecondChar = charList[hourSecond]
        val minuteFirstChar = charList[minuteFirst]
        val minuteSecondChar = charList[minuteSecond]

        setHourMinuteView(hourFirstChar, hourSecondChar, minuteFirstChar, minuteSecondChar)
    }

    private fun onDateChange(timeInMillis: Long) {
        val date = Date(timeInMillis)
        val dayWeekFormat = SimpleDateFormat("EEEE,", Locale.getDefault())
        val dateTimeFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        val dayWeek = dayWeekFormat.format(date)
        val dateTime = dateTimeFormat.format(date)

        setDateTimeView(dayWeek, dateTime)
    }


    protected open fun setHourMinuteView(
        hourFirst: Character,
        hourSecond: Character,
        minuteFirst: Character,
        minuteSecond: Character
    ) {
        when (hourFirst) {
            is TextCharacter -> tvHourFirstChar?.text = hourFirst.text.toString()
            is ImageCharacter -> ivHourFirstChar?.setImageResource(hourFirst.imageResId)
        }

        when (hourSecond) {
            is TextCharacter -> tvHourSecondChar?.text = hourSecond.text.toString()
            is ImageCharacter -> ivHourSecondChar?.setImageResource(hourSecond.imageResId)
        }

        when (minuteFirst) {
            is TextCharacter -> tvMinuteFirstChar?.text = minuteFirst.text.toString()
            is ImageCharacter -> ivMinuteFirstChar?.setImageResource(minuteFirst.imageResId)
        }

        when (minuteSecond) {
            is TextCharacter -> tvMinuteSecondChar?.text = minuteSecond.text.toString()
            is ImageCharacter -> ivMinuteSecondChar?.setImageResource(minuteSecond.imageResId)
        }
    }

    protected open fun setDateTimeView(
        dayWeek: String,
        dateTime: String
    ) {
        tvDayWeek?.text = dayWeek
        tvDateTime?.text = dateTime
    }


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

    private fun <T> tryToFindViewById(id: Int): T? {
        return try {
            view?.findViewById(id)
        } catch (ex: Exception) {
            null
        }
    }


    companion object {

        private const val KEY_LAYOUT_ID = "key_layout_id"
        private const val KEY_CHAR_LIST = "key_char_list"

        fun newInstance(
            layoutId: Int = R.layout.layout_clock_base,
            charList: List<Character> = DEFAULT_CHAR_LIST
        ): BaseClockFragment {
            return BaseClockFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_LAYOUT_ID, layoutId)
                    putParcelableArray(KEY_CHAR_LIST, charList.toTypedArray())
                }
            }
        }


        const val DEFAULT_TEXT_COLOR = Color.WHITE

        const val MAX_SCALE = 5f
        const val MIN_SCALE = 0.5f

        val DEFAULT_CHAR_LIST = listOf<Character>(
            TextCharacter('0'), TextCharacter('1'), TextCharacter('2'),
            TextCharacter('3'), TextCharacter('4'), TextCharacter('5'),
            TextCharacter('6'), TextCharacter('7'), TextCharacter('8'),
            TextCharacter('9')
        )
    }
}
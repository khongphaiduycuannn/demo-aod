package com.ndmquan.base.demoaod.clock

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ndmquan.base.demoaod.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

open class BaseClockFragment : Fragment() {

    protected open val layoutId: Int = R.layout.fragment_base_clock


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }


    // Text characters
    protected val tvHourFirstChar by lazy { tryToFindViewById<TextView>(R.id.tvHourFirstChar) }
    protected val tvHourSecondChar by lazy { tryToFindViewById<TextView>(R.id.tvHourSecondChar) }
    protected val tvMinuteFirstChar by lazy { tryToFindViewById<TextView>(R.id.tvMinuteFirstChar) }
    protected val tvMinuteSecondChar by lazy { tryToFindViewById<TextView>(R.id.tvMinuteSecondChar) }

    // Date and time
    protected val tvDayWeek by lazy { tryToFindViewById<TextView>(R.id.tvDayWeek) }
    protected val tvDateTime by lazy { tryToFindViewById<TextView>(R.id.tvDateTime) }

    // Battery
    protected val tvBattery by lazy { tryToFindViewById<TextView>(R.id.tvBattery) }

    // Background
    protected val ivBackground by lazy { tryToFindViewById<ImageView>(R.id.ivBackground) }


    protected open val charList = mutableListOf<Any>().apply {
        addAll(DEFAULT_CHAR_LIST)
    }

    private var showBattery = true
    private var showDate = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val date = Date()
        onDateTimeChanged(date.time)
        onBatteryChanged(100)

        tvBattery?.visibility = if (showBattery) View.VISIBLE else View.GONE
        tvDayWeek?.visibility = if (showDate) View.VISIBLE else View.GONE
        tvDateTime?.visibility = if (showDate) View.VISIBLE else View.GONE

        ivBackground?.setOnClickListener {
            activity?.finish()
        }
    }


    fun notifyDateTimeChanged(timeInMillis: Long) {
        onDateTimeChanged(timeInMillis)
    }

    fun notifyBatteryChanged(batteryPercent: Int) {
        onBatteryChanged(batteryPercent)
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


    protected open fun setHourMinuteView(
        hourFirst: Any,
        hourSecond: Any,
        minuteFirst: Any,
        minuteSecond: Any
    ) {
        if (isTextChar(hourFirst)) {
            tvHourFirstChar?.text = (hourFirst as Char).toString()
        } else {
            tvHourFirstChar?.background = hourFirst as Drawable
        }

        if (isTextChar(hourSecond)) {
            tvHourSecondChar?.text = (hourSecond as Char).toString()
        } else {
            tvHourSecondChar?.background = hourSecond as Drawable
        }

        if (isTextChar(minuteFirst)) {
            tvMinuteFirstChar?.text = (minuteFirst as Char).toString()
        } else {
            tvMinuteFirstChar?.background = minuteFirst as Drawable
        }

        if (isTextChar(minuteSecond)) {
            tvMinuteSecondChar?.text = (minuteSecond as Char).toString()
        } else {
            tvMinuteSecondChar?.background = minuteSecond as Drawable
        }
    }

    protected open fun setDateTimeView(dayWeek: String, dateTime: String) {
        tvDayWeek?.text = dayWeek
        tvDateTime?.text = dateTime
    }


    private fun onDateTimeChanged(timeInMillis: Long) {
        onTimeChange(timeInMillis)
        onDateChange(timeInMillis)
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


    protected open fun onBatteryChanged(batteryPercent: Int) {
        val percent = "$batteryPercent%"
        tvBattery?.text = percent
    }


    private fun <T> tryToFindViewById(id: Int): T? {
        return try {
            view?.findViewById(id)
        } catch (ex: Exception) {
            null
        }
    }


    companion object {

        const val DEFAULT_TEXT_COLOR = Color.WHITE


        const val MAX_SCALE = 1.3f

        const val MIN_SCALE = 0.5f


        val DEFAULT_CHAR_LIST = listOf<Any>(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        )

        fun isTextChar(char: Any): Boolean {
            return char is Char
        }
    }
}
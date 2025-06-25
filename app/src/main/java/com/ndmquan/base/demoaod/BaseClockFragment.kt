package com.ndmquan.base.demoaod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

abstract class BaseClockFragment : Fragment() {

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


    // Image characters


    // Date and time
    protected val tvDayWeek by lazy { tryToFindViewById<TextView>(R.id.tvDayWeek) }
    protected val tvDateTime by lazy { tryToFindViewById<TextView>(R.id.tvDateTime) }


    // Battery
    protected val tvBattery by lazy { tryToFindViewById<TextView>(R.id.tvBattery) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    fun notifyDateTimeChanged(dateTime: String) {

    }


    protected open fun onDateTimeChanged(timeInMillis: Long) {

    }


    fun notifyBatteryChanged(batteryPercent: Int) {
        onBatteryChanged(batteryPercent)
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
}
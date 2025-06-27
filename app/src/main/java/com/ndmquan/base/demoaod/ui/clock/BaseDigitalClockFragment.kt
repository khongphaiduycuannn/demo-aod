package com.ndmquan.base.demoaod.ui.clock

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.data.Character
import com.ndmquan.base.demoaod.ui.clock.data.ImageCharacter
import com.ndmquan.base.demoaod.ui.clock.data.TextCharacter
import com.ndmquan.base.demoaod.ui.clock.utils.tryToFindViewById

open class BaseDigitalClockFragment : BaseClockFragment() {

    val tvHourFirstChar by lazy { tryToFindViewById<TextView>(R.id.tvHourFirstChar) }
    val tvHourSecondChar by lazy { tryToFindViewById<TextView>(R.id.tvHourSecondChar) }
    val tvMinuteFirstChar by lazy { tryToFindViewById<TextView>(R.id.tvMinuteFirstChar) }
    val tvMinuteSecondChar by lazy { tryToFindViewById<TextView>(R.id.tvMinuteSecondChar) }
    val tvSecondFirstChar by lazy { tryToFindViewById<TextView>(R.id.tvSecondFirstChar) }
    val tvSecondSecondChar by lazy { tryToFindViewById<TextView>(R.id.tvSecondSecondChar) }

    val ivHourFirstChar by lazy { tryToFindViewById<ImageView>(R.id.ivHourFirstChar) }
    val ivHourSecondChar by lazy { tryToFindViewById<ImageView>(R.id.ivHourSecondChar) }
    val ivMinuteFirstChar by lazy { tryToFindViewById<ImageView>(R.id.ivMinuteFirstChar) }
    val ivMinuteSecondChar by lazy { tryToFindViewById<ImageView>(R.id.ivMinuteSecondChar) }
    val ivSecondFirstChar by lazy { tryToFindViewById<ImageView>(R.id.ivSecondFirstChar) }
    val ivSecondSecondChar by lazy { tryToFindViewById<ImageView>(R.id.ivSecondSecondChar) }


    override var layoutId: Int = R.layout.layout_clock_base_digital

    open val charList = mutableListOf<Character>().apply {
        addAll(DEFAULT_CHAR_LIST)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments ?: return
        val charList = args
            .getParcelableArray(KEY_CHAR_LIST)
            ?.toList()
            ?.filterIsInstance<Character>()
        if (charList != null) {
            this.charList.clear()
            this.charList.addAll(charList)
        }
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


    override fun onTimeChange(
        hourFirst: Int,
        hourSecond: Int,
        minuteFirst: Int,
        minuteSecond: Int,
        secondFirst: Int,
        secondSecond: Int
    ) {
        val hourFirstChar = charList[hourFirst]
        val hourSecondChar = charList[hourSecond]
        val minuteFirstChar = charList[minuteFirst]
        val minuteSecondChar = charList[minuteSecond]
        val secondFirstChar = charList[secondFirst]
        val secondSecondChar = charList[secondSecond]

        setHourMinuteView(
            hourFirstChar,
            hourSecondChar,
            minuteFirstChar,
            minuteSecondChar,
            secondFirstChar,
            secondSecondChar
        )
    }

    open fun setHourMinuteView(
        hourFirst: Character,
        hourSecond: Character,
        minuteFirst: Character,
        minuteSecond: Character,
        secondFirst: Character,
        secondSecond: Character
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

        when (secondFirst) {
            is TextCharacter -> tvSecondFirstChar?.text = secondFirst.text.toString()
            is ImageCharacter -> ivSecondFirstChar?.setImageResource(secondFirst.imageResId)
        }

        when (secondSecond) {
            is TextCharacter -> tvSecondSecondChar?.text = secondSecond.text.toString()
            is ImageCharacter -> ivSecondSecondChar?.setImageResource(secondSecond.imageResId)
        }
    }


    companion object {
        fun newInstance(
            layoutId: Int = R.layout.layout_clock_base_digital,
            charList: List<Character> = DEFAULT_CHAR_LIST
        ): BaseDigitalClockFragment {
            return BaseDigitalClockFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_LAYOUT_ID, layoutId)
                    putParcelableArray(KEY_CHAR_LIST, charList.toTypedArray())
                }
            }
        }

        val DEFAULT_CHAR_LIST = listOf<Character>(
            TextCharacter('0'), TextCharacter('1'), TextCharacter('2'),
            TextCharacter('3'), TextCharacter('4'), TextCharacter('5'),
            TextCharacter('6'), TextCharacter('7'), TextCharacter('8'),
            TextCharacter('9')
        )
    }
}
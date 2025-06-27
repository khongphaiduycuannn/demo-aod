package com.ndmquan.base.demoaod.ui.clock

import com.ndmquan.base.demoaod.ui.clock.view.FoldImageView
import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.base.BaseDigitalClockFragment
import com.ndmquan.base.demoaod.ui.clock.data.Character
import com.ndmquan.base.demoaod.ui.clock.data.ImageCharacter
import com.ndmquan.base.demoaod.ui.clock.utils.tryToFindViewById

class FlipClockFragment : BaseDigitalClockFragment() {

    private val ivHourFirstCharTop by lazy { tryToFindViewById<FoldImageView>(R.id.ivHourFirstChar) }
    private val ivHourSecondCharTop by lazy { tryToFindViewById<FoldImageView>(R.id.ivHourSecondChar) }
    private val ivMinuteFirstCharTop by lazy { tryToFindViewById<FoldImageView>(R.id.ivMinuteFirstChar) }
    private val ivMinuteSecondCharTop by lazy { tryToFindViewById<FoldImageView>(R.id.ivMinuteSecondChar) }

    private val ivHourFirstCharBottom by lazy { tryToFindViewById<FoldImageView>(R.id.ivHourFirstCharTemp) }
    private val ivHourSecondCharBottom by lazy { tryToFindViewById<FoldImageView>(R.id.ivHourSecondCharTemp) }
    private val ivMinuteFirstCharBottom by lazy { tryToFindViewById<FoldImageView>(R.id.ivMinuteFirstCharTemp) }
    private val ivMinuteSecondCharBottom by lazy { tryToFindViewById<FoldImageView>(R.id.ivMinuteSecondCharTemp) }


    private var previousHourFirstChar: ImageCharacter? = null
    private var previousHourSecondChar: ImageCharacter? = null
    private var previousMinuteFirstChar: ImageCharacter? = null
    private var previousMinuteSecondChar: ImageCharacter? = null


    override var layoutId: Int = R.layout.layout_clock_digital_5

    override val charList: MutableList<Character> = mutableListOf(
        ImageCharacter(R.drawable.ic_flip_clock_0),
        ImageCharacter(R.drawable.ic_flip_clock_1),
        ImageCharacter(R.drawable.ic_flip_clock_2),
        ImageCharacter(R.drawable.ic_flip_clock_3),
        ImageCharacter(R.drawable.ic_flip_clock_4),
        ImageCharacter(R.drawable.ic_flip_clock_5),
        ImageCharacter(R.drawable.ic_flip_clock_6),
        ImageCharacter(R.drawable.ic_flip_clock_7),
        ImageCharacter(R.drawable.ic_flip_clock_8),
        ImageCharacter(R.drawable.ic_flip_clock_9)
    )


    override fun setHourMinuteView(
        hourFirst: Character,
        hourSecond: Character,
        minuteFirst: Character,
        minuteSecond: Character,
        secondFirst: Character,
        secondSecond: Character
    ) {
        if (hourFirst !is ImageCharacter
            || hourSecond !is ImageCharacter
            || minuteFirst !is ImageCharacter
            || minuteSecond !is ImageCharacter
            || secondFirst !is ImageCharacter
            || secondSecond !is ImageCharacter
        ) {
            return
        }

        ivHourFirstCharBottom?.setImageResource(secondSecond.imageResId)
        if (secondSecond != previousHourFirstChar) {
            startFoldTransition(
                ivHourFirstCharTop as FoldImageView,
                ivHourFirstCharBottom as FoldImageView,
                secondSecond
            )
            previousHourFirstChar = secondSecond
        }
    }

    private fun startFoldTransition(
        topImageView: FoldImageView,
        bottomImageView: FoldImageView,
        char: ImageCharacter
    ) {
        topImageView.foldLeft(400) {
            bottomImageView.unfoldRight(400) {
                topImageView.setImageResource(char.imageResId)
                bottomImageView.foldAngle = 90f
                bottomImageView.invalidate()
            }
        }
    }
}
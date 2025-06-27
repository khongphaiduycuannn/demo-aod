package com.ndmquan.base.demoaod.ui.clock.data.source

import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.BaseAnalogClockFragment
import com.ndmquan.base.demoaod.ui.clock.BaseDigitalClockFragment
import com.ndmquan.base.demoaod.ui.clock.data.ImageCharacter

object ClockSource {

    val BaseDigitalClock
        get() = BaseDigitalClockFragment.newInstance()

    val MinimalClock
        get() = BaseDigitalClockFragment.newInstance(R.layout.layout_clock_digital_1)

    val BoldClock
        get() = BaseDigitalClockFragment.newInstance(R.layout.layout_clock_digital_2)

    val ClassicClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val ArtDecoClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val FlipClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val TechClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val StrongClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val ModernClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val ColorClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val VintageClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val SimpleClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val FunClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val RetroClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val LineClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val GirdClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val DateClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val CleanClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val FullClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val CreativeClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )

    val ThreeDimensClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_1,
            charList = listOf(
                ImageCharacter(R.drawable.ic_view_clock_3d_0),
                ImageCharacter(R.drawable.ic_view_clock_3d_1),
                ImageCharacter(R.drawable.ic_view_clock_3d_2),
                ImageCharacter(R.drawable.ic_view_clock_3d_3),
                ImageCharacter(R.drawable.ic_view_clock_3d_4),
                ImageCharacter(R.drawable.ic_view_clock_3d_5),
                ImageCharacter(R.drawable.ic_view_clock_3d_6),
                ImageCharacter(R.drawable.ic_view_clock_3d_7),
                ImageCharacter(R.drawable.ic_view_clock_3d_8),
                ImageCharacter(R.drawable.ic_view_clock_3d_9)
            )
        )


    val BaseAnalogClock
        get() = BaseAnalogClockFragment()
}
package com.ndmquan.base.demoaod.ui.clock.data.source

import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.FlipClockFragment
import com.ndmquan.base.demoaod.ui.clock.base.BaseAnalogClockFragment
import com.ndmquan.base.demoaod.ui.clock.base.BaseDigitalClockFragment
import com.ndmquan.base.demoaod.ui.clock.data.ImageCharacter

object ClockSource {

    val BaseDigitalClock
        get() = BaseDigitalClockFragment.newInstance()

    val MinimalClock
        get() = BaseDigitalClockFragment.newInstance(R.layout.layout_clock_digital_1)

    val BoldClock
        get() = BaseDigitalClockFragment.newInstance(R.layout.layout_clock_digital_2)

    val ClassicClock
        get() = BaseDigitalClockFragment.newInstance(R.layout.layout_clock_digital_3)

    val ArtDecoClock
        get() = BaseDigitalClockFragment.newInstance(
            layoutId = R.layout.layout_clock_digital_4,
            charList = listOf(
                ImageCharacter(R.drawable.art_deco_0),
                ImageCharacter(R.drawable.art_deco_1),
                ImageCharacter(R.drawable.art_deco_2),
                ImageCharacter(R.drawable.art_deco_3),
                ImageCharacter(R.drawable.art_deco_4),
                ImageCharacter(R.drawable.art_deco_5),
                ImageCharacter(R.drawable.art_deco_6),
                ImageCharacter(R.drawable.art_deco_7),
                ImageCharacter(R.drawable.art_deco_8),
                ImageCharacter(R.drawable.art_deco_9)
            )
        )

    val FlipClock
        get() = FlipClockFragment()

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
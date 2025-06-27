package com.ndmquan.base.demoaod.ui.clock.data.source

import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.BaseDigitalClockFragment
import com.ndmquan.base.demoaod.ui.clock.data.ImageCharacter

object ClockSource {

    val BaseDigitalClock
        get() = BaseDigitalClockFragment.newInstance()

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
}
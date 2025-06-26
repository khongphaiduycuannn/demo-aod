package com.ndmquan.base.demoaod.ui.clock.data.source

import com.ndmquan.base.demoaod.R
import com.ndmquan.base.demoaod.ui.clock.BaseClockFragment
import com.ndmquan.base.demoaod.ui.clock.data.ImageCharacter

object ClockSource {

    val BaseClock by lazy {
        BaseClockFragment.newInstance()
    }

    val ThreeDimensClock by lazy {
        BaseClockFragment.newInstance(
            layoutId = R.layout.layout_clock_3d,
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
}
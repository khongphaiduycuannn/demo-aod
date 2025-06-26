package com.ndmquan.base.demoaod.ui.clock.data.source

import android.graphics.Color
import com.ndmquan.base.demoaod.ui.clock.BaseClockFragment
import com.ndmquan.base.demoaod.ui.clock.data.AodTheme
import com.ndmquan.base.demoaod.ui.clock.data.AodType

object AodSource {

    val themes = listOf(
        AodTheme(
            id = 1,
            layout = 1,
            thumbnail = "assets:///aod/thumb_aod_3d.webp",
            textColor = Color.parseColor("#ff000000"),
            themeColor = Color.parseColor("#ff000000"),
            type = AodType.IMAGE_CHAR.value
        )
    )


    fun mapClockToLayout(layout: Int): BaseClockFragment {
        return when (layout) {
            1 -> ClockSource.ThreeDimensClock
            else -> ClockSource.BaseClock
        }
    }
}
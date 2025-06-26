package com.ndmquan.base.demoaod.ui.clock

import com.ndmquan.base.demoaod.R

class SampleImageClockFragment : BaseClockFragment() {

    override val layoutId: Int = R.layout.fragment_sample_image_clock

    override val charList = mutableListOf<Any>(
        R.drawable.ic_view_clock_3d_0,
        R.drawable.ic_view_clock_3d_1,
        R.drawable.ic_view_clock_3d_2,
        R.drawable.ic_view_clock_3d_3,
        R.drawable.ic_view_clock_3d_4,
        R.drawable.ic_view_clock_3d_5,
        R.drawable.ic_view_clock_3d_6,
        R.drawable.ic_view_clock_3d_7,
        R.drawable.ic_view_clock_3d_8,
        R.drawable.ic_view_clock_3d_9
    )
}
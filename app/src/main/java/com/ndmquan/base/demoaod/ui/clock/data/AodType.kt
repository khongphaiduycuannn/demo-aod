package com.ndmquan.base.demoaod.ui.clock.data

enum class AodType(val value: Int) {
    DIGITAL_TEXT(1), DIGITAL_IMAGE(2),
    ANALOG(3),
    OTHER(4)
}

enum class ShowBattery(val value: Int) {
    NEVER(1), CHARGING(2), ALWAYS(3)
}
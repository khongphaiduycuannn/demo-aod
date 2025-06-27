package com.ndmquan.base.demoaod.ui.clock.data

import android.graphics.Color
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AodTheme(
    val id: Int,

    val thumbnail: String,
    val layout: Int,

    val scale: Float = 1f,

    val backgroundPath: String? = null,
    val backgroundColor: Int? = Color.parseColor("#000000"),
    val backgroundAlpha: Float = 1f,

    val timeColor: Int = Color.parseColor("#ffffff"),
    val themeColor: Int = Color.parseColor("#ffffff"),

    val showBattery: Int = ShowBattery.ALWAYS.value,
    val saveBattery: Boolean = true,

    val showDate: Boolean = true,

    val type: Int = AodType.DIGITAL_TEXT.value
) : Parcelable
package com.ndmquan.base.demoaod.ui.clock.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface Character : Parcelable

@Parcelize
data class TextCharacter(val text: Char) : Character

@Parcelize
data class ImageCharacter(val imageResId: Int) : Character
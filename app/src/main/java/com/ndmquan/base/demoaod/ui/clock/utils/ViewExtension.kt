package com.ndmquan.base.demoaod.ui.clock.utils

import android.view.ViewGroup
import androidx.fragment.app.Fragment

fun <T> ViewGroup?.tryToFindViewById(id: Int): T? {
    return try {
        this?.findViewById(id)
    } catch (ex: Exception) {
        null
    }
}

fun <T> Fragment?.tryToFindViewById(id: Int): T? {
    return try {
        this?.view?.findViewById(id)
    } catch (ex: Exception) {
        null
    }
}
package com.ndmquan.base.demoaod.clock

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class ScalableClockLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var scaleFactor = 1f
    private val originalSizes = mutableMapOf<View, OriginalSize>()

    data class OriginalSize(
        val textSize: Float,
        val marginStart: Int,
        val marginTop: Int,
        val marginEnd: Int,
        val marginBottom: Int
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val textSize = if (child is TextView) pxToDp(child.textSize) else 0f
            val lp = child.layoutParams as? MarginLayoutParams

            originalSizes[child] = OriginalSize(
                textSize = textSize,
                marginStart = lp?.marginStart ?: 0,
                marginTop = lp?.topMargin ?: 0,
                marginEnd = lp?.marginEnd ?: 0,
                marginBottom = lp?.bottomMargin ?: 0
            )
        }
    }

    fun setScale(scale: Float) {
        scaleFactor = scale

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val originalSize = originalSizes[child] ?: continue

            if (child is TextView && originalSize.textSize > 0) {
                child.textSize = originalSize.textSize * scale
            }

            val lp = child.layoutParams as? MarginLayoutParams
            if (lp != null) {
                lp.setMargins(
                    (originalSize.marginStart * scale).toInt(),
                    (originalSize.marginTop * scale).toInt(),
                    (originalSize.marginEnd * scale).toInt(),
                    (originalSize.marginBottom * scale).toInt()
                )
                child.layoutParams = lp
            }
        }

        requestLayout()
    }


    private fun pxToDp(px: Float): Float {
        val density = resources.displayMetrics.density
        return px / density
    }
}
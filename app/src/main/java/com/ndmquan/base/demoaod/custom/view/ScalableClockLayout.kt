package com.ndmquan.base.demoaod.custom.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class ScalableClockLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var scaleFactor = 1f
    private val originalSizes = mutableMapOf<View, OriginalSize>()
    private val disableScaleViews = mutableSetOf<Int>()

    data class OriginalSize(
        val textSize: Float,
        val width: Int,
        val height: Int,
        val marginStart: Int,
        val marginTop: Int,
        val marginEnd: Int,
        val marginBottom: Int,
        val paddingStart: Int,
        val paddingTop: Int,
        val paddingEnd: Int,
        val paddingBottom: Int
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        measureForViewGroup(this)
    }

    fun setScale(scale: Float) {
        scaleFactor = scale
        scaleForViewGroup(scale, this)
        requestLayout()
    }


    private fun measureForViewGroup(view: ViewGroup) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            val textSize = if (child is TextView) pxToDp(child.textSize) else 0f
            val lp = child.layoutParams as? MarginLayoutParams

            originalSizes[child] = OriginalSize(
                textSize = textSize,
                width = lp?.width ?: 0,
                height = lp?.height ?: 0,
                marginStart = lp?.marginStart ?: 0,
                marginTop = lp?.topMargin ?: 0,
                marginEnd = lp?.marginEnd ?: 0,
                marginBottom = lp?.bottomMargin ?: 0,
                paddingStart = child.paddingStart,
                paddingTop = child.paddingTop,
                paddingEnd = child.paddingEnd,
                paddingBottom = child.paddingBottom
            )

            if (child is ViewGroup) {
                measureForViewGroup(child)
            }
        }
    }


    private fun scaleForViewGroup(scale: Float, view: ViewGroup) {
        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)

            if (disableScaleViews.contains(child.id)) {
                continue
            }

            val originalSize = originalSizes[child] ?: continue

            if (child is TextView && originalSize.textSize > 0) {
                child.textSize = originalSize.textSize * scale
            }

            val lp = child.layoutParams as? MarginLayoutParams
            if (lp != null) {
                if (originalSize.width > 0) {
                    lp.width = (originalSize.width * scale).toInt()
                }
                if (originalSize.height > 0) {
                    lp.height = (originalSize.height * scale).toInt()
                }

                lp.setMargins(
                    (originalSize.marginStart * scale).toInt(),
                    (originalSize.marginTop * scale).toInt(),
                    (originalSize.marginEnd * scale).toInt(),
                    (originalSize.marginBottom * scale).toInt()
                )

                child.layoutParams = lp
            }

            child.setPadding(
                (originalSize.paddingStart * scale).toInt(),
                (originalSize.paddingTop * scale).toInt(),
                (originalSize.paddingEnd * scale).toInt(),
                (originalSize.paddingBottom * scale).toInt()
            )

            if (child is ViewGroup) {
                scaleForViewGroup(scale, child)
            }
        }
    }

    fun disableScaleView(view: View) {
        disableScaleViews.add(view.id)
    }

    fun enableScaleView(view: View) {
        disableScaleViews.remove(view.id)
    }

    fun getCurrentScale(): Float = scaleFactor

    private fun pxToDp(px: Float): Float {
        val density = resources.displayMetrics.density
        return px / density
    }
}
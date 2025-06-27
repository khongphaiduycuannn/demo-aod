package com.ndmquan.base.demoaod.ui.clock.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView

class FoldImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var leftHalf: Bitmap? = null
    private var rightHalf: Bitmap? = null

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera()
    private val matrix = Matrix()

    var foldAngle = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var isAnimating = false
    private var originalBitmap: Bitmap? = null

    init {
        // Đảm bảo view sẽ gọi onDraw ngay cả khi có drawable
        setWillNotDraw(false)
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        bm?.let {
            originalBitmap = it
            createHalves()
        }
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        post {
            extractBitmapFromDrawable()
        }
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        post {
            extractBitmapFromDrawable()
        }
    }

    private fun extractBitmapFromDrawable() {
        drawable?.let { d ->
            val bitmap = when (d) {
                is BitmapDrawable -> d.bitmap
                else -> {
                    // Tạo bitmap từ drawable
                    val bitmap = Bitmap.createBitmap(
                        d.intrinsicWidth.takeIf { it > 0 } ?: width,
                        d.intrinsicHeight.takeIf { it > 0 } ?: height,
                        Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(bitmap)
                    d.setBounds(0, 0, canvas.width, canvas.height)
                    d.draw(canvas)
                    bitmap
                }
            }
            originalBitmap = bitmap
            createHalves()
        }
    }

    private fun createHalves() {
        originalBitmap?.let { bmp ->
            val width = bmp.width
            val height = bmp.height
            val halfWidth = width / 2

            try {
                // Tạo nửa trái
                leftHalf = Bitmap.createBitmap(bmp, 0, 0, halfWidth, height)

                // Tạo nửa phải
                rightHalf = Bitmap.createBitmap(bmp, halfWidth, 0, halfWidth, height)

                invalidate()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun foldLeft(duration: Long = 400, onComplete: (() -> Unit)? = null) {
        if (isAnimating) return

        val animator = ValueAnimator.ofFloat(0f, 90f)
        animator.duration = duration
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addUpdateListener { animation ->
            foldAngle = animation.animatedValue as Float
        }

        animator.addListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationStart(animation: android.animation.Animator) {
                isAnimating = true
            }

            override fun onAnimationEnd(animation: android.animation.Animator) {
                isAnimating = false
                onComplete?.invoke()
            }

            override fun onAnimationCancel(animation: android.animation.Animator) {
                isAnimating = false
            }

            override fun onAnimationRepeat(animation: android.animation.Animator) {}
        })

        animator.start()
    }

    fun unfoldRight(duration: Long = 400, onComplete: (() -> Unit)? = null) {
        if (isAnimating) return

        val animator = ValueAnimator.ofFloat(90f, 0f)
        animator.duration = duration
        animator.interpolator = AccelerateDecelerateInterpolator()

        animator.addUpdateListener { animation ->
            foldAngle = animation.animatedValue as Float
        }

        animator.addListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationStart(animation: android.animation.Animator) {
                isAnimating = true
            }

            override fun onAnimationEnd(animation: android.animation.Animator) {
                isAnimating = false
                onComplete?.invoke()
            }

            override fun onAnimationCancel(animation: android.animation.Animator) {
                isAnimating = false
            }

            override fun onAnimationRepeat(animation: android.animation.Animator) {}
        })

        animator.start()
    }

    fun resetFold() {
        foldAngle = 0f
        if (isAnimating) {
            // Hủy animation đang chạy nếu có
            clearAnimation()
            isAnimating = false
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Tạo lại halves khi size thay đổi
        post {
            extractBitmapFromDrawable()
        }
    }

    override fun onDraw(canvas: Canvas) {
        // Nếu không có hiệu ứng gập hoặc chưa có bitmap halves, vẽ bình thường
        if (foldAngle == 0f || leftHalf == null || rightHalf == null) {
            super.onDraw(canvas)
            return
        }

        // Vẽ hiệu ứng gập
        drawFoldEffect(canvas)
    }

    private fun drawFoldEffect(canvas: Canvas) {
        if (leftHalf == null || rightHalf == null) return

        val viewWidth = width.toFloat()
        val viewHeight = height.toFloat()
        val centerX = viewWidth / 2f
        val centerY = viewHeight / 2f

        // Tính toán kích thước hiển thị dựa trên scaleType
        val imageMatrix = imageMatrix
        val drawableWidth = leftHalf!!.width + rightHalf!!.width
        val drawableHeight = leftHalf!!.height

        // Áp dụng matrix của ImageView để tính toán vị trí và kích thước chính xác
        val values = FloatArray(9)
        imageMatrix.getValues(values)

        val scaleX = values[Matrix.MSCALE_X]
        val scaleY = values[Matrix.MSCALE_Y]
        val transX = values[Matrix.MTRANS_X]
        val transY = values[Matrix.MTRANS_Y]

        val scaledWidth = drawableWidth * scaleX
        val scaledHeight = drawableHeight * scaleY
        val scaledHalfWidth = scaledWidth / 2f

        // Vị trí bắt đầu vẽ
        val startX = transX
        val startY = transY
        val imageCenterX = startX + scaledHalfWidth

        // Vẽ nửa trái (không thay đổi)
        val leftRect = RectF(
            startX,
            startY,
            imageCenterX,
            startY + scaledHeight
        )
        canvas.drawBitmap(leftHalf!!, null, leftRect, paint)

        // Vẽ nửa phải với hiệu ứng gập
        canvas.save()

        camera.save()
        camera.rotateY(foldAngle) // Xoay theo trục Y
        camera.getMatrix(matrix)
        camera.restore()

        // Đặt pivot tại đường giữa
        matrix.preTranslate(-scaledHalfWidth / 2f, -scaledHeight / 2f)
        matrix.postTranslate(imageCenterX, startY + scaledHeight / 2f)

        canvas.concat(matrix)

        val rightRect = RectF(
            -scaledHalfWidth / 2f,
            -scaledHeight / 2f,
            scaledHalfWidth / 2f,
            scaledHeight / 2f
        )
        canvas.drawBitmap(rightHalf!!, null, rightRect, paint)

        canvas.restore()
    }
}
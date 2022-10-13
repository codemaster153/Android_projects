package com.lkb.baseandroidproject

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class RoundCornerRectangularView : View {
    private var mOffscreenBitmap: Bitmap? = null
    private var mOffscreenCanvas: Canvas? = null
    private lateinit var mPaint: Paint
    private lateinit var mRectF: RectF

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        setWillNotDraw(false)
    }

    override fun draw(canvas: Canvas) {
        if (mOffscreenBitmap == null) {
            mOffscreenBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            mOffscreenBitmap?.let {
                mOffscreenCanvas = Canvas(it)
                val mBitmapShader = BitmapShader(it, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

                mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
                mPaint.shader = mBitmapShader
                mRectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
            }
        }
        super.draw(mOffscreenCanvas)
        canvas.drawRoundRect(mRectF, 8f, 9f, mPaint)
    }


}
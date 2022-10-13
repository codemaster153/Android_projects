package com.lkb.baseandroidproject

import android.R.attr
import android.content.Context
import android.graphics.*
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.View


class TesterView : View {
    private var mOffscreenBitmap: Bitmap? = null
    private var mOffscreenCanvas: Canvas? = null
    private lateinit var mPaint: Paint
    private lateinit var mRectF: RectF
    private lateinit var mCircle: OvalShape

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
        var sx=10
        var sy=10
        var fx=50
        var fy=50
        for(i in 0..50){
            canvas.drawLine(sx.toFloat(), sy.toFloat(), (fx+10).toFloat(),(fy+10).toFloat(),mPaint)
            sy++
            fx--
        }

       // drawTriangle(canvas,mPaint, 0f,0f, 100)
    }


//    fun drawTriangle(canvas: Canvas, paint: Paint, x: Float, y: Float, width: Int) {
//        val halfWidth = width / 2
//        val path = Path()
//        path.moveTo(attr.x.toFloat(), (attr.y - halfWidth).toFloat()) // Top
//        path.lineTo((attr.x - halfWidth).toFloat(), (attr.y + halfWidth).toFloat()) // Bottom left
//        path.lineTo((attr.x + halfWidth).toFloat(), (attr.y + halfWidth).toFloat()) // Bottom right
//        path.lineTo(attr.x.toFloat(), (attr.y - halfWidth).toFloat()) // Back to Top
//        path.close()
//        canvas.drawPath(path, paint)
//    }


}
package by.itacademy.myapp.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class Dz4MyView : View {

    private val circlePaintBig = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circlePaintLitle = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var cx = 0f
    private var cy = 0f
    private var radius = 0f

    private var paint = Paint()


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
        circlePaintLitle.color = ContextCompat.getColor(context, android.R.color.black)
        circlePaintBig.color = ContextCompat.getColor(context, android.R.color.white)
        linePaint.color = ContextCompat.getColor(context, android.R.color.white)
        linePaint.setStrokeWidth(5f)
    }

    override fun onSizeChanged(weight: Int, height: Int, oldWeight: Int, oldHeight: Int) {
        super.onSizeChanged(weight, height, oldWeight, oldHeight)

        cx = weight/2f
        cy = height/2f
        radius = min(cx, cy)/1.5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        canvas.drawCircle(cx, cy, (radius+5), circlePaintBig)
        canvas.drawCircle(cx, cy, radius, circlePaintLitle)
        canvas.drawLine(cx, cy-radius-15, cx, cy-radius+10, linePaint)
    }
}
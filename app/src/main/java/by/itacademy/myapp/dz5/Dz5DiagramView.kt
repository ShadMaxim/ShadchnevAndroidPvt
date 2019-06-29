package by.itacademy.myapp.dz5

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import by.itacademy.myapp.R
import java.lang.Math.min

class Dz5DiagramView : View {

    private var centerWeight = 0f
    private var centerHeight = 0f
    private var radius = 0f

    private val myRect = RectF()
    private val textRect = Rect()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val cyrclePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var diagramData = arrayOf(30, 20, 10, 40, 50)

    private val dataSum = diagramData.sum()
    private var angleStart = 0f
    private var angleStop = 0f
    private var colorNext = 0
    private var radianArc = 0.0
    private val textOffset = 1.5f

    private val diagramColors =
        arrayOf(
            R.color.dz5color1,
            R.color.dz5color2,
            R.color.dz5color3,
            R.color.dz5color4,
            R.color.dz5color5,
            R.color.dz5color6,
            R.color.dz5color7,
            R.color.dz5color8,
            R.color.dz5color9,
            R.color.dz5color10
        )

    init {
        textPaint.textSize = resources.getDimension(R.dimen.dz5TextSize)
        textPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)

        linePaint.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = resources.getDimension(R.dimen.dz5LineSize)

        pointPaint.color = ContextCompat.getColor(context, R.color.colorButton)
        pointPaint.style = Paint.Style.FILL
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerHeight = h / 2f
        centerWeight = w / 2f

        radius = min(w, h) * 0.3f

        myRect.set(
            centerWeight - radius,
            centerHeight - radius,
            centerWeight + radius,
            centerHeight + radius
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        drawMyDiagram(canvas)
    }

    private fun drawLinesCyrcles(
        canvas: Canvas,
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        textWidth: Float
    ) {
        val radiusCircle = textWidth * 2f
        canvas.drawLine(startX, startY, endX, endY, linePaint)
        canvas.drawCircle(endX, endY, radiusCircle, pointPaint)
    }

    private fun drawMyDiagram(canvas: Canvas) {

        for (number in diagramData) {

            angleStop = ((number * 360) / dataSum).toFloat()
            radianArc = (angleStart + angleStop / 2) * Math.PI / 180f

            val circleX = (centerWeight + (radius * Math.cos(radianArc))).toFloat()
            val circleY = (centerHeight + (radius * Math.sin(radianArc))).toFloat()

            val textX = (centerWeight + ((radius * textOffset) * Math.cos(radianArc))).toFloat()
            val textY = (centerHeight + ((radius * textOffset) * Math.sin(radianArc))).toFloat()

            val text = number.toString()
            textPaint.getTextBounds(text, 0, text.length, textRect)
            val textWidth = textPaint.measureText(text, 0, text.length) / 2.0f

            drawLinesCyrcles(canvas, circleX, circleY, textX, textY, textWidth)
            canvas.drawText(text, textX - textWidth, textY + textRect.height() / 2, textPaint)

            cyrclePaint.color = ContextCompat.getColor(context, diagramColors[colorNext++])

            canvas.drawArc(myRect, angleStart, angleStop, true, cyrclePaint)
            angleStart += angleStop
        }
    }
}

package by.itacademy.myapp.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import by.itacademy.myapp.R
import java.util.Calendar
import kotlin.math.min

class Dz4MyClock : View {

    private val circlePaintBig = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circlePaintLitle = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaintBig = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaintLitle = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaintMedium = Paint(Paint.ANTI_ALIAS_FLAG)
    private val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var centerX = 0f
    private var centerY = 0f

    private var radius = 0f

    private var startX = 0f
    private var startY = 0f
    private var stopY = 0f
    private var stopX = 0f

    private val textNumberArr = arrayOf("3", "6", "9", "12")

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
        linePaintBig.color = ContextCompat.getColor(context, android.R.color.white)
        linePaintLitle.color = ContextCompat.getColor(context, android.R.color.white)
        linePaintMedium.color = ContextCompat.getColor(context, android.R.color.white)
        numberPaint.color = ContextCompat.getColor(context, android.R.color.white)

        linePaintBig.strokeWidth = resources.getDimension(R.dimen.dz4linePaintBigSize)
        linePaintLitle.strokeWidth = resources.getDimension(R.dimen.dz4linePaintLitleSize)
        linePaintMedium.strokeWidth = resources.getDimension(R.dimen.dz4linePaintMediumSize)

        numberPaint.style = Paint.Style.FILL
        numberPaint.textSize = resources.getDimension(R.dimen.dz4NumberSize)
    }

    override fun onSizeChanged(weight: Int, height: Int, oldWeight: Int, oldHeight: Int) {
        super.onSizeChanged(weight, height, oldWeight, oldHeight)

        centerX = weight / 2f
        centerY = height / 2f

        radius = min(centerX, centerY) / 1.5f

        startY = centerY - radius
        startX = centerX
        stopX = centerX
        stopY = centerY - radius
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        var calendar = Calendar.getInstance()

        drawMyCircles(canvas)
        drawLinesInCircle(canvas)
        drawNumberClock(canvas)
        drawClockHands(canvas, calendar)

        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawClockHands(canvas: Canvas, calendar: Calendar) {

        var minutes = calendar.get(Calendar.MINUTE)
        var hour = calendar.get(Calendar.HOUR)
        var seconds = calendar.get(Calendar.SECOND)

        canvas.save()
        canvas.rotate(seconds * 6f, centerX, centerY)
        canvas.drawLine(centerX, centerY + 50, centerX, centerY - 200, linePaintLitle)
        canvas.restore()

        canvas.save()
        canvas.rotate(hour * 30f + (minutes / 2), centerX, centerY)
        canvas.drawLine(centerX, centerY + 30, centerX, centerY - 150, linePaintMedium)
        canvas.restore()

        canvas.save()
        canvas.rotate(minutes * 6f, centerX, centerY)
        canvas.drawLine(centerX, centerY + 50, centerX, centerY - 200, linePaintMedium)
        canvas.restore()
    }

    private fun drawNumberClock(canvas: Canvas) {
        canvas.drawText(textNumberArr[2], (centerX - radius - 50), centerY + 15, numberPaint)
        canvas.drawText(textNumberArr[0], (centerX + radius + 20), centerY + 15, numberPaint)
        canvas.drawText(textNumberArr[3], (centerX - 30), centerY - radius - 20, numberPaint)
        canvas.drawText(textNumberArr[1], (centerX - 15), centerY + radius + 50, numberPaint)
    }

    private fun drawLinesInCircle(canvas: Canvas) {
        canvas.save()

        for (i in 0..60) {
            if (i % 15 == 0) {
                canvas.drawLine(startX, startY, stopX, stopY + 12, linePaintBig)
            } else if (i % 5 == 0) {
                canvas.drawLine(startX, startY, stopX, stopY + 10, linePaintMedium)
            } else {
                canvas.drawLine(startX, startY, stopX, stopY + 5, linePaintLitle)
            }
            canvas.rotate(6f, centerX, centerY)
        }
        canvas.restore()
    }

    private fun drawMyCircles(canvas: Canvas) {
        canvas.drawCircle(centerX, centerY, (radius + 70), circlePaintBig)
        canvas.drawCircle(centerX, centerY, radius + 65, circlePaintLitle)

        canvas.drawCircle(centerX, centerY, (radius + 5), circlePaintBig)
        canvas.drawCircle(centerX, centerY, radius, circlePaintLitle)
    }
}
package by.itacademy.myapp.dz4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import by.itacademy.myapp.R
import java.util.*
import kotlin.math.min

class Dz4MyClock : View {

    var calendar = Calendar.getInstance()
    var minutes = calendar.get(Calendar.MINUTE)
    var hour = calendar.get(Calendar.HOUR)
    var seconds = calendar.get(Calendar.SECOND)

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

    private val textNumberArr = arrayOf("3","6","9","12")

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

        linePaintBig.setStrokeWidth(10f)
        linePaintLitle.setStrokeWidth(2f)
        linePaintMedium.setStrokeWidth(4f)

        numberPaint.style = Paint.Style.FILL
        numberPaint.textSize = resources.getDimension(R.dimen.dz4NumberSize)
    }

    override fun onSizeChanged(weight: Int, height: Int, oldWeight: Int, oldHeight: Int) {
        super.onSizeChanged(weight, height, oldWeight, oldHeight)

        centerX = weight/2f
        centerY = height/2f

        radius = min(centerX, centerY)/1.5f

        startY = centerY-radius
        startX = centerX
        stopX =  centerX
        stopY =  centerY-radius
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        drawMyCircles(canvas)
        drawLinesInCircle(canvas)
        drawNumberClock(canvas)
        drawClockHands(canvas)
    }

    fun drawClockHands(canvas: Canvas){

        canvas.save()
        canvas.rotate(hour*30f+(minutes/2), centerX, centerY)
        canvas.drawLine(centerX, centerY+30, centerX, centerY-150, linePaintMedium)
        canvas.restore()

        canvas.save()
        canvas.rotate(minutes*6f, centerX, centerY)
        canvas.drawLine(centerX, centerY+50, centerX, centerY-200, linePaintMedium)
        canvas.restore()

        canvas.save()
        canvas.rotate(seconds*6f, centerX, centerY)
        canvas.drawLine(centerX, centerY+50, centerX, centerY-200, linePaintLitle)
        canvas.restore()
    }

    fun drawNumberClock(canvas: Canvas){
        canvas.drawText(textNumberArr[2], (centerX-radius-50), centerY+15, numberPaint)
        canvas.drawText(textNumberArr[0], (centerX+radius+20), centerY+15, numberPaint)
        canvas.drawText(textNumberArr[3], (centerX-30), centerY-radius-20, numberPaint)
        canvas.drawText(textNumberArr[1], (centerX-15), centerY+radius+50, numberPaint)
    }

    fun drawLinesInCircle(canvas: Canvas){
        canvas.save()

        for(i in 0..60){
            if (i%15 == 0){
                canvas.drawLine(startX, startY, stopX, stopY+12, linePaintBig)

            }else if(i%5 == 0){
                canvas.drawLine(startX, startY, stopX, stopY+10, linePaintMedium)
            }else{
                canvas.drawLine(startX, startY, stopX, stopY+5, linePaintLitle)
            }
            canvas.rotate(6f, centerX, centerY)
        }
        canvas.restore()
    }

    fun drawMyCircles(canvas: Canvas){
        canvas.drawCircle(centerX, centerY, (radius+70), circlePaintBig)
        canvas.drawCircle(centerX, centerY, radius+65, circlePaintLitle)

        canvas.drawCircle(centerX, centerY, (radius+5), circlePaintBig)
        canvas.drawCircle(centerX, centerY, radius, circlePaintLitle)
    }
}
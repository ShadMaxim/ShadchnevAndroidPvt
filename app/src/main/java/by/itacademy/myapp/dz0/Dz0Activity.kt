package by.itacademy.myapp.dz0

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.Button
import android.widget.TextView

import android.os.Bundle
import android.view.View
import by.itacademy.myapp.R

class Dz0Activity : Activity() {

    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var button: Button

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dzo)

        tv1 = findViewById<TextView>(R.id.tv1)
        tv2 = findViewById<TextView>(R.id.tv2)
        button = findViewById<Button>(R.id.button)

        fun changeText() {
            button.text = tv1.text
            tv1.text = tv2.text
            tv2.text = button.text
            button.setText(R.string.button1)
        }

        fun changeBack() {
            var back: Drawable = tv1.background
            tv1.setBackgroundDrawable(tv2.background)
            tv2.setBackgroundDrawable(back)
        }

        val onClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.tv1 -> {
                    changeText()
                    changeBack()
                }
                R.id.tv2 -> {
                    changeText()
                    changeBack()
                }
                R.id.button -> {
                    changeText()
                    changeBack()
                }
            }
        }
        var q: Drawable = tv1.background
        tv1.setBackgroundDrawable(tv2.background)
        tv2.setBackgroundDrawable(q)

        tv1.setOnClickListener(onClickListener)
        tv2.setOnClickListener(onClickListener)
        button.setOnClickListener(onClickListener)
    }
}
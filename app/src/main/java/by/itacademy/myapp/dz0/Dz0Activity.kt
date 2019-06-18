package by.itacademy.myapp.dz0

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.TextView
import android.os.Bundle
import android.view.View
import by.itacademy.myapp.R

class Dz0Activity : Activity() {

    private lateinit var oneTextView: TextView
    private lateinit var twoTextView: TextView
    private lateinit var oneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dzo)

        oneTextView = findViewById<TextView>(R.id.oneTextView)
        twoTextView = findViewById<TextView>(R.id.twoTextView)
        oneButton = findViewById<Button>(R.id.oneButton)

        val onClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.oneTextView -> {
                    changeText()
                    changeBack()
                }
                R.id.twoTextView -> {
                    changeText()
                    changeBack()
                }
                R.id.oneButton -> {
                    changeText()
                    changeBack()
                }
            }
        }
        var temp: Drawable = oneTextView.background
        oneTextView.setBackgroundDrawable(twoTextView.background)
        twoTextView.setBackgroundDrawable(temp)

        oneTextView.setOnClickListener(onClickListener)
        twoTextView.setOnClickListener(onClickListener)
        oneButton.setOnClickListener(onClickListener)
    }

    private fun changeText() {
        oneButton.text = oneTextView.text
        oneTextView.text = twoTextView.text
        twoTextView.text = oneButton.text
        oneButton.setText(R.string.button1)
    }

    private fun changeBack() {
        var back: Drawable = oneTextView.background
        oneTextView.setBackgroundDrawable(twoTextView.background)
        twoTextView.setBackgroundDrawable(back)
    }
}
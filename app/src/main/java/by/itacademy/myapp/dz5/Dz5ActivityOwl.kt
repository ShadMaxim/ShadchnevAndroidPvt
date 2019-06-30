package by.itacademy.myapp.dz5

import android.app.Activity
import android.os.Bundle
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import by.itacademy.myapp.R
import kotlinx.android.synthetic.main.activity_dz5.*

class Dz5ActivityOwl : Activity() {

    private lateinit var animationOwl: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5)

        animationOwl = findViewById<ImageView>(R.id.dz5AnimationImageView).background as AnimationDrawable
    }

    override fun onResume() {
        super.onResume()

        animationOwl.start()
    }

    override fun onPause() {
        super.onPause()

        animationOwl.stop()
    }
}
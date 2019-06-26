package by.itacademy.myapp.dz5

import android.app.Activity
import android.os.Bundle
import android.graphics.drawable.AnimationDrawable
import by.itacademy.myapp.R
import kotlinx.android.synthetic.main.activity_dz5.*

class Dz5ActivityOwl : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5)

        (dz5AnimationImageView.background as AnimationDrawable).start()
    }
}
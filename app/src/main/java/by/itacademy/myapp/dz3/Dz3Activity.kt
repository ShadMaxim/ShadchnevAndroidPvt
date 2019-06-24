package by.itacademy.myapp.dz3

import android.app.Activity
import android.os.Bundle
import by.itacademy.myapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_dz3.*

class Dz3Activity : Activity() {

    private val uriPicture = "https://i.imgur.com/5JJe3Ic.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)
    }

    override fun onStart() {
        super.onStart()
        startGlide()
    }

    fun startGlide(){
        Glide
            .with(this)
            .load(uriPicture)
            .circleCrop()
            .into(showFotoImageViewDz3)
    }
}
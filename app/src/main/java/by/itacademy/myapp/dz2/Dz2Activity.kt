package by.itacademy.myapp.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import by.itacademy.myapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_dz2.*
import java.lang.Exception

class Dz2Activity : Activity() {

    private lateinit var view: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        view = progressBarDz2

        val OnClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.loadButton -> {
                    showProgressBar()
                    workPicasso()
                }
            }
        }
        loadButton.setOnClickListener(OnClickListener)
    }

    private fun showProgressBar() {
        view.visibility = View.VISIBLE
    }

    private fun unShowProgressBar() {
        view.visibility = View.GONE
    }

    private fun workPicasso() {
        Picasso.get()
            .load(uriEditText.text.toString())
            .transform(CropCircleTransformation())
            .error(R.drawable.ic_close_black_24dp)
            .into(showImageView, object : Callback {
                override fun onSuccess() {
                    unShowProgressBar()
                }

                override fun onError(e: Exception?) {
                    unShowProgressBar()
                }
            })
    }
}
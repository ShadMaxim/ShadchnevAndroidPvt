package by.itacademy.myapp.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import by.itacademy.myapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_dz2.*

class Dz2Activity : Activity() {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        progressBar = findViewById(R.id.progressBarDz2)

        val OnClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.loadButton -> {
                    Picasso.get().load(uriEditText.text.toString())
                        .placeholder(R.drawable.ic_login_password)
                        .transform(CropCircleTransformation())
                        .error(R.drawable.ic_login_name)
                        .into(showImageView)
                }
            }
        }
        loadButton.setOnClickListener(OnClickListener)
    }
}
package by.itacademy.myapp.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import by.itacademy.myapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dz2.*

class Dz2Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        val OnClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.loadButton -> {
                    Picasso.get().load(uriEditText.text.toString())
                        .placeholder(R.drawable.ic_login_password)
                        .error(R.drawable.ic_login_name)
                        .into(showImageView)
                }
            }
        }
        loadButton.setOnClickListener(OnClickListener)
    }
}
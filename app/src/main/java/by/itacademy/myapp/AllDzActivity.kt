package by.itacademy.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.myapp.dz0.Dz0Activity
import by.itacademy.myapp.dz1.Dz1Activity
import kotlinx.android.synthetic.main.activity_alldz.*


class AllDzActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alldz)

        val onClickListener = View.OnClickListener{v->

            lateinit var intent : Intent

            when (v.id){
                R.id.buttonDz0 ->{
                    intent = Intent(this, Dz0Activity::class.java)
                }
                R.id.buttonDz1 ->{
                    intent = Intent(this, Dz1Activity::class.java)
                }
            }
            startActivity(intent)
        }
        buttonDz0.setOnClickListener(onClickListener)
        buttonDz1.setOnClickListener(onClickListener)
    }
}

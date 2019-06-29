package by.itacademy.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.myapp.dz0.Dz0Activity
import by.itacademy.myapp.dz1.Dz1Activity
import by.itacademy.myapp.dz2.Dz2Activity
import by.itacademy.myapp.dz2.Dz2LoginActivity
import by.itacademy.myapp.dz3.Dz3Activity
import by.itacademy.myapp.dz4.Dz4Activity
import kotlinx.android.synthetic.main.activity_alldz.*

class AllDzActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alldz)

        startAllDz()

    }

    private fun startAllDz(){
        val onClickListener = View.OnClickListener{v->
            lateinit var intent : Intent

            when (v.id){

                R.id.dz0Button ->{
                    intent = Intent(this, Dz0Activity::class.java)
                }

                R.id.dz1Button ->{
                    intent = Intent(this, Dz1Activity::class.java)
                }

                R.id.dz2Button ->{
                    intent = Intent(this, Dz2Activity::class.java)
                }

                R.id.dz2_2Button ->{
                    intent = Intent(this, Dz2LoginActivity::class.java)
                }

                R.id.dz3Button ->{
                    intent = Intent(this, Dz3Activity::class.java)
                }

                R.id.dz4Button ->{
                    intent = Intent(this, Dz4Activity::class.java)
                }
            }
            startActivity(intent)

        }
        dz0Button.setOnClickListener(onClickListener)
        dz1Button.setOnClickListener(onClickListener)
        dz2Button.setOnClickListener(onClickListener)
        dz2_2Button.setOnClickListener(onClickListener)
        dz3Button.setOnClickListener(onClickListener)
        dz4Button.setOnClickListener(onClickListener)

    }
}

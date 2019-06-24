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
            }
            startActivity(intent)

        }
        dz0Button.setOnClickListener(onClickListener)
        dz1Button.setOnClickListener(onClickListener)

    }
}

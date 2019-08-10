package by.itacademy.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.myapp.dz0.Dz0Activity
import by.itacademy.myapp.dz1.Dz1Activity
import by.itacademy.myapp.dz11.mvp.Dz11ManagerActivity
import by.itacademy.myapp.dz11.mvvm.Dz11Activity
import by.itacademy.myapp.dz2.Dz2Activity
import by.itacademy.myapp.dz2.Dz2LoginActivity
import by.itacademy.myapp.dz3.Dz3Activity
import by.itacademy.myapp.dz4.Dz4Activity
import by.itacademy.myapp.dz5.Dz5ActivityOwl
import by.itacademy.myapp.dz5.Dz5DiagramActivity
import by.itacademy.myapp.dz6.Dz6StudentListActivity
import by.itacademy.myapp.dz8.Dz8FragmManagerActivity
import by.itacademy.myapp.dz9.Dz9Activity
import kotlinx.android.synthetic.main.activity_alldz.*

class AllDzActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alldz)
        startAllDz()
    }

    private fun startAllDz() {
        val onClickListener = View.OnClickListener { v ->
            lateinit var intent: Intent

            when (v.id) {
                R.id.dz0Button -> {
                    intent = Intent(this, Dz0Activity::class.java)
                }
                R.id.dz1Button -> {
                    intent = Intent(this, Dz1Activity::class.java)
                }
                R.id.dz2part1Button -> {
                    intent = Intent(this, Dz2Activity::class.java)
                }
                R.id.dz2part2Button -> {
                    intent = Intent(this, Dz2LoginActivity::class.java)
                }
                R.id.dz3Button -> {
                    intent = Intent(this, Dz3Activity::class.java)
                }
                R.id.dz4Button -> {
                    intent = Intent(this, Dz4Activity::class.java)
                }
                R.id.dz5part1Button -> {
                    intent = Intent(this, Dz5ActivityOwl::class.java)
                }
                R.id.dz5part2Button -> {
                    intent = Intent(this, Dz5DiagramActivity::class.java)
                }
                R.id.dz6Button -> {
                    intent = Intent(this, Dz6StudentListActivity::class.java)
                }
                R.id.dz9Button -> {
                    intent = Intent(this, Dz9Activity::class.java)
                }
                R.id.dz8Button -> {
                    intent = Intent(this, Dz8FragmManagerActivity::class.java)
                }
                R.id.dz11path1Button -> {
                    intent = Intent(this, Dz11ManagerActivity::class.java)
                }
                R.id.dz11path2Button -> {
                    intent = Intent(this, Dz11Activity::class.java)
                }
            }

            startActivity(intent)
        }

        dz0Button.setOnClickListener(onClickListener)
        dz1Button.setOnClickListener(onClickListener)
        dz2part1Button.setOnClickListener(onClickListener)
        dz2part2Button.setOnClickListener(onClickListener)
        dz3Button.setOnClickListener(onClickListener)
        dz4Button.setOnClickListener(onClickListener)
        dz5part1Button.setOnClickListener(onClickListener)
        dz5part2Button.setOnClickListener(onClickListener)
        dz6Button.setOnClickListener(onClickListener)
        dz8Button.setOnClickListener(onClickListener)
        dz9Button.setOnClickListener(onClickListener)
        dz11path1Button.setOnClickListener(onClickListener)
        dz11path2Button.setOnClickListener(onClickListener)
    }
}

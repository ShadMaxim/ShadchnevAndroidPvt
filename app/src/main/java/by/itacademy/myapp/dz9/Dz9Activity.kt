package by.itacademy.myapp.dz9

import android.app.Activity
import android.os.Bundle
import by.itacademy.myapp.R

class Dz9Activity : Activity() {

    private val carRepository: CarRepository = providerCarRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)
    }
}
package by.itacademy.myapp.dz9

import by.itacademy.myapp.dz9.entity.CoordParams

interface CarRepository {

    fun getCarsByCoord(params: CoordParams)
}
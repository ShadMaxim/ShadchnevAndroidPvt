package by.itacademy.myapp.dz12.cars.provider

import by.itacademy.myapp.dz9.entity.CarResponse
import by.itacademy.myapp.dz9.entity.CoordParams
import io.reactivex.Single

interface Dz12CarRepository {

    fun getCarsByCoord(params: CoordParams): Single<CarResponse>
}
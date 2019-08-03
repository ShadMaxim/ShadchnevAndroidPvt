package by.itacademy.myapp.dz12.cars.provider

import by.itacademy.myapp.dz9.entity.CarResponse
import by.itacademy.myapp.dz9.entity.CoordParams
import io.reactivex.Observable

interface Dz12CarRepository {

    fun getCarsByCoord(params: CoordParams): Observable<CarResponse>
}
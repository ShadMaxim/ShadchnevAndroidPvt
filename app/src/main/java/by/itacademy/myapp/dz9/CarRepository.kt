package by.itacademy.myapp.dz9

import by.itacademy.myapp.dz9.entity.CoordParams
import by.itacademy.myapp.dz9.entity.Poi

interface CarRepository {

    fun getCarsByCoord(params: CoordParams, listener: CarRepositoryResult)
}

interface CarRepositoryResult {

    fun onSuccessfully(listPoi: List<Poi>)
    fun onError(throwable: Throwable)
}

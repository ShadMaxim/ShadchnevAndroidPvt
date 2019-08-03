package by.itacademy.myapp.dz12.cars.provider

import by.itacademy.myapp.dz9.entity.CarResponse
import by.itacademy.myapp.dz9.entity.CoordParams
import io.reactivex.Observable

class Dz12CarRepositoryRemote(private val api: Dz12Api) : Dz12CarRepository {

    override fun getCarsByCoord(params: CoordParams): Observable<CarResponse> {
        return api.getCarsByCoord(
            params.coord1.latitude,
            params.coord1.longitude,
            params.coord2.latitude,
            params.coord2.longitude
        )
    }
}
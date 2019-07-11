package by.itacademy.myapp.dz9

import by.itacademy.myapp.dz9.entity.CoordParams

class CarRepositoryRemote(private val api: Api) : CarRepository {
    override fun getCarsByCoord(params: CoordParams) { // add implementation api.getCarsDyCoord().
    }
}
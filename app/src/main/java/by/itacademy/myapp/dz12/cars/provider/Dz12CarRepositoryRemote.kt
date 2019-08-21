package by.itacademy.myapp.dz12.cars.provider

import android.util.Log
import by.itacademy.myapp.dz15.PoiDao
import by.itacademy.myapp.dz9.entity.CarResponse
import by.itacademy.myapp.dz9.entity.CoordParams
import io.reactivex.Single

class Dz12CarRepositoryRemote(private val api: Dz12Api, private var poiDao: PoiDao) : Dz12CarRepository {

    override fun getCarsByCoord(params: CoordParams): Single<CarResponse> {
        return api
            .getCarsByCoord(
                params.coord1.latitude,
                params.coord1.longitude,
                params.coord2.latitude,
                params.coord2.longitude

            ).doOnSuccess {

                poiDao.insert(it.poiList)
            }.onErrorResumeNext {

                if (poiDao.get().isEmpty()) {

                    Log.e("AAA", it.toString())
                    Single.error(it)
                } else {

                    Single.just(CarResponse(poiDao.get()))
                }
            }
    }
}
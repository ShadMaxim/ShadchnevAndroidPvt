package by.itacademy.myapp.dz12.cars.provider

import android.util.Log
import by.itacademy.myapp.app.App
import by.itacademy.myapp.dz9.entity.CarResponse
import by.itacademy.myapp.dz9.entity.CoordParams
import io.reactivex.Single

class Dz12CarRepositoryRemote(private val api: Dz12Api) : Dz12CarRepository {

    private val poiDao = App.instance.getDataBase().getPoiDao()

    override fun getCarsByCoord(params: CoordParams): Single<CarResponse> {
        return api
            .getCarsByCoord(
                params.coord1.latitude,
                params.coord1.longitude,
                params.coord2.latitude,
                params.coord2.longitude

            ).doOnSuccess {
                poiDao.insert(it.poiList)
                Log.e("doOnSuccess poiDao = ", poiDao.get().toString())
                Log.e("doOnSuccess it = ", it.toString())
            }.onErrorResumeNext {
                Single.just(CarResponse(poiDao.get()))
            }
    }
}
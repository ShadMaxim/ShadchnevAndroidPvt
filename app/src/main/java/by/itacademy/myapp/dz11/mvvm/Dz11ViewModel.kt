package by.itacademy.myapp.dz11.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.myapp.dz9.CarRepository
import by.itacademy.myapp.dz9.CarRepositoryResult
import by.itacademy.myapp.dz9.entity.CoordParams
import by.itacademy.myapp.dz9.entity.Coordinate
import by.itacademy.myapp.dz9.entity.Poi
import by.itacademy.myapp.dz9.provideCarRepository

class Dz11ViewModel : ViewModel(), CarRepositoryResult {

    val state: MutableLiveData<Dz11State> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Dz11State>() }
    val carItem: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Poi>() }
    private val carRepository: CarRepository = provideCarRepository()
    private lateinit var poiList: List<Poi>

    fun drawMap() {
        val params = CoordParams(
            Coordinate(3333.0, 342.0),
            Coordinate(3242.0, 3453.0)
        )
        carRepository.getCarsByCoord(params, this)
    }

    override fun onSuccessfully(listPoi: List<Poi>) {
        state.value = Dz11State.Data(listPoi)
        poiList = listPoi
    }

    override fun onError(throwable: Throwable) {
        state.value = Dz11State.Error(throwable)
    }

    fun onCarTouch(item: Poi) {
        carItem.value = item
    }
}
package by.itacademy.myapp.dz12.cars

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.myapp.dz12.cars.provider.Dz12CarRepository
import by.itacademy.myapp.dz12.cars.provider.dz12provideCarRepository
import by.itacademy.myapp.dz9.entity.CoordParams
import by.itacademy.myapp.dz9.entity.Coordinate
import by.itacademy.myapp.dz9.entity.Poi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12ViewModel : ViewModel() {

    val state: MutableLiveData<Dz12State> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Dz12State>() }
    val carItem: MutableLiveData<Poi> by lazy(LazyThreadSafetyMode.NONE) { MutableLiveData<Poi>() }

    var disposable: Disposable? = null
    private val carRepository: Dz12CarRepository = dz12provideCarRepository()

    init {
        val params = CoordParams(
            Coordinate(3333.0, 342.0),
            Coordinate(3242.0, 3453.0)
        )

        disposable = carRepository
            .getCarsByCoord(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                state.value = Dz12State.Data(it.poiList)
            }, {

                state.value = Dz12State.Error(it)
            })
    }

    fun onCarTouch(item: Poi) {
        carItem.value = item
    }

    override fun onCleared() {
        super.onCleared()
    }
}
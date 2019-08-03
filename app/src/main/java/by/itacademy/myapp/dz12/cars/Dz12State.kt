package by.itacademy.myapp.dz12.cars

import by.itacademy.myapp.dz9.entity.Poi

sealed class Dz12State {

    class Data(val dataList: List<Poi>) : Dz12State()
    class Error(val throwable: Throwable) : Dz12State()
}
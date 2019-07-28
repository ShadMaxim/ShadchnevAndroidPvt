package by.itacademy.myapp.dz11.mvvm

import by.itacademy.myapp.dz9.entity.Poi

sealed class Dz11State {

    class Data(val dataList: List<Poi>) : Dz11State()
    class Error(val throwable: Throwable) : Dz11State()
}
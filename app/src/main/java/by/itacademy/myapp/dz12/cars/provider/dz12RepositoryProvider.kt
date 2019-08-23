package by.itacademy.myapp.dz12.cars.provider

import by.itacademy.myapp.app.App

fun dz12provideCarRepository(): Dz12CarRepository {

    val poiDao = App.instance.getDataBase().getPoiDao()

    return Dz12CarRepositoryRemote(
        Dz12NetProvider.provideApi(
            Dz12NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                Dz12NetProvider.provideOkHttp(),
                Dz12NetProvider.provideGson()
            )
        ), poiDao
    )
}
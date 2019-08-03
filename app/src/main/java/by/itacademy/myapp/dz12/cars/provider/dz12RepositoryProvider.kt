package by.itacademy.myapp.dz12.cars.provider

fun dz12provideCarRepository(): Dz12CarRepository {

    return Dz12CarRepositoryRemote(
        Dz12NetProvider.provideApi(
            Dz12NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                Dz12NetProvider.provideOkHttp(),
                Dz12NetProvider.provideGson()
            )
        )
    )
}
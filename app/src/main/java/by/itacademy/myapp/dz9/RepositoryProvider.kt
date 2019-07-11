package by.itacademy.myapp.dz9

fun providerCarRepository(): CarRepository {

    return CarRepositoryRemote(
        NetProvaider.gprovidertApi(

            NetProvaider.gprovidertRetrofit(
                "http://kiparo.ru/",
                NetProvaider.gprovidertOkHttp(),
                NetProvaider.providerGson()
            )

        )
    )
}
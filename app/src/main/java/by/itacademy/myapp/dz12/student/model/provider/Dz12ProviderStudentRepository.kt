package by.itacademy.myapp.dz12.student.model.provider

private val BASE_URL = "http://api.backendless.com/2E2FD0CF-791C-51CC-FFED-679EF05FFD00/8EBE3D6E-0DD9-DFA2-FFB2-6CD00161ED00/"

fun provideStudentRepository(): Dz12StudentRepository {

    return Dz12StudentRepositoryRemote(
        Dz12NetProvider.providertStudentApi(
            Dz12NetProvider.providertRetrofit(
                BASE_URL,
                Dz12NetProvider.providertOkHttp(),
                Dz12NetProvider.providerGson()
            )
        )
    )
}
package by.itacademy.myapp.dz12.cars.provider

import by.itacademy.myapp.dz9.entity.CarResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Dz12Api {

    @GET("t/fake-api/car-service.php")
    fun getCarsByCoord(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Observable<CarResponse>
}

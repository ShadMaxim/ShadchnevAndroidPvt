package by.itacademy.myapp.dz12.student.model.provider

import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentData
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.http.Body

interface Dz12StudentApi {

    @GET("data/student")
    fun getStudent(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int
    ): Observable<List<Dz12StudentData>>

    @GET("data/student/{objectId}")
    fun getStudentById(
        @Path("objectId") objectId: String
    ): Observable<Dz12StudentData>

    @POST("data/student")
    fun createStudent(
        @Body studentData: Dz12StudentData
    ): Observable<Dz12StudentData>

    @PUT("data/student")
    fun updateStudent(
        @Body studentData: Dz12StudentData
    ): Observable<Dz12StudentData>

    @DELETE("data/student/{objectId}")
    fun deleteStudent(
        @Path("objectId") objectId: String
    ): Completable/*Observable<DeleteClass>*/

    @GET("data/student")
    fun searchByCharOrName(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") charName: String
    ): Observable<List<Dz12StudentData>>
}
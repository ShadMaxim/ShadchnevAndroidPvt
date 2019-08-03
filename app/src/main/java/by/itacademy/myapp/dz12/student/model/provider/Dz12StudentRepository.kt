package by.itacademy.myapp.dz12.student.model.provider

import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentData
import io.reactivex.Completable
import io.reactivex.Observable

interface Dz12StudentRepository {

    fun get(pageSize: Int, offset: Int): Observable<List<Dz12StudentData>>
    fun getById(id: String): Observable<Dz12StudentData>
    fun create(student: Dz12StudentData): Observable<Dz12StudentData>
    fun update(student: Dz12StudentData): Observable<Dz12StudentData>
    fun delete(id: String): Completable/*Observable<DeleteClass>*/
    fun search(pageSize: Int, offset: Int, charName: String): Observable<List<Dz12StudentData>>
}
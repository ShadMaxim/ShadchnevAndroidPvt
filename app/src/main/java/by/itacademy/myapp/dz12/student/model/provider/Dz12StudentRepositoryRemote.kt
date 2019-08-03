package by.itacademy.myapp.dz12.student.model.provider

import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentData
import io.reactivex.Completable
import io.reactivex.Observable

class Dz12StudentRepositoryRemote(private var api: Dz12StudentApi) : Dz12StudentRepository {

    override fun get(pageSize: Int, offset: Int): Observable<List<Dz12StudentData>> {
        return api.getStudent(pageSize, offset)
    }

    override fun getById(id: String): Observable<Dz12StudentData> {
        return api.getStudentById(id)
    }

    override fun create(student: Dz12StudentData): Observable<Dz12StudentData> {
        return api.createStudent(
            studentData = student
        )
    }

    override fun update(student: Dz12StudentData): Observable<Dz12StudentData> {
        return api.updateStudent(
            studentData = student
        )
    }

    override fun delete(id: String): Completable {
        return api.deleteStudent(id)
    }

    override fun search(pageSize: Int, offset: Int, charName: String): Observable<List<Dz12StudentData>> {
        return api.searchByCharOrName(pageSize, offset, "name LIKE '%$charName%'")
    }
}
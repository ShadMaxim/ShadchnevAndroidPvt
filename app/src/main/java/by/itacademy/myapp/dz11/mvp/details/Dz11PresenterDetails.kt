package by.itacademy.myapp.dz11.mvp.details

import by.itacademy.myapp.dz11.mvp.model.Dz11DataStudent
import by.itacademy.myapp.dz6.Dz6Student
class Dz11PresenterDetails : Dz11BasePresenterDetails {

    private var view: Dz11ViewDetails? = null
    private lateinit var student: Dz6Student

    override fun setView(view: Dz11ViewDetails) {
        this.view = view
    }

    override fun getStudentById(idStudent: Long): Dz6Student {
        student = Dz11DataStudent.findStudentById(idStudent)
        view?.showStudent(student)
        return student
    }

    override fun clickDelete(idStudent: Long) {
        student = Dz11DataStudent.findStudentById(idStudent)
        Dz11DataStudent.deleteStudentOfList(student)
    }

    override fun detachView() {
        this.view = null
    }
}
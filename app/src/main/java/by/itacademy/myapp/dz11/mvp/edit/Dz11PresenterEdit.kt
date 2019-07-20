package by.itacademy.myapp.dz11.mvp.edit

import by.itacademy.myapp.dz11.mvp.model.Dz11DataStudent
import by.itacademy.myapp.dz6.Dz6Student

class Dz11PresenterEdit : Dz11BasePresenterEdit {

    private var view: Dz11ViewEdit? = null
    private lateinit var student: Dz6Student

    override fun setView(view: Dz11ViewEdit) {
        this.view = view
    }

    override fun getStudentById(idStudent: Long) {
        student = Dz11DataStudent.findStudentById(idStudent)
        view?.showStudent(student)
    }

    override fun saveEditedStudent(idStudent: Long, name: String, age: Int, url: String) {
        student = Dz11DataStudent.findStudentById(idStudent)
        val studentDataEdited = Dz6Student(idStudent, url, name, age)
        Dz11DataStudent.editStudent(student, studentDataEdited)
    }

    override fun saveNewStudent(name: String, age: Int, url: String) {
        student = Dz6Student(Dz11DataStudent.creatureNewId(), url, name, age)
        Dz11DataStudent.addNewStudent(student)
    }

    override fun detachView() {
        this.view = null
    }
}
package by.itacademy.myapp.dz11.mvp.edit

import android.util.Patterns
import by.itacademy.myapp.dz11.mvp.model.Dz11DataStudent
import by.itacademy.myapp.dz6.Dz6Student

class Dz11PresenterEdit : Dz11BasePresenterEdit {

    private var view: Dz11ViewEdit? = null
    private val pattern = Patterns.WEB_URL

    companion object {
        var textErrorCorrectDataFiling = ""
    }

    override fun setView(view: Dz11ViewEdit) {
        this.view = view
    }

    override fun getStudentById(idStudent: Long) {
        val student = Dz11DataStudent.findStudentById(idStudent)
        view?.showStudent(student)
    }

    override fun saveEditedStudent(idStudent: Long, name: String, age: Int, url: String) {
        val student = Dz11DataStudent.findStudentById(idStudent)
        val studentDataEdited = Dz6Student(idStudent, url, name, age)
        Dz11DataStudent.editStudent(student, studentDataEdited)
    }

    override fun saveNewStudent(name: String, age: Int, url: String) {
        val student = Dz6Student(Dz11DataStudent.creatureNewId(), url, name, age)
        Dz11DataStudent.addNewStudent(student)
    }

    override fun detachView() {
        this.view = null
    }

    override fun correctDataFilling(name: String, age: Int, url: String): Boolean {
        if (!pattern.matcher(url).matches()) {
            textErrorCorrectDataFiling = "ERROR( URL ): Not valid URL"
            return false
        } else if (age == null) {
            textErrorCorrectDataFiling = "ERROR( AGE ): Not valid Age, must be >= 0"
            return false
        } else if (name.isEmpty()) {
            textErrorCorrectDataFiling = "ERROR( NAME ): Not valid Name, must be filled"
            return false
        } else {
            return true
        }
    }
}
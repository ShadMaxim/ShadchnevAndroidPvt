package by.itacademy.myapp.dz12.student.edit

interface Dz12BasePresenterEdit {

    fun setView(view: Dz12ViewEdit)
    fun detachView()
    fun getStudentById(idStudent: String)
    fun goToSaveOrEdit(idStudent: String, name: String, imageUrl: String, age: String)
}
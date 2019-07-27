package by.itacademy.myapp.dz11.mvp.edit

interface Dz11BasePresenterEdit {

    fun setView(view: Dz11ViewEdit)
    fun detachView()
    fun getStudentById(idStudent: Long)
    fun saveEditedStudent(idStudent: Long, name: String, age: Int, url: String)
    fun saveNewStudent(name: String, age: Int, url: String)
    fun correctDataFilling(name: String, age: Int, url: String): Boolean
}
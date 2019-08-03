package by.itacademy.myapp.dz12.student.details

interface Dz12BasePresenterDetails {

    fun setView(view: Dz12ViewDetails)
    fun getStudentById(idStudent: String)
    fun deleteOfServerAndSingleton(idStudent: String)
    fun detachView()
    fun goEdit(idStudent: String)
    fun goBackStack()
}
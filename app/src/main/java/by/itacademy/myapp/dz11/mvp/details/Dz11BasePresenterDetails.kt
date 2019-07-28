package by.itacademy.myapp.dz11.mvp.details

interface Dz11BasePresenterDetails {

    fun setView(view: Dz11ViewDetails)
    fun getStudentById(idStudent: Long)
    fun clickDelete(idStudent: Long)
    fun detachView()
}
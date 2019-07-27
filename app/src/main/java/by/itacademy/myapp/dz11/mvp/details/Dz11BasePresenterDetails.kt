package by.itacademy.myapp.dz11.mvp.details

import by.itacademy.myapp.dz6.Dz6Student

interface Dz11BasePresenterDetails {

    fun setView(view: Dz11ViewDetails)
    fun getStudentById(idStudent: Long): Dz6Student
    fun clickDelete(idStudent: Long)
    fun detachView()
}
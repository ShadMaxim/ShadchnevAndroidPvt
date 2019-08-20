package by.itacademy.myapp.dz12.student.edit

import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData

interface Dz12ViewEdit {

    fun showStudent(student: Dz12StudentData)
    fun showToastUpdateOk(text: String)
    fun showToastUpdateError(text: String)
    fun showToastCreateError(text: String)
    fun showToastCreateOk(text: String)
    fun showToastErrorFilling(text: String)
    fun showErrorLoadById(text: String)
    fun updatePage()

    fun showProgressBar()
    fun notShowProgressBar()
}
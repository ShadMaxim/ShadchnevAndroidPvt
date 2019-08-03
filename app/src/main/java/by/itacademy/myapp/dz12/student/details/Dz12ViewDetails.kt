package by.itacademy.myapp.dz12.student.details

import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentData

interface Dz12ViewDetails {

    fun showStudent(student: Dz12StudentData)
    fun showToastOk(text: String)
    fun showToastError(text: String)
    fun updatePage()
    fun goToEdit(idStudent: String)
    fun ifNullStudent()
}
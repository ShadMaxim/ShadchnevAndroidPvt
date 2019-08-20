package by.itacademy.myapp.dz12.student.list

import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData

interface Dz12ViewList {

    fun showNewList(list: MutableList<Dz12StudentData>)
    fun showToastGetOk(text: String)
    fun showToastGetError(text: String)
    fun showTimer(number: Long)
    fun showTimerError(text: String)
    fun addButtonClick()

    fun showProgressBar()
    fun notShowProgressBar()
}
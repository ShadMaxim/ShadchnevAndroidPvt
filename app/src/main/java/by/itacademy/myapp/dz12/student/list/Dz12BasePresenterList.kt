package by.itacademy.myapp.dz12.student.list

import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData

interface Dz12BasePresenterList {

    fun setView(view: Dz12ViewList)
    fun detachView()
    fun newListForSearch(text: String): MutableList<Dz12StudentData>
    fun loadList(text: String)
    fun reloadRecycler()
    // fun timerToast()
    fun goToAddButton()
}
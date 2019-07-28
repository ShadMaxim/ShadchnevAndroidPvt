package by.itacademy.myapp.dz11.mvp.list

import by.itacademy.myapp.dz6.Dz6Student

interface Dz11BasePresenterList {

    fun setView(view: Dz11ViewList)
    fun detachView()
    fun newListForSearch(text: String): MutableList<Dz6Student>
}
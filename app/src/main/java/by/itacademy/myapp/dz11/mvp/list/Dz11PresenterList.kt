package by.itacademy.myapp.dz11.mvp.list

import by.itacademy.myapp.dz11.mvp.model.Dz11DataStudent
import by.itacademy.myapp.dz6.Dz6Student

class Dz11PresenterList : Dz11BasePresenterList {

    private var view: Dz11ViewList? = null
    private lateinit var student: Dz6Student

    override fun setView(view: Dz11ViewList) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun newListForSearch(text: String): MutableList<Dz6Student> {
        val list = Dz11DataStudent.search(text) as MutableList<Dz6Student>
        view?.showNewList(list)
        return list
    }

    /*fun update(text: String){
        view!!.updateRecyclerList(text)
    }*/
}
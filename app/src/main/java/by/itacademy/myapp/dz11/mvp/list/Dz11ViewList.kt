package by.itacademy.myapp.dz11.mvp.list

import by.itacademy.myapp.dz6.Dz6Student

interface Dz11ViewList {

    // fun updateRecyclerList(text: String)
    fun showNewList(list: MutableList<Dz6Student>)
}
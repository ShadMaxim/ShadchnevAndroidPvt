package by.itacademy.myapp.dz11.mvp.edit

import by.itacademy.myapp.dz6.Dz6Student

interface Dz11ViewEdit {

    fun correctDataFilling(name: String, age: Int, url: String): Boolean
    fun showStudent(student: Dz6Student)
}
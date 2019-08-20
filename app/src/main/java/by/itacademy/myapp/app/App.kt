package by.itacademy.myapp.app

import android.app.Application
import androidx.room.Room
import by.itacademy.myapp.dz15.AppDataBase

class App : Application() {

    private lateinit var dataBase: AppDataBase

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        dataBase = Room
            .databaseBuilder(this, AppDataBase::class.java, "dataBase")
            .build()
    }

    fun getInstance(): App {
        return instance
    }

    fun getDataBase(): AppDataBase {
        return dataBase
    }
}

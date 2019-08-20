package by.itacademy.myapp.dz15

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.itacademy.myapp.dz9.entity.Poi

@Database(entities = [Poi::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    companion object{

        var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase{

            if (instance == null){
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "name")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
    abstract fun getPoiDao(): PoiDao
}
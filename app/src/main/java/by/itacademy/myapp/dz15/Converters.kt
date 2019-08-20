package by.itacademy.myapp.dz15

import androidx.room.TypeConverter
import by.itacademy.myapp.dz9.entity.FleeType

class Converters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun getFromFleetTypeDb(value: FleeType): String {
            return value.name
        }

        @TypeConverter
        @JvmStatic
        fun toFleetTypeDb(value: String): FleeType {
            return FleeType.valueOf(value)
        }
    }
}
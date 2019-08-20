package by.itacademy.myapp.dz12.student.model.data

import com.google.gson.annotations.SerializedName

data class Dz12StudentData(

    @SerializedName("objectId")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("age")
    val age: String
)
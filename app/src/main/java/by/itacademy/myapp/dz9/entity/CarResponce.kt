package by.itacademy.myapp.dz9.entity

import com.google.gson.annotations.SerializedName

class CarResponce(

    @SerializedName("poilist")
    val poiList: List<Poi>
)
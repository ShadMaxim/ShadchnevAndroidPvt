package by.itacademy.myapp.dz9.entity

import com.google.gson.annotations.SerializedName

class Poi(
    @SerializedName("id")
    val id: String,

    @SerializedName("coordinate")
    val coordinate: Coordinate?,

    @SerializedName("fleeType")
    val fleeType: FleeType?,

    @SerializedName("healibg")
    val healibg: Double?
)
package by.itacademy.myapp.dz9.entity

import com.google.gson.annotations.SerializedName

data class Poi(

    @SerializedName("id")
    val id: String,

    @SerializedName("coordinate")
    val coordinate: Coordinate?,

    @SerializedName("fleetType")
    val fleeType: FleeType?,

    @SerializedName("heading")
    val heading: Double?
)
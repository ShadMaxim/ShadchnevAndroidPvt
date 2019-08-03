package by.itacademy.myapp.dz9

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz9.entity.FleeType
import by.itacademy.myapp.dz9.entity.Poi

class Dz9ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image = itemView.findViewById<ImageView>(R.id.dz9TextItem1)
    private val idCar = itemView.findViewById<TextView>(R.id.dz9TextItem2)
    private val fleetType = itemView.findViewById<TextView>(R.id.dz9TextItem3)

    fun bind(item: Poi) {
        if (item.fleeType == FleeType.TAXI) {
            image.setImageResource(R.drawable.ic_near_me_black_24dp)
        } else {
            image.setImageResource(R.drawable.ic_send_black_24dp)
        }
        idCar.text = item.id
        fleetType.text = item.fleeType.toString()
    }
}
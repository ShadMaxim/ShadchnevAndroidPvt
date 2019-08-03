package by.itacademy.myapp.dz9
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz9.entity.Poi

class Dz9Adapter(private var items: List<Poi>, private var listener: ClickListener)
    : RecyclerView.Adapter<Dz9ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz9ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_for_recyclerview_dz9, parent, false)

        val holder = Dz9ViewHolder(view)
        holder.itemView.setOnClickListener {
            listener.onCarsClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Dz9ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface ClickListener {
        fun onCarsClick(item: Poi)
    }
}
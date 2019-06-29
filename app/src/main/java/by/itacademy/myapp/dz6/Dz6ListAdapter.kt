package by.itacademy.myapp.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R

class Dz6ListAdapter(
    private val item: List<Student>,
    private val listner: ClickListner
) : RecyclerView.Adapter<Dz6ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6ListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_for_recyclerview, parent, false)

        val holder = Dz6ListViewHolder(view)
        holder.itemView.setOnClickListener {
            listner.onStudentClick(item[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz6ListViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

    interface ClickListner {
        fun onStudentClick(item: Student)
    }
}
package by.itacademy.myapp.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R

class Dz6ListAdapter
    (private var items: MutableList<Student>, private val listener: ClickListener)
    : RecyclerView.Adapter<Dz6ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6ListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_for_recyclerview, parent, false)

        val holder = Dz6ListViewHolder(view)
        holder.itemView.setOnClickListener {
            listener.onStudentClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz6ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list: MutableList<Student>) {
        items = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onStudentClick(item: Student)
    }
}
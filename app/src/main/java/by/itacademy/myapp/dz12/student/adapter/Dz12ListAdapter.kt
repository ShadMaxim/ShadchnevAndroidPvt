package by.itacademy.myapp.dz12.student.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentData

class Dz12ListAdapter
    (private var items: MutableList<Dz12StudentData>, private val listener: ClickListener)
    : RecyclerView.Adapter<Dz12ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz12ListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_for_recyclerview, parent, false)

        val holder = Dz12ListViewHolder(view)
        holder.itemView.setOnClickListener {
            listener.onStudentClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz12ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list: MutableList<Dz12StudentData>) {
        items = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onStudentClick(item: Dz12StudentData)
    }
}
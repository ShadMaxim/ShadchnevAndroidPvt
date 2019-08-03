package by.itacademy.myapp.dz12.student.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentData
import by.itacademy.myapp.util.picassoLoaderCircle

class Dz12ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val avatarMiniImageView = itemView.findViewById<ImageView>(R.id.avatarMiniImageView)
    private val nameForAvatarTextView = itemView.findViewById<TextView>(R.id.nameForAvatarTextView)

    fun bind(student: Dz12StudentData) {

        picassoLoaderCircle(student.imageUrl, avatarMiniImageView)
        nameForAvatarTextView.text = student.name
    }
}
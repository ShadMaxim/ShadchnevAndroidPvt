package by.itacademy.myapp.dz6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.util.picassoLoaderCircle

class Dz6ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val avatarMiniImageView = itemView.findViewById<ImageView>(R.id.avatarMiniImageView)
    private val nameForAvatarTextView = itemView.findViewById<TextView>(R.id.nameForAvatarTextView)

    fun bind(item: Student) {

        picassoLoaderCircle(item.url!!, avatarMiniImageView)
        nameForAvatarTextView.text = item.name
    }
}
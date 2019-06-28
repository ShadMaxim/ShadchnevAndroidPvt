package by.itacademy.myapp.dz6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R

class Dz6ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val avatarImageView = itemView.findViewById<ImageView>(R.id.avatarMiniImageView)
    private val nameForAvatarTextView = itemView.findViewById<TextView>(R.id.nameForAvatarTextView)

    fun bind(item: Student){

        avatarImageView.setImageResource(R.drawable.ic_login_name)
        nameForAvatarTextView.text = item.name

    }

}
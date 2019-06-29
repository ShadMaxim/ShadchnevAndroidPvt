package by.itacademy.myapp.dz6

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import kotlinx.android.synthetic.main.activity_dz6_recycler.*

class Dz6Activity : Activity(), Dz6ListAdapter.ClickListner {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_recycler)

        startActivity()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        val items = listOf(
            Student("1", "http://pngimg.com/uploads/trollface/trollface_PNG25.png", "Petr", 22),
            Student("2", "http://pngimg.com/uploads/trollface/trollface_PNG11.png", "Sacha", 21),
            Student("3", "http://pngimg.com/uploads/trollface/trollface_PNG3.png", "Vania", 30),
            Student("4", "http://pngimg.com/uploads/trollface/trollface_PNG17.png", "Lesha", 55),
            Student("5", "http://pngimg.com/uploads/trollface/trollface_PNG22.png", "Maxim", 30),
            Student("6", "http://pngimg.com/uploads/trollface/trollface_PNG29.png", "Danila", 21),
            Student("7", "http://pngimg.com/uploads/trollface/trollface_PNG31.png", "Sergey", 30),
            Student("8", "http://pngimg.com/uploads/trollface/trollface_PNG39.png", "Nikolai", 55),
            Student("9", "http://pngimg.com/uploads/trollface/trollface_PNG44.png", "Gosha", 45)
        )
        recyclerView.adapter = Dz6ListAdapter(items, this)
    }

    override fun onStudentClick(item: Student) {
        val intent = Intent(this, Dz6ProfilActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, " You are viewing information about " + item.name, Toast.LENGTH_SHORT).show()
    }

    fun startActivity() {
        addImageView.setOnClickListener {
            val intent = Intent(this, Dz6EditProfileActivity::class.java)
            Toast.makeText(this, "You have entered the profile edit window", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}
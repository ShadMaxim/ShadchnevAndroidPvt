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

    val idKey = "ID_KEY"

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_recycler)

        startActivity()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = Dz6ListAdapter(StudentsSinglton.instance.getStudents(), this)
    }

    override fun onStudentClick(item: Student) {
        val intent = Intent(this, Dz6ProfilActivity::class.java)
        intent.putExtra(idKey, item.id)
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
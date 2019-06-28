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
            Student("1", "", "New       1111111111 one po'gfjer'gj", 5),
            Student("2", "", "New      1111121212222222222222222211111 one po'gfjer'gj", 21) ,
            Student("3", "", "New    32222222222222 one po'gfjer'gj",30),
            Student("4", "", "New    33333333one poqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq'gfjer'gj", 55),
            Student("1", "", "New       1111111111 one po'gfjer'gj", 5),
            Student("2", "", "New      1111121212222222222222222211111 one po'gfjer'gj", 21) ,
            Student("3", "", "New    32222222222222 one po'gfjer'gj",30),
            Student("4", "", "New    33333333one poqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq'gfjer'gj", 55),
            Student("1", "", "New       1111111111 one po'gfjer'gj", 5),
            Student("2", "", "New      1111121212222222222222222211111 one po'gfjer'gj", 21) ,
            Student("3", "", "New    32222222222222 one po'gfjer'gj",30),
            Student("4", "", "New    33333333one poqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq'gfjer'gj", 55),
            Student("1", "", "New       1111111111 one po'gfjer'gj", 5),
            Student("2", "", "New      1111121212222222222222222211111 one po'gfjer'gj", 21) ,
            Student("3", "", "New    32222222222222 one po'gfjer'gj",30),
            Student("4", "", "New    33333333one poqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq'gfjer'gj", 55),
            Student("1", "", "New       1111111111 one po'gfjer'gj", 5),
            Student("2", "", "New      1111121212222222222222222211111 one po'gfjer'gj", 21) ,
            Student("3", "", "New    32222222222222 one po'gfjer'gj",30),
            Student("4", "", "New    33333333one poqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq'gfjer'gj", 55)
        )

        recyclerView.adapter = Dz6ListAdapter(items, this )
    }

    override fun onStudentClick(item: Student) {
        val intent = Intent(this, Dz6ProfilActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
    }

    fun startActivity(){
        addImageView.setOnClickListener {
            val intent = Intent(this, Dz6EditProfileActivity::class.java)
            Toast.makeText(this, "You have entered the profile edit window", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}
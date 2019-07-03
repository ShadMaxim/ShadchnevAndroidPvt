package by.itacademy.myapp.dz6

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import kotlinx.android.synthetic.main.activity_dz6_recycler.*

class Dz6Activity : Activity(), Dz6ListAdapter.ClickListener {

    private lateinit var adapter: Dz6ListAdapter

    companion object {
        const val ID_KEY_SHOW = "ID_KEY_SHOW"
    }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_recycler)

        startAddActivity()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        val decor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decor)

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        adapter = Dz6ListAdapter(StudentsSinglton.getStudentsExprorerList(), this)

        recyclerView.adapter = adapter

        searchStudentDz6.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                timer = Handler()
                timer?.postDelayed({
                    (adapter.updateList(StudentsSinglton.search(s.toString()) as MutableList<Student>))
                }, 500)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer?.removeCallbacks(null)
            }
        })
    }

    override fun onStudentClick(item: Student) {
        val intent = Intent(this, Dz6ProfilActivity::class.java)
        intent.putExtra(ID_KEY_SHOW, item.id)
        startActivity(intent)
        Toast.makeText(this, " You are viewing information about " + item.name, Toast.LENGTH_SHORT).show()
    }

    fun startAddActivity() {
        addImageView.setOnClickListener {
            val intent = Intent(this, Dz6EditProfileActivity::class.java)
            Toast.makeText(this, "You have entered the window for creating a new student", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateList(StudentsSinglton.getStudentsList())
    }
}
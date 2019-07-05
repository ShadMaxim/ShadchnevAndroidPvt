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

class Dz6StudentListActivity : Activity(), Dz6ListAdapter.ClickListener {

    private lateinit var adapter: Dz6ListAdapter

    companion object {
        const val ID_KEY_SHOW = "ID_KEY_SHOW"
    }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_recycler)

        startAddActivity()
        explourerRecycler()
        startDecor()
        searchStudent()
    }

    override fun onStudentClick(item: Dz6Student) {
        val intent = Intent(this, Dz6StudentDetailsActivity::class.java)
        intent.putExtra(ID_KEY_SHOW, item.id)
        startActivity(intent)
        Toast.makeText(this, " You are viewing information about " + item.name, Toast.LENGTH_SHORT).show()
    }

    fun startAddActivity() {
        addImageView.setOnClickListener {
            val intent = Intent(this, Dz6StudentEditActivity::class.java)
            Toast.makeText(this, "You have entered the window for creating a new student", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

    fun searchStudent() {
        searchStudentDz6.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                timer = Handler()
                timer?.postDelayed({
                    (adapter.updateList(Dz6StudentsSinglton.search(s.toString()) as MutableList<Dz6Student>))
                }, 500)
            }
        })
    }

    fun startDecor() {
        val decor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decor)
    }

    @SuppressLint("WrongConstant")
    fun explourerRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        adapter = Dz6ListAdapter(Dz6StudentsSinglton.getStudentsExplorerList(), this)

        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.updateList(Dz6StudentsSinglton.getStudentsList())
    }
}
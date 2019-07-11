package by.itacademy.myapp.dz8

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz6.Dz6ListAdapter
import by.itacademy.myapp.dz6.Dz6Student
import by.itacademy.myapp.dz6.Dz6StudentsSinglton
import kotlinx.android.synthetic.main.activity_dz8_recycler.view.*

class Dz8StudentListFragment : Fragment(),
    Dz6ListAdapter.ClickListener {

    private lateinit var adapter: Dz6ListAdapter
    private lateinit var prefManager: SharedPrefManager
    private var searchText: String = ""
    private lateinit var dz8SearchEditText: EditText
    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_dz8_recycler, container, false)

        val dz8RecyclerView = view.findViewById<RecyclerView>(R.id.dz8RecyclerView)
        dz8RecyclerView.setHasFixedSize(true)

        val decor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dz8RecyclerView.addItemDecoration(decor)

        dz8RecyclerView.layoutManager = LinearLayoutManager(context)
        dz8RecyclerView.isNestedScrollingEnabled = false
        adapter = Dz6ListAdapter(Dz8StudentListFragment().reload(), this)
        dz8RecyclerView.adapter = adapter

        dz8SearchEditText = view.searchStudentDz8
        dz8SearchEditText.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer = Handler()
                timer?.postDelayed({
                    searchText = p0.toString()
                    adapter.updateList(Dz6StudentsSinglton.search(searchText) as MutableList<Dz6Student>)
                }, 500)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        view.dz8AddImageView.setOnClickListener {
            listener?.onAddButtonClick()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        prefManager = SharedPrefManager(requireContext())
        dz8SearchEditText.setText(prefManager.readUserText())
        updateRecyclerList()
    }

    override fun onPause() {
        super.onPause()
        updateRecyclerList()
        prefManager.saveSharedPrefs(dz8SearchEditText.text.toString())
    }

    override fun onStudentClick(item: Dz6Student) {
        listener?.onStudentClick(item.id)
    }

    fun updateRecyclerList() {
        adapter.updateList(Dz6StudentsSinglton.search(searchText) as MutableList<Dz6Student>)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun reload(): MutableList<Dz6Student> {
        if (Dz6StudentsSinglton.getStudentsList().isEmpty()) {
            return Dz6StudentsSinglton.getStudentsExplorerList()
        } else {
            return Dz6StudentsSinglton.getStudentsList()
        }
    }

    interface Listener {
        fun onStudentClick(id: Long)
        fun onAddButtonClick()
    }
}

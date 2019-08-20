package by.itacademy.myapp.dz12.student.list

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz12.student.adapter.Dz12ListAdapter
import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData
import by.itacademy.myapp.dz12.student.pageload.AutoLoadRecyclerListener
import by.itacademy.myapp.dz8.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_dz12_recycler.view.*

class Dz12ListFragment : Fragment(),
    Dz12ListAdapter.ClickListener, Dz12ViewList {

    private lateinit var adapter: Dz12ListAdapter
    private lateinit var prefManager: SharedPrefManager
    private var searchText: String = ""
    private lateinit var dz12SearchEditText: EditText
    private var listener: Listener? = null
    private var presenter: Dz12PresenterList? = null
    private lateinit var scrollListener: AutoLoadRecyclerListener
    private var emptyList: MutableList<Dz12StudentData> = mutableListOf()

    private lateinit var progressBar: ProgressBar
    private lateinit var frameLayout: FrameLayout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener)
            listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dz12_recycler, container, false)

        presenter = Dz12PresenterList()
        presenter?.setView(this)

        progressBar = view.findViewById(R.id.dz12ListProgressBar)
        frameLayout = view.findViewById(R.id.dz12ListFrameLayout)

        // presenter.timerToast()
        presenter?.loadList(searchText)

        val dz12RecyclerView = view.findViewById<RecyclerView>(R.id.dz12RecyclerView)
        dz12RecyclerView.setHasFixedSize(true)

        val decor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dz12RecyclerView.addItemDecoration(decor)

        val manager = LinearLayoutManager(context)
        dz12RecyclerView.layoutManager = manager
        dz12RecyclerView.isNestedScrollingEnabled = false
        adapter = Dz12ListAdapter(load(), this)
        dz12RecyclerView.adapter = adapter

        scrollListener = object : AutoLoadRecyclerListener(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter?.loadMore(page, searchText)
            }
        }
        dz12RecyclerView.addOnScrollListener(scrollListener)

        dz12SearchEditText = view.searchStudentDz12
        dz12SearchEditText.addTextChangedListener(object : TextWatcher {

            var timer: Handler? = null
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer = Handler()
                timer?.postDelayed({
                    searchText = p0.toString()
                    scrollListener.resetPages()
                    presenter?.loadList(searchText)
                }, 500)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        view.dz12AddImageView.setOnClickListener {
            presenter?.goToAddButton()
        }
        return view
    }

    override fun showNewList(list: MutableList<Dz12StudentData>) {
        adapter.updateList(list)
    }

    override fun onResume() {
        super.onResume()
        prefManager = SharedPrefManager(requireContext())
        dz12SearchEditText.setText(prefManager.readUserText())
        presenter?.reloadRecycler()
    }

    override fun onPause() {
        super.onPause()
        prefManager.saveSharedPrefs(dz12SearchEditText.text.toString())
    }

    override fun onStudentClick(item: Dz12StudentData) {
        Toast.makeText(context, "show profile " + item.name, Toast.LENGTH_SHORT).show()
        listener?.onStudentClick(item.id)
    }

    fun updateRecyclerList() {
        var list = presenter?.newListForSearch(searchText)
        showNewList(list!!)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        frameLayout.visibility = View.GONE
    }

    override fun notShowProgressBar() {
        progressBar.visibility = View.GONE
        frameLayout.visibility = View.VISIBLE
    }

    override fun showToastGetOk(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastGetError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showTimer(number: Long) {
        Toast.makeText(context, number.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showTimerError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun addButtonClick() {
        Toast.makeText(context, "Create new profile", Toast.LENGTH_LONG).show()
        listener?.onAddButtonClick()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        presenter?.detachView()
    }

    private fun load(): MutableList<Dz12StudentData> {
        return emptyList
    }

    interface Listener {
        fun onStudentClick(id: String)
        fun onAddButtonClick()
    }
}
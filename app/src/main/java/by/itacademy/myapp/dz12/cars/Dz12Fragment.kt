package by.itacademy.myapp.dz12.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz9.Dz9Adapter
import by.itacademy.myapp.dz9.entity.Poi

class Dz12Fragment : Fragment(), Dz9Adapter.ClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapterDz9: Dz9Adapter
    private lateinit var viewModel: Dz12ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = activity?.run {
            ViewModelProviders.of(this)[Dz12ViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel
            .state
            .observe(this, Observer {
                when (it) {
                    is Dz12State.Data -> {
                        startAdapret(it.dataList)
                    }
                    is Dz12State.Error -> {
                        error(it)
                        errorLoad(it.throwable)
                    }
                }
            })
    }

    override fun onCarsClick(item: Poi) {
        viewModel.onCarTouch(item)
    }

    private fun startAdapret(listPoi: List<Poi>) {
        recyclerView = view!!.findViewById<RecyclerView>(R.id.dz9RecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        myAdapterDz9 = Dz9Adapter(listPoi, this)
        recyclerView.adapter = myAdapterDz9
    }

    fun errorLoad(throwable: Throwable) {
        Toast.makeText(context, "<< ERROR: $throwable >>", Toast.LENGTH_LONG).show()
    }
}
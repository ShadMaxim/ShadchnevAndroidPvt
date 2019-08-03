package by.itacademy.myapp.dz9

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.myapp.R
import by.itacademy.myapp.dz9.entity.CoordParams
import by.itacademy.myapp.dz9.entity.Coordinate
import by.itacademy.myapp.dz9.entity.Poi

class Dz9Fragment : Fragment(), Dz9Adapter.ClickListener, CarRepositoryResult {

    private val carRepository: CarRepository = provideCarRepository()
    private var clickListener: Listener? = null
    private var poiList: MutableList<Poi> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapterDz9: Dz9Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById<RecyclerView>(R.id.dz9RecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val params = CoordParams(
            Coordinate(3333.0, 342.0),
            Coordinate(3242.0, 3453.0))

        carRepository.getCarsByCoord(params, this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            clickListener = context
        }
    }

    override fun onSuccessfully(listPoi: List<Poi>) {

        poiList.addAll(listPoi)
        myAdapterDz9 = Dz9Adapter(listPoi, this)
        recyclerView.adapter = myAdapterDz9
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, " << ERROR DOWNLOAD >> ", Toast.LENGTH_LONG).show()
    }

    override fun onCarsClick(item: Poi) {
        clickListener?.onCarTouch(item)
    }

    interface Listener {
        fun onCarTouch(item: Poi)
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }
}
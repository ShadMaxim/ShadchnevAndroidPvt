package by.itacademy.myapp.dz11.mvvm

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.itacademy.myapp.R
import by.itacademy.myapp.dz9.entity.FleeType
import by.itacademy.myapp.dz9.entity.Poi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Dz11Activity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private var listPoi: MutableList<Poi> = mutableListOf()
    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var arrowBitmapTaxi: Bitmap
    private lateinit var arrowBitmapPool: Bitmap
    private var back = false
    private var mapLoaded = false

    private lateinit var viewModel: Dz11ViewModel
    private lateinit var observerState: Observer<Dz11State>
    private lateinit var observerCarItem: Observer<Poi>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9_bottom_sheet)

        observerState = Observer { status ->
            when (status) {
                is Dz11State.Data -> {
                    onSuccessfully(status.dataList)
                }
                is Dz11State.Error -> {
                    onError(status.throwable)
                }
            }
        }

        observerCarItem = Observer { item ->
            if (item != null) {
                onCarTouch(item)
            } else {
                onErrorCarTouch()
            }
        }

        viewModel = ViewModelProviders.of(this).get(Dz11ViewModel::class.java)
        viewModel.drawMap()

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9container))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        if (savedInstanceState == null) {
            replaceFragment(R.id.dz9container, Dz11Fragment())
        }

        mapView = findViewById(R.id.dz9Map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    private fun onSuccessfully(list: List<Poi>) {
        listPoi.addAll(list)

        Handler().postDelayed({
        if (mapLoaded) {

            val builder = LatLngBounds.builder()

            this.listPoi.forEach {
                val coord = LatLng(
                    it.coordinate?.latitude!!,
                    it.coordinate.longitude
                )
                drawCarsOnMap(it, createLatLng(it))
                builder.include(coord)
            }
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)

            val bounds = builder.build()
            val paddingForMaps = 50

            googleMap.animateCamera(
                CameraUpdateFactory
                    .newLatLngBounds(
                        bounds,
                        metrics.widthPixels,
                        metrics.heightPixels,
                        paddingForMaps
                    )
            )
        } }, 600)
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, " << ERROR:$throwable >> ", Toast.LENGTH_LONG).show()
    }

    private fun onErrorCarTouch() {
        Toast.makeText(this, " << ERROR: List cars not find >> ", Toast.LENGTH_LONG).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        googleMap.setOnMarkerClickListener(this)
        googleMap.setOnMapClickListener(this)

        mapLoaded = true

        viewModel.state.observeForever(observerState)
        viewModel.carItem.observeForever(observerCarItem)
    }

    private fun onCarTouch(item: Poi) {
        if (mapLoaded) {

            val zoomCamera = 18.5f
            googleMap.clear()
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        item.coordinate?.latitude!!,
                        item.coordinate.longitude
                    ), zoomCamera
                )
            )

            drawCarsOnMap(item, createLatLng(item))
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            back = true
        }
    }

    // центрирует и увеличивает маркер по его нажатию, выводит его координаты в тоаст
    override fun onMarkerClick(marker: Marker?): Boolean {
        if (marker != null) {
            Toast.makeText(this, marker.position.toString(), Toast.LENGTH_LONG).show()

            googleMap.clear()
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    marker.position, 18.5f
                ))

            googleMap.addMarker(MarkerOptions()
                .position(marker.position)
                .anchor(0.5f, 0.4f)
                .flat(true)
                .icon(BitmapDescriptorFactory.fromBitmap(arrowBitmapTaxi)))
        }
        back = true
        return true
    }

    // центрируем экран по нажатию на карту,
    override fun onMapClick(coord: LatLng?) {
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(coord))
        // Toast.makeText(this, coord.toString(), Toast.LENGTH_LONG).show()
    }

    private fun drawCarsOnMap(poi: Poi, latLng: LatLng) {

        arrowBitmapTaxi = AppCompatResources
            .getDrawable(this, R.drawable.ic_near_me_black_24dp)!!.toBitmap()
        arrowBitmapPool = AppCompatResources
            .getDrawable(this, R.drawable.ic_send_black_24dp)!!.toBitmap()

        googleMap.addMarker(

            if (poi.fleeType == FleeType.TAXI) {
                poi.heading?.toFloat()?.let { it1 ->
                    MarkerOptions()
                        .position(latLng)
                        .anchor(0.5f, 0.4f)
                        .rotation(it1)
                        .flat(true)
                        .icon(BitmapDescriptorFactory.fromBitmap(arrowBitmapTaxi))
                        .title(poi.fleeType.toString())
                }
            } else {
                poi.heading?.toFloat()?.let { it1 ->
                    MarkerOptions()
                        .position(latLng)
                        .anchor(0.5f, 0.4f)
                        .rotation(it1)
                        .flat(true)
                        .icon(BitmapDescriptorFactory.fromBitmap(arrowBitmapPool))
                        .title(poi.fleeType.toString())
                }
            }
        )
    }

    private fun replaceFragment(idContainer: Int, fragmentName: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, fragmentName)
        transaction.commit()
    }

    private fun createLatLng(item: Poi): LatLng {
        val coordinate = LatLng(
            item.coordinate?.latitude!!,
            item.coordinate.longitude
        )
        return coordinate
    }

    override fun onBackPressed() {
        if (back) {
            onSuccessfully(listPoi)
        } else {
            super.onBackPressed()
        }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        viewModel.state.removeObserver(observerState)
        viewModel.carItem.removeObserver(observerCarItem)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
package by.itacademy.myapp.dz9

import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.myapp.R
import by.itacademy.myapp.dz9.entity.CoordParams
import by.itacademy.myapp.dz9.entity.Coordinate
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
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Dz9Activity : FragmentActivity(), CarRepositoryResult, OnMapReadyCallback, Dz9Fragment.Listener {

    private val carRepository: CarRepository = provideCarRepository()
    private var listPoi: MutableList<Poi> = mutableListOf()
    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var arrowBitmapTaxi: Bitmap
    private lateinit var arrowBitmapPool: Bitmap
    private var back = false
    private var mapLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9_bottom_sheet)

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9container))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        if (savedInstanceState == null) {
            replaceFragment(R.id.dz9container, Dz9Fragment())
        }

        mapView = findViewById(R.id.dz9Map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val params = CoordParams(
            Coordinate(3333.0, 342.0),
            Coordinate(3242.0, 3453.0)
        )
        carRepository.getCarsByCoord(params, this)
    }

    override fun onSuccessfully(list: List<Poi>) {
        listPoi.addAll(list)

        if (mapLoaded) {
            val builder = LatLngBounds.builder()

            this.listPoi.forEach {
                val coord = LatLng(
                    it.coordinate?.latitude!!,
                    it.coordinate.longitude
                )

                addMark(it, createLatLng(it))
                builder.include(coord)
            }

            val bounds = builder.build()
            val paddForMaps = 200
            googleMap.moveCamera(
                CameraUpdateFactory
                    .newLatLngBounds(bounds, paddForMaps)
            )
        }
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, " << ERROR DOWNLOAD >> ", Toast.LENGTH_LONG).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mapLoaded = true
    }

    override fun onCarTouch(item: Poi) {
        if (mapLoaded) {

            val zoomerCamera = 18f
            googleMap.clear()
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        item.coordinate?.latitude!!,
                        item.coordinate.longitude
                    ), zoomerCamera
                )
            )

            addMark(item, createLatLng(item))
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            back = true
        }
    }

    private fun addMark(poi: Poi, latLng: LatLng) {

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

    private fun replaceFragment(idContainer: Int, nameFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = nameFragment
        transaction.replace(idContainer, fragment)
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
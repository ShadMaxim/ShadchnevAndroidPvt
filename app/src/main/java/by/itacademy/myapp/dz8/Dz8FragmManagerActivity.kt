package by.itacademy.myapp.dz8

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.myapp.R

class Dz8FragmManagerActivity : FragmentActivity(),
    Dz8StudentListFragment.Listener,
    MyListener {

    private var isLandOrientation = false
    private var container: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8_for_fragments)

        isLandOrientation = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        container = getLandOrPortret()

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8ContainerHead, Dz8StudentListFragment())
            transaction.commit()
        }
    }

    override fun onStudentClick(id: Long) {
        createTransaction(getLandOrPortret(), Dz8StudentDetailsFragment.getInstance(id))
    }

    override fun onAddButtonClick() {
        createTransaction(getLandOrPortret(), Dz8StudentEditFragment.getInstance())
    }

    private fun createTransaction(idContainer: Int, nameFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, nameFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRealization() {

        supportFragmentManager.popBackStack()
        if (isLandOrientation) {
            (supportFragmentManager.findFragmentById(R.id.dz8ContainerHead) as Dz8StudentListFragment).updateRecyclerList()
        } else {
            createTransaction(R.id.dz8ContainerHead, Dz8StudentListFragment())
        }
    }

    override fun onEdit(id: Long) {
        if (isLandOrientation) {
            supportFragmentManager.popBackStack()
        }
        createTransaction(getLandOrPortret(), Dz8StudentEditFragment.getInstance(id))
    }

    override fun onIfNullToBack() {
        supportFragmentManager.popBackStack()
    }

    fun getLandOrPortret(): Int {

        return if (isLandOrientation) {
            R.id.dz8ContainerSecond
        } else {
            R.id.dz8ContainerHead
        }
    }




}
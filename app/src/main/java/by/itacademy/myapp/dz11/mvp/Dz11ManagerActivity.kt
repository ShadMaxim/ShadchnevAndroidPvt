package by.itacademy.myapp.dz11.mvp

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.myapp.R
import by.itacademy.myapp.dz11.mvp.details.Dz11DetailsFragment
import by.itacademy.myapp.dz11.mvp.edit.Dz11EditFragment
import by.itacademy.myapp.dz11.mvp.list.Dz11ListFragment
import by.itacademy.myapp.dz8.MyListener

class Dz11ManagerActivity : FragmentActivity(),
    Dz11ListFragment.Listener,
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
            transaction.replace(R.id.dz8ContainerHead, Dz11ListFragment())
            transaction.commit()
        }
    }

    override fun onStudentClick(id: Long) {
        replaceFragment(getLandOrPortret(), Dz11DetailsFragment.getInstance(id))
    }

    override fun onAddButtonClick() {
        replaceFragment(getLandOrPortret(), Dz11EditFragment.getInstance())
    }

    private fun replaceFragment(idContainer: Int, nameFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, nameFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRealization() {

        supportFragmentManager.popBackStack()
        if (isLandOrientation) {
            (supportFragmentManager.findFragmentById(R.id.dz8ContainerHead) as Dz11ListFragment).updateRecyclerList()
        } else {
            replaceFragment(R.id.dz8ContainerHead, Dz11ListFragment())
        }
    }

    override fun onEdit(id: Long) {
        if (isLandOrientation) {
            supportFragmentManager.popBackStack()
        }
        replaceFragment(getLandOrPortret(), Dz11EditFragment.getInstance(id))
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
package by.itacademy.myapp.dz12.student

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.myapp.R
import by.itacademy.myapp.dz12.student.details.Dz12DetailsFragment
import by.itacademy.myapp.dz12.student.edit.Dz12EditFragment
import by.itacademy.myapp.dz12.student.list.Dz12ListFragment

class Dz12ManagerActivity : FragmentActivity(),
    Dz12ListFragment.Listener,
    Dz12MyListener {

    private var isLandOrientation = false
    private var container: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8_for_fragments)

        isLandOrientation = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        container = getLandOrPortrait()

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz8ContainerHead, Dz12ListFragment())
            transaction.commit()
        }
    }

    override fun onStudentClick(id: String) {
        replaceFragment(getLandOrPortrait(), Dz12DetailsFragment.getInstance(id))
    }

    override fun onAddButtonClick() {
        replaceFragment(getLandOrPortrait(), Dz12EditFragment.getInstance())
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
            (supportFragmentManager.findFragmentById(R.id.dz8ContainerHead) as Dz12ListFragment).updateRecyclerList()
        } else {
            replaceFragment(R.id.dz8ContainerHead, Dz12ListFragment())
        }
    }

    override fun onEdit(id: String) {
        if (isLandOrientation) {
            supportFragmentManager.popBackStack()
        }
        replaceFragment(getLandOrPortrait(), Dz12EditFragment.getInstance(id))
    }

    override fun onIfNullToBack() {
        supportFragmentManager.popBackStack()
    }

    private fun getLandOrPortrait(): Int {
        return if (isLandOrientation) {
            R.id.dz8ContainerSecond
        } else {
            R.id.dz8ContainerHead
        }
    }
}
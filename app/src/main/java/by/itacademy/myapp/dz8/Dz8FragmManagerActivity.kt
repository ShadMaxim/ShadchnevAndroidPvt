package by.itacademy.myapp.dz8

import android.content.res.Configuration
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.myapp.R

class Dz8FragmManagerActivity : FragmentActivity(), Dz8StudentListFragment.Listener, Dz8StudentDetailsFragment.Listener, MyListenerChangeBox {

    private var isLandOrientation = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8_for_fragments)

        isLandOrientation = (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)

        if (savedInstanceState == null && !isLandOrientation)
            completeContainer1Start()
        else {
            if (isLandOrientation && supportFragmentManager.findFragmentById(R.id.dz8ContainerOne) is Dz8StudentListFragment) {
                supportFragmentManager.beginTransaction()
                    .remove(supportFragmentManager.findFragmentById(R.id.dz8ContainerOne) as Dz8StudentListFragment)
                    .commit()
            } else if (!isLandOrientation && (findViewById<FrameLayout>(R.id.dz8ContainerOne) == null))
                completeContainer1Start()
        }
    }

    private fun completeContainer1Start() {
        createTransaction(R.id.dz8ContainerOne, Dz8StudentListFragment())
    }

    override fun onStudentClick(id: Long) {
        createTransaction(R.id.dz8ContainerOne, Dz8StudentDetailsFragment.getInstance(id))
    }

    override fun onAddButtonClick() {
        createTransaction(R.id.dz8ContainerOne, Dz8StudentEditFragment.getInstance())
    }

    override fun onEditStudentClick(id: Long) {
        supportFragmentManager.popBackStack()
        createTransaction(R.id.dz8ContainerOne, Dz8StudentEditFragment.getInstance(id))
    }

    private fun createTransaction(idContainer: Int, nameFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(idContainer, nameFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onChangeBox() {
        supportFragmentManager.popBackStack()
        if (isLandOrientation) {
            (supportFragmentManager.findFragmentById(R.id.dz8ContainerHead) as Dz8StudentListFragment).updateRecyclerList()
        }
    }
}
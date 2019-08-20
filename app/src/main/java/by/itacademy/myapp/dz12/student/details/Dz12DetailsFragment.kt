package by.itacademy.myapp.dz12.student.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.LinearLayout
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.myapp.R
import by.itacademy.myapp.dz12.student.Dz12MyListener
import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData
import by.itacademy.myapp.util.picassoLoader

class Dz12DetailsFragment : Fragment(), Dz12ViewDetails {

    private var mylistener: Dz12MyListener? = null
    private var presenter: Dz12PresenterDetails? = null

    private lateinit var ageEditText: TextView
    private lateinit var nameEditText: TextView
    private lateinit var urlEditText: TextView
    private lateinit var avatarImageView: ImageView

    private lateinit var progressBar: ProgressBar
    private lateinit var linearLayout: LinearLayout

    private var nameStudent: String = ""

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: String): Dz12DetailsFragment {
            val fragment = Dz12DetailsFragment()
            val args = Bundle()
            args.putString(ID_STUDENT, idStudent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Dz12MyListener)
            mylistener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dz12_details_student, container, false)

        val idStudent = arguments?.getString(ID_STUDENT)

        nameEditText = view.findViewById(R.id.dz12NameProfilStudentTextView)
        ageEditText = view.findViewById(R.id.dz12AgeProfilStudentTextView)
        urlEditText = view.findViewById(R.id.dz12UrlProfilStudentTextView)
        avatarImageView = view.findViewById(R.id.dz12AvatarProfileImageView)

        progressBar = view.findViewById(R.id.dz12DetailsProgressBar)
        linearLayout = view.findViewById(R.id.dz12DetailsLinearLayout)

        presenter = Dz12PresenterDetails()
        presenter!!.setView(this)

        if (idStudent != null) {
            presenter!!.getStudentById(idStudent)

            view.findViewById<Button>(R.id.dz12DeleteProfileButton).setOnClickListener {
                presenter!!.delete(idStudent)
            }

            view.findViewById<Button>(R.id.dz12EditProfileButton).setOnClickListener {
                presenter!!.goEdit(idStudent)
            }
        } else {
            presenter?.goBackStack()
        }
        return view
    }

    override fun showStudent(student: Dz12StudentData) {
        picassoLoader(student.imageUrl, avatarImageView)
        nameEditText.text = student.name
        urlEditText.text = student.imageUrl
        ageEditText.text = student.age.toString()
        nameStudent = student.name
    }

    override fun ifNullStudent() {
        Toast.makeText(context, "ERROR( Student ): Not valid student", Toast.LENGTH_SHORT).show()
        mylistener?.onIfNullToBack()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        linearLayout.visibility = View.GONE
    }

    override fun notShowProgressBar() {
        progressBar.visibility = View.GONE
        linearLayout.visibility = View.VISIBLE
    }

    override fun showToastOk(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun updatePage() {
        mylistener?.onRealization()
    }

    override fun goToEdit(idStudent: String) {
        Toast.makeText(context, " Change student " + nameStudent + " data ", Toast.LENGTH_SHORT).show()
        mylistener?.onEdit(idStudent)
    }

    override fun onDetach() {
        super.onDetach()
        mylistener = null
        presenter!!.detachView()
    }
}
package by.itacademy.myapp.dz12.student.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.myapp.R
import by.itacademy.myapp.dz12.student.Dz12MyListener
import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData
import kotlinx.android.synthetic.main.activity_dz8_edit_profile.*
import kotlinx.android.synthetic.main.activity_dz8_edit_profile.view.*

class Dz12EditFragment : Fragment(), Dz12ViewEdit {

    private var myListener: Dz12MyListener? = null
    private lateinit var presenter: Dz12PresenterEdit

    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"
        const val NEW_STUDENT = "NEW_STUDENT"

        fun getInstance(idStudent: String = NEW_STUDENT): Dz12EditFragment {
            val fragment = Dz12EditFragment()
            val args = Bundle()
            args.putString(ID_STUDENT, idStudent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Dz12MyListener)
            myListener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_dz8_edit_profile, container, false)

        ageEditText = view.findViewById<EditText>(R.id.dz8AgeEditProfileEditText)
        nameEditText = view.findViewById<EditText>(R.id.dz8NameEditProfileEditText)
        urlEditText = view.findViewById<EditText>(R.id.dz8UrlEditProfileEditText)

        val idStudent = arguments?.getString(ID_STUDENT, NEW_STUDENT)

        presenter = Dz12PresenterEdit()
        presenter.setView(this)

        if (idStudent != NEW_STUDENT && idStudent != null) {
            presenter.getStudentById(idStudent)
        }

        view.dz8SaveEditProfileButton.setOnClickListener {

            val name = dz8NameEditProfileEditText.text.toString()
            val url = dz8UrlEditProfileEditText.text.toString()
            val age = dz8AgeEditProfileEditText.text.toString().toInt()

            presenter.goToSaveOrEdit(idStudent!!, name, url, age)
        }

        return view
    }

    override fun showStudent(student: Dz12StudentData) {
        nameEditText.setText(student.name)
        ageEditText.setText(student.age.toString())
        urlEditText.setText(student.imageUrl)
    }

    override fun showToastUpdateOk(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastUpdateError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastCreateError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastCreateOk(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToastErrorFilling(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun showErrorLoadById(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun updatePage() {
        myListener?.onRealization()
    }

    override fun onDetach() {
        super.onDetach()
        myListener = null
        presenter.detachView()
    }
}
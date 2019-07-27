package by.itacademy.myapp.dz11.mvp.edit

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.myapp.R
import by.itacademy.myapp.dz6.Dz6Student
import by.itacademy.myapp.dz8.MyListener
import kotlinx.android.synthetic.main.activity_dz8_edit_profile.*
import kotlinx.android.synthetic.main.activity_dz8_edit_profile.view.*

class Dz11EditFragment : Fragment(), Dz11ViewEdit {

    private val pattern = Patterns.WEB_URL
    private var myListener: MyListener? = null
    // private var student: Dz6Student? = null
    private lateinit var presenter: Dz11PresenterEdit

    private lateinit var ageEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var urlEditText: EditText

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: Long = 0): Dz11EditFragment {
            val fragment = Dz11EditFragment()
            val args = Bundle()
            args.putLong(ID_STUDENT, idStudent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MyListener)
            myListener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_dz8_edit_profile, container, false)

        ageEditText = view.findViewById<EditText>(R.id.dz8AgeEditProfileEditText)
        nameEditText = view.findViewById<EditText>(R.id.dz8NameEditProfileEditText)
        urlEditText = view.findViewById<EditText>(R.id.dz8UrlEditProfileEditText)

        val idStudent = arguments?.getLong(ID_STUDENT, -1)

        presenter = Dz11PresenterEdit()
        presenter.setView(this)

        if (idStudent != 0.toLong() && idStudent != null) {
            presenter.getStudentById(idStudent)
        }

        if (idStudent != 0.toLong() && idStudent != null) {

            view.dz8SaveEditProfileButton.setOnClickListener {

                val name = dz8NameEditProfileEditText.text.toString()
                val url = dz8UrlEditProfileEditText.text.toString()
                val age = dz8AgeEditProfileEditText.text.toString().toInt()

                if (presenter.correctDataFilling(name, age, url)) {
                    presenter.saveEditedStudent(idStudent, name, age, url)
                    myListener?.onRealization()
                } else {
                    Toast.makeText(context, Dz11PresenterEdit.textErrorCorrectDataFiling, Toast.LENGTH_LONG)
                        .show()
                }
            }
        } else {

            view.dz8SaveEditProfileButton.setOnClickListener {

                val name = dz8NameEditProfileEditText.text.toString()
                val url = dz8UrlEditProfileEditText.text.toString()
                val age = dz8AgeEditProfileEditText.text.toString().toInt()

                if (presenter.correctDataFilling(name, age, url)) {
                    presenter.saveNewStudent(name, age, url)
                    myListener?.onRealization()
                } else {
                    Toast.makeText(context, Dz11PresenterEdit.textErrorCorrectDataFiling, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        return view
    }

    override fun showStudent(student: Dz6Student) {
        nameEditText.setText(student.name)
        ageEditText.setText(student.age.toString())
        urlEditText.setText(student.url)
    }

    override fun onDetach() {
        super.onDetach()
        myListener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}
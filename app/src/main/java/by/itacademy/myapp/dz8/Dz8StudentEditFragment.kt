package by.itacademy.myapp.dz8

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
import by.itacademy.myapp.dz6.Dz6StudentsSinglton
import kotlinx.android.synthetic.main.activity_dz8_edit_profile.*
import kotlinx.android.synthetic.main.activity_dz8_edit_profile.view.*

class Dz8StudentEditFragment : Fragment() {

    private val pattern = Patterns.WEB_URL
    private var myListener: MyListener? = null
    private var student: Dz6Student? = null

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: Long = 0): Dz8StudentEditFragment {
            val fragment = Dz8StudentEditFragment()
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

        val ageEditText = view.findViewById<EditText>(R.id.dz8AgeEditProfileEditText)
        val nameEditText = view.findViewById<EditText>(R.id.dz8NameEditProfileEditText)
        val urlEditText = view.findViewById<EditText>(R.id.dz8UrlEditProfileEditText)

        val idStudent = arguments?.getLong(ID_STUDENT, -1)

        val text = idStudent.toString()
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()

        if (idStudent != 0.toLong() && idStudent != null) {
            student = Dz6StudentsSinglton.findStudentById(idStudent)

            ageEditText.setText(student!!.age.toString())
            nameEditText.setText(student!!.name)
            urlEditText.setText(student!!.url)

            view.dz8SaveEditProfileButton.setOnClickListener {

                val studentDataEdited: Dz6Student
                val name: String
                val url: String
                val age: Int

                try {
                    name = dz8NameEditProfileEditText.text.toString()
                    url = dz8UrlEditProfileEditText.text.toString()
                    age = dz8AgeEditProfileEditText.text.toString().toInt()

                    if (!pattern.matcher(url).matches()) {
                        Toast.makeText(context, "ERROR( URL ): Not valid URL", Toast.LENGTH_SHORT).show()
                    } else if (age == null) {
                        Toast.makeText(context, "ERROR( AGE ): Not valid Age, must be >= 0", Toast.LENGTH_SHORT).show()
                    } else if (name.isEmpty()) {
                        Toast.makeText(context, "ERROR( NAME ): Not valid Name, must be filled", Toast.LENGTH_SHORT).show()
                    } else {
                        studentDataEdited = Dz6Student(student!!.id, url, name, age)
                        Dz6StudentsSinglton.editStudent(student!!, studentDataEdited)

                        Toast.makeText(context, "profile successfully edited", Toast.LENGTH_SHORT).show()

                        myListener?.onRealization()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        } else {

            view.dz8SaveEditProfileButton.setOnClickListener {

                val name: String
                val url: String
                val age: Int

                try {

                    name = dz8NameEditProfileEditText.text.toString()
                    url = dz8UrlEditProfileEditText.text.toString()
                    age = dz8AgeEditProfileEditText.text.toString().toInt()

                    if (!pattern.matcher(url).matches()) {
                        Toast.makeText(context, "ERROR( URL ): Not valid URL", Toast.LENGTH_SHORT).show()
                    } else if (age <= 0) {
                        Toast.makeText(context, "ERROR( AGE ): Not valid Age, must be >= 0", Toast.LENGTH_SHORT).show()
                    } else if (name.isEmpty()) {
                        Toast.makeText(context, "ERROR( NAME ): Not valid Name, must be filled", Toast.LENGTH_SHORT).show()
                    } else {
                        val studentNew = Dz6Student(Dz6StudentsSinglton.creatureNewId(), url, name, age)
                        Dz6StudentsSinglton.addNewStudent(studentNew)

                        Toast.makeText(context, "New profile successfully created", Toast.LENGTH_SHORT).show()

                        myListener?.onRealization()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        myListener = null
    }
}
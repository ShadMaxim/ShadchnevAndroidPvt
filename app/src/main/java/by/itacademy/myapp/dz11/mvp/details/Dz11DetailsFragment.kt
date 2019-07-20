package by.itacademy.myapp.dz11.mvp.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.myapp.R
import by.itacademy.myapp.dz11.mvp.model.Dz11DataStudent
import by.itacademy.myapp.dz6.Dz6Student
import by.itacademy.myapp.dz8.MyListener
import by.itacademy.myapp.util.picassoLoader

class Dz11DetailsFragment : Fragment(), Dz11ViewDetails {

    private var mylistener: MyListener? = null
    private var student: Dz6Student? = null
    private lateinit var presenter: Dz11PresenterDetails

    private lateinit var ageEditText: TextView
    private lateinit var nameEditText: TextView
    private lateinit var urlEditText: TextView

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: Long): Dz11DetailsFragment {
            val fragment = Dz11DetailsFragment()
            val args = Bundle()
            args.putLong(ID_STUDENT, idStudent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MyListener)
            mylistener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.activity_dz8_profil_student, container, false)

        val idStudent = arguments?.getLong(ID_STUDENT, -1)

        nameEditText = view.findViewById(R.id.dz8NameProfilStudentTextView)
        ageEditText = view.findViewById(R.id.dz8AgeProfilStudentTextView)
        urlEditText = view.findViewById(R.id.dz8UrlProfilStudentTextView)

        presenter = Dz11PresenterDetails()
        presenter.setView(this)

        if (idStudent != null) {
            presenter.getId(idStudent)
            showStudent(Dz11DataStudent.findStudentById(idStudent), view.findViewById(R.id.dz8AvatarProfileImageView))
            student = Dz11DataStudent.findStudentById(idStudent)

            view.findViewById<Button>(R.id.dz8DeleteProfileButton).setOnClickListener {
                presenter.clickDelete(idStudent)
                Toast.makeText(context, student!!.name + " successfully deleted ", Toast.LENGTH_LONG).show()
                mylistener?.onRealization()
            }

            view.findViewById<Button>(R.id.dz8EditProfileButton).setOnClickListener {
                Toast.makeText(context, " Change student " + student!!.name + " data ", Toast.LENGTH_LONG).show()
                mylistener?.onEdit(student!!.id)
            }
        } else {
            Toast.makeText(context, "ERROR( Student ): Not valid student", Toast.LENGTH_LONG).show()
            mylistener?.onIfNullToBack()
        }
        return view
    }

    override fun showStudent(student: Dz6Student, imageView: ImageView) {
        picassoLoader(student.url, imageView)
        nameEditText.text = student.name
        urlEditText.text = student.url
        ageEditText.text = student.age.toString()
    }

    override fun onDetach() {
        super.onDetach()
        mylistener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}
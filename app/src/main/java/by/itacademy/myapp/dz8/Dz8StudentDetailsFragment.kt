package by.itacademy.myapp.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.myapp.R
import by.itacademy.myapp.dz6.Dz6Student
import by.itacademy.myapp.dz6.Dz6StudentsSinglton
import by.itacademy.myapp.util.picassoLoader

class Dz8StudentDetailsFragment : Fragment() {

    private var mylistener: MyListener? = null
    private var student: Dz6Student? = null

    companion object {
        private const val ID_STUDENT = "ID_STUDENT"

        fun getInstance(idStudent: Long): Dz8StudentDetailsFragment {
            val fragment = Dz8StudentDetailsFragment()
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

        if (idStudent != null) {

            student = Dz6StudentsSinglton.findStudentById(idStudent)

            val text = idStudent.toString()
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()

            if (student == null)
                mylistener?.onIfNullToBack()
                //activity?.supportFragmentManager?.popBackStack()
            else {
                picassoLoader(student!!.url, view.findViewById(R.id.dz8AvatarProfileImageView))
                view.findViewById<TextView>(R.id.dz8NameProfilStudentTextView).text = student!!.name
                view.findViewById<TextView>(R.id.dz8UrlProfilStudentTextView).text = student!!.url
                view.findViewById<TextView>(R.id.dz8AgeProfilStudentTextView).text = student!!.age.toString()

                view.findViewById<Button>(R.id.dz8DeleteProfileButton).setOnClickListener {
                    Dz6StudentsSinglton.deleteStudentOfList(student!!)
                    mylistener?.onRealization()
                }

                view.findViewById<Button>(R.id.dz8EditProfileButton).setOnClickListener {
                    mylistener?.onEdit(student!!.id)
                }
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        mylistener = null
    }
}
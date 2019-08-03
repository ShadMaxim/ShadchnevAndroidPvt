package by.itacademy.myapp.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.myapp.R
import by.itacademy.myapp.util.picassoLoader
import kotlinx.android.synthetic.main.activity_dz6_profil_student.*

class Dz6StudentDetailsActivity : Activity() {

    companion object {
        const val ID_KEY_SHOW = "ID_KEY_SHOW"
        const val ID_KEY_EDIT = "ID_KEY_EDIT"
    }

    var student: Dz6Student? = null
    var studentsSinglton = Dz6StudentsSinglton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_profil_student)

        val id = intent.getLongExtra(ID_KEY_SHOW, -1)
        student = studentsSinglton.findStudentById(id)

        showStudent(student!!)
        deleteStudent()
        editStudent(id)
    }

    fun showStudent(student: Dz6Student) {
        picassoLoader(student.url, avatarProfileImageView)
        nameProfilStudentTextView.text = student.name
        ageProfilStudentTextView.text = student.age.toString()
        urlProfilStudentTextView.text = student.url
    }

    fun deleteStudent() {
        deleteProfileButton.setOnClickListener {
            studentsSinglton.deleteStudentOfList(student!!)
            Toast.makeText(this, "Profile deleted successfully", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }

    fun editStudent(id: Long) {
        editProfileButton.setOnClickListener {
            val intent = Intent(this, Dz6StudentEditActivity::class.java)
            intent.putExtra(ID_KEY_EDIT, id)
            Toast.makeText(this, "You can edit the current profile", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            this.finish()
        }
    }
}
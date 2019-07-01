package by.itacademy.myapp.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.myapp.R
import by.itacademy.myapp.util.picassoLoader
import kotlinx.android.synthetic.main.activity_dz6_profil_student.*

class Dz6ProfilActivity : Activity() {

    val idKey = "ID_KEY"
    lateinit var student: Student

    init {
        val id = intent.getStringExtra(idKey)
        var studentsSinglton = StudentsSinglton.instance
        student = studentsSinglton.findStudentById(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_profil_student)

        showStudent()

        deleteProfileButton.setOnClickListener {
            val intent = Intent(this, Dz6Activity::class.java)
            Toast.makeText(this, "Profile deleted successfully", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        editProfileButton.setOnClickListener {
            val intent = Intent(this, Dz6EditProfileActivity::class.java)
            Toast.makeText(this, "You can edit the current profile", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

    fun showStudent() {
        picassoLoader(student.url, avatarProfileImageView)
        nameProfilStudentTextView.text = student.name
        ageProfilStudentTextView.text = student.age.toString()
        urlProfilStudentTextView.text = student.url
    }
}
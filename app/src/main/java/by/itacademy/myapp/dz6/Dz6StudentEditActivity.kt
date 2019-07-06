package by.itacademy.myapp.dz6

import android.app.Activity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import by.itacademy.myapp.R
import by.itacademy.myapp.dz6.Dz6StudentsSinglton.addNewStudent
import by.itacademy.myapp.dz6.Dz6StudentsSinglton.creatureNewId
import by.itacademy.myapp.dz6.Dz6StudentsSinglton.editStudent
import kotlinx.android.synthetic.main.activity_dz6_edit_profile.*

class Dz6StudentEditActivity : Activity() {

    private val pattern = Patterns.WEB_URL

    companion object {
        const val ID_KEY_EDIT = "ID_KEY_EDIT"
    }

    private var student: Dz6Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_edit_profile)

        if (intent.hasExtra(Dz6StudentDetailsActivity.ID_KEY_EDIT)) {

            returnIdAndStudent()
            showDataStudent()
            savedProfile()
        } else {
            saveNewProfile()
        }
    }

    private fun returnIdAndStudent() {
        val id = intent.getLongExtra(ID_KEY_EDIT, -1)
        var studentsSinglton = Dz6StudentsSinglton
        student = studentsSinglton.findStudentById(id)
    }

    private fun showDataStudent() {
        nameEditProfileEditText.setText(student!!.name)
        ageEditProfileEditText.setText(student!!.age.toString())
        urlEditProfileEditText.setText(student!!.url)
    }

    private fun savedProfile() {
        saveEditProfileButton.setOnClickListener {

            val studentDataEdited: Dz6Student
            val name: String
            val url: String
            val age: Int

            try {
                name = nameEditProfileEditText.text.toString()
                url = urlEditProfileEditText.text.toString()
                age = ageEditProfileEditText.text.toString().toInt()

                if (!pattern.matcher(url).matches()) {
                    Toast.makeText(this, "ERROR( URL ): Not valid URL", Toast.LENGTH_SHORT).show()
                    this.finish()
                } else if (age == null) {
                    Toast.makeText(this, "ERROR( AGE ): Not valid Age, must be >= 0", Toast.LENGTH_SHORT).show()
                    this.finish()
                } else if (name.isEmpty()) {
                    Toast.makeText(this, "ERROR( NAME ): Not valid Name, must be filled", Toast.LENGTH_SHORT).show()
                    this.finish()
                } else {
                    studentDataEdited = Dz6Student(student!!.id, url, name, age)
                    editStudent(student!!, studentDataEdited)

                    Toast.makeText(this, "profile successfully edited", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }

    fun saveNewProfile() {
        saveEditProfileButton.setOnClickListener {

            val name: String
            val url: String
            val age: Int

            try {

                name = nameEditProfileEditText.text.toString()
                url = urlEditProfileEditText.text.toString()
                age = ageEditProfileEditText.text.toString().toInt()

                if (!pattern.matcher(url).matches()) {
                    Toast.makeText(this, "ERROR( URL ): Not valid URL", Toast.LENGTH_SHORT).show()
                    this.finish()
                } else if (age <= 0) {
                    Toast.makeText(this, "ERROR( AGE ): Not valid Age, must be >= 0", Toast.LENGTH_SHORT).show()
                    this.finish()
                } else if (name.isEmpty()) {
                    Toast.makeText(this, "ERROR( NAME ): Not valid Name, must be filled", Toast.LENGTH_SHORT).show()
                    this.finish()
                } else {

                    val studentNew = Dz6Student(creatureNewId(), url, name, age)
                    addNewStudent(studentNew)

                    Toast.makeText(this, "New profile successfully created", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }
}
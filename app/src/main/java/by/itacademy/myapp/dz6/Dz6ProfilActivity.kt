package by.itacademy.myapp.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.myapp.R
import kotlinx.android.synthetic.main.activity_dz6_profil_student.*

class Dz6ProfilActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_profil_student)

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
}
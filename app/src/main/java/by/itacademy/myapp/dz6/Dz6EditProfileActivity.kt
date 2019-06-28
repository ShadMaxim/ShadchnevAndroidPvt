package by.itacademy.myapp.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.itacademy.myapp.R
import kotlinx.android.synthetic.main.activity_dz6_edit_profile.*

class Dz6EditProfileActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6_edit_profile)

        savedProfile()
    }

    private fun savedProfile(){
        saveEditProfileButton.setOnClickListener {
            val intent = Intent(this, Dz6Activity::class.java)
            Toast.makeText(this, "data saved successfully", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}
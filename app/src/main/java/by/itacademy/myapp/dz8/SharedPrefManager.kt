package by.itacademy.myapp.dz8

import android.content.Context
import android.content.SharedPreferences

private const val SHAR_PREF = "SHAR_PREF"
private const val TEXT_KEY = "TEXT_KEY"

class SharedPrefManager(private var context: Context?) {

    private val sharedPrefs: SharedPreferences = context!!.getSharedPreferences(SHAR_PREF, Context.MODE_PRIVATE)

    fun saveSharedPrefs(text: String) {
        sharedPrefs
            .edit()
            .putString(TEXT_KEY, text)
            .apply()
    }

    fun readUserText(): String {
        return sharedPrefs.getString(TEXT_KEY, "") ?: "!!! ERROR: text not find !!!"
    }
}
package fekri.multithreading

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fekri.multithreading.databinding.ActivityMainBinding

const val KEY_DATA = "data"
const val KEY_NAME = "name"
const val KEY_EMAIL = "email"
const val KEY_GENDER_FEMALE = "isFemale"
const val KEY_GENDER_MALE = "isMale"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(KEY_DATA, Context.MODE_PRIVATE)

        // put data into shared preferences -->
        binding.btnSubmit.setOnClickListener {

            // method 1 -->
            val name = binding.edtInputName.text.toString()
            sharedPreferences.edit().putString(KEY_NAME, name).apply()

            val email = binding.edtInputEmail.text.toString()
            sharedPreferences.edit().putString(KEY_EMAIL, email).apply()

            if (binding.radioBtnFemale.isChecked) {
                // user is female -->
                sharedPreferences.edit().putBoolean(KEY_GENDER_FEMALE, true).apply()

            } else {
                // user is male -->
                sharedPreferences.edit().putBoolean(KEY_GENDER_MALE, false).apply()
            }

            /*            // method 2 -->
    /            val editor = sharedPreferences.edit()
    /            editor.putString( "nane", name )
    /            editor.putString( "email", email )
    /
    /            if( binding.radioBtnFemale.isChecked ) {
    /                // user is female -->
    /                editor.putBoolean( "isFemale", true )
    /            } else {
    /                // user is male -->
    /                editor.putBoolean( "isMale", false )
    /            }
    /
    /        editor.apply()
    */
        }

        // get from shared preferences -->
        val name = sharedPreferences.getString(KEY_NAME, "")
        val email = sharedPreferences.getString(KEY_EMAIL, "")
        val isFemale = sharedPreferences.getBoolean(KEY_GENDER_FEMALE, true)

        binding.edtInputName.setText(name)
        binding.edtInputEmail.setText(email)

        if (isFemale) {
            // is female
            binding.radioBtnFemale.isChecked = true
        } else {
            // is female
            binding.radioBtnMale.isChecked = true
        }

    }

    private fun funOfPreferences() {

        val isNmeAvailable = sharedPreferences.contains("name") // -- > it checks the exist item
        sharedPreferences.edit().remove("email").apply() // -- > remove an item by key
        sharedPreferences.edit().clear().apply() // -- > remove xml file

    }
}
package com.example.lab_week_01

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.ui.semantics.text
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // kek OOP, inisiasi Objek aja
    private lateinit var nameDisplay: TextView
    private lateinit var numberDisplay: TextView
    private lateinit var nameInput: TextInputEditText
    private lateinit var numberInput: TextInputEditText

    // Function inisialisasi display
    private fun initViews() {
        nameDisplay = findViewById(R.id.name_display)
        numberDisplay = findViewById(R.id.number_display)
        nameInput = findViewById(R.id.name_input)
        numberInput = findViewById(R.id.number_input)
    }

    //func trigger submit button proses
    private fun setupClickListener() {
        val nameSubmit = findViewById<Button>(R.id.name_submit)
        nameSubmit.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val number = numberInput.text.toString().trim()

            validateAndDisplayName(name)
            validateAndDisplayNumber(number)
        }
    }

    // Function validasi nama
    private fun validateAndDisplayName(name: String): Boolean {
        return if (name.isNotEmpty()) {
            nameDisplay.text = getString(R.string.name_greet) + name
            true
        } else {
            showCenteredToast(getString(R.string.empty_field))
            false
        }
    }

    // Function validasi nomor
    private fun validateAndDisplayNumber(number: String): Boolean {
        return when {
            number.isEmpty() -> {
                showCenteredToast(getString(R.string.empty_field))
                false
            }
            number.toIntOrNull() == null -> {
                showCenteredToast(getString(R.string.invalid_number))
                false
            }
            number.length != 11 -> {
                showCenteredToast(getString(R.string.invalid_number))
                false
            }
            else -> {
                numberDisplay.text = getString(R.string.number_greet) + number
                true
            }
        }
    }

    // Function taro error message
    private fun showCenteredToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
    }

    // Kek Main Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupClickListener()
    }
}
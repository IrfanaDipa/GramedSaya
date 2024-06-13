package com.example.gramedsaya.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gramedsaya.MainActivity
import com.example.gramedsaya.R
import com.example.gramedsaya.adapter.SharedPreferencesHelper

class AdminLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        val adminKeyEditText: EditText = findViewById(R.id.admin_key_edit_text)
        val loginButton: Button = findViewById(R.id.login_button)
        val backButton: Button = findViewById(R.id.btn_back)

        loginButton.setOnClickListener {
            val enteredKey = adminKeyEditText.text.toString()

            if (verifyAdminKey(enteredKey)) {
                SharedPreferencesHelper.setAdmin(this, true)
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Invalid Admin Key", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            startActivity(Intent(this@AdminLoginActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun verifyAdminKey(key: String): Boolean {
        val correctKey = "admin123" // Ganti dengan admin key yang Anda inginkan
        return key == correctKey
    }
}
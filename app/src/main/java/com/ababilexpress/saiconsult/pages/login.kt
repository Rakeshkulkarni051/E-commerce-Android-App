package com.ababilexpress.saiconsult.pages

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ababilexpress.saiconsult.API_interfaces.user_auth
import com.ababilexpress.saiconsult.Data.User.AuthResponse
import com.ababilexpress.saiconsult.Data.User.LoginRequest
import com.ababilexpress.saiconsult.MainActivity
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.databinding.ActivityLoginBinding
import com.ababilexpress.saiconsult.databinding.ActivitySignupBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signuptxt.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }

        binding.submit.setOnClickListener {
            Toast.makeText(this@login, "Login successful!", Toast.LENGTH_SHORT)
                .show()

            val intent = Intent(this@login, MainActivity::class.java)
            startActivity(intent)
            finish()


        }
    }


    private fun validateFields(): Boolean {
        val email = binding.email.editText?.text.toString().trim()
        val password = binding.password.editText?.text.toString().trim()

        // Validate Email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Enter a valid email"
            return false
        } else {
            binding.email.error = null
        }

        // Validate Password
        if (password.isEmpty()) {
            binding.password.error = "Password must be filled"
            return false
        } else {
            binding.password.error = null
        }
        return true
    }
}
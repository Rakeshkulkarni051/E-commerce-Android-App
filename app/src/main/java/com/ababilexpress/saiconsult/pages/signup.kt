package com.ababilexpress.saiconsult.pages

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ababilexpress.saiconsult.API_interfaces.user_auth
import com.ababilexpress.saiconsult.Data.User.AuthResponse
import com.ababilexpress.saiconsult.Data.User.RegisterRequest
import com.ababilexpress.saiconsult.databinding.ActivitySignupBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var api: user_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ababilexpress.com/api/v1/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(user_auth::class.java)

        //Move existing user to login page
        binding.logintext.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        // Set onClickListener to the Register button
        binding.submit.setOnClickListener {
            if (validateFields()) {
                // Proceed with API call or further steps
                    RegisterUser()

                Toast.makeText(this, "All fields are valid!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateFields(): Boolean {
        val firstName = binding.FName.editText?.text.toString().trim()
        val lastName = binding.LName.editText?.text.toString().trim()
        val email = binding.email.editText?.text.toString().trim()
        val password = binding.password.editText?.text.toString().trim()
        val confirmPassword = binding.conPassword.editText?.text.toString().trim()

        // Validate First Name
        if (firstName.isEmpty() || firstName.length < 1) {
            binding.FName.error = "Enter a valid first name"
            return false
        } else {
            binding.FName.error = null
        }

        //Validate l name
        if (lastName.isEmpty()) {
            binding.LName.error = "Field cant be empty"
            return false
        } else {
            binding.LName.error = null
        }

        // Validate Email
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Enter a valid email"
            return false
        } else {
            binding.email.error = null
        }

        // Validate Password
        if (password.isEmpty() || password.length < 6) {
            binding.password.error = "Password must be greater than 6 characters"
            return false
        } else {
            binding.password.error = null
        }

        // Validate Confirm Password
        if (confirmPassword != password) {
            binding.conPassword.error = "Passwords do not match"
            return false
        } else {
            binding.conPassword.error = null
        }
        return true
    }

    //Retrofit API logic to send user data to server
    fun RegisterUser() {
        val firstName = binding.FName.editText?.text.toString().trim()
        val lastName = binding.LName.editText?.text.toString().trim()
        val email = binding.email.editText?.text.toString().trim()
        val password = binding.password.editText?.text.toString().trim()

        val registerRequest = RegisterRequest(
            first_name = firstName,
            last_name = lastName,
            email = email,
            password = password,
            password_confirmation = password
        )

        val call = api.registerUser(registerRequest)
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.message == "Your Account has been created successfully." ||
                        responseBody?.message == "Customer registered successfully.") {
                        Toast.makeText(this@signup, "Registration successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@signup, login::class.java)
                        startActivity(intent)
                        finish()
                    }else if (response.code() == 400 && response.body()?.message == "Invalid Request Parameters") {
                        Toast.makeText(this@signup, "User already exists. Please try logging in.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@signup, "Unexpected response: ${responseBody?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Unknown error"
                    Log.e("SignupError", "Status Code: ${response.code()}, Error: $errorMsg")
                    Toast.makeText(this@signup,  "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                // Handle failure in making the request
                Toast.makeText(this@signup, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("SignupError", "onFailure: ${t.message}")
            }
        })
    }
}
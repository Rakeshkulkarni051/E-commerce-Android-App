package com.ababilexpress.saiconsult.Data.User

data class RegisterRequest(val first_name: String,
                           val last_name: String,
                           val email: String,
                           val password: String,
                           val password_confirmation: String)

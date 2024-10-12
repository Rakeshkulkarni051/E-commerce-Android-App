package com.ababilexpress.saiconsult.Data.User

data class AuthResponse(
    val status: Boolean,
    val token: String?,
    val message: String
)

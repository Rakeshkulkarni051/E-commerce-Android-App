package com.ababilexpress.saiconsult.API_interfaces

import com.ababilexpress.saiconsult.Data.User.AuthResponse
import com.ababilexpress.saiconsult.Data.User.LoginRequest
import com.ababilexpress.saiconsult.Data.User.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface user_auth {

    @Headers("Content-Type: application/json")
    @POST("customer/register")
    fun registerUser(@Body request: RegisterRequest): Call<AuthResponse>

    @Multipart
    @POST("customer/login")
    fun loginUser(
        @Part("email") email: String,
        @Part("password") password: String
    ): Call<AuthResponse>
}

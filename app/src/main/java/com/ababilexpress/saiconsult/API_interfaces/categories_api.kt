package com.ababilexpress.saiconsult.API_interfaces

import com.ababilexpress.saiconsult.Data.Categories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface categories_api {
    @Headers("Accept: application/json")
    @GET("categories")
    fun getCategories(
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null,
        @Query("pagination") pagination: Int? = null
    ): Call<Categories>
}
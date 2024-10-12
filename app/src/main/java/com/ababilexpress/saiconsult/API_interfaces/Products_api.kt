package com.ababilexpress.saiconsult.API_interfaces
import com.ababilexpress.saiconsult.Data.Products.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Products_api {

    // Get new products
    @GET("products")
    fun getNewProducts(
        @Query("new") new: Int,      // Set "new" parameter to 1 to fetch new products
        @Query("limit") limit: Int, // Optional: Limit the number of products
        @Query("page") page: Int   // Optional: Specify the page number
    ): Call<Products>

    // Get featured products
    @GET("products")
    fun getFeaturedProducts(
        @Query("featured") isFeatured: Int = 1,  // Set "featured" parameter to 1 to fetch featured products
        @Query("limit") limit: Int? = null,      // Optional: Limit the number of products
        @Query("page") page: Int? = null         // Optional: Specify the page number
    ): Call<Products>

    @GET("products")
    fun getcateprods(
        @Query("category_id") category_id: Int,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1
    ): Call<Products>



}
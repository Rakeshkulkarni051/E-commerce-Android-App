package com.ababilexpress.saiconsult.Repository

import android.util.Log
import com.ababilexpress.saiconsult.API_interfaces.Products_api
import com.ababilexpress.saiconsult.Data.Products.Data_products
import com.ababilexpress.saiconsult.Data.Products.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Prod_API {
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://ababilexpress.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Products_api::class.java)

    fun getnewprod(
        onSuccess: (List<Data_products>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val productsBuilder = retrofitBuilder.getNewProducts(new = 1, limit = 10, page = 1)
        productsBuilder.enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let(onSuccess) ?: onFailure("Response body is null")
                } else {
                    Log.d("API Response", response.errorBody()?.string() ?: "Error with empty body")
                    onFailure("Failure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                onFailure("Error: ${t.message}")
            }
        })

    }

        fun getfetuprod(onSuccess: (List<Data_products>) -> Unit,
                        onFailure: (String) -> Unit){
            val productsBuilder = retrofitBuilder.getFeaturedProducts(isFeatured = 1, limit = 10, page = 1)
            productsBuilder.enqueue(object : Callback<Products> {
                override fun onResponse(call: Call<Products>, response: Response<Products>) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let(onSuccess) ?: onFailure("Response body is null")
                    } else {
                        Log.d("API Response", response.errorBody()?.string() ?: "Error with empty body")
                        onFailure("Failure: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<Products>, t: Throwable) {
                    onFailure("Error: ${t.message}")
                }
            })

        }
}

package com.ababilexpress.saiconsult.Repository

import com.ababilexpress.saiconsult.API_interfaces.Products_api
import com.ababilexpress.saiconsult.Data.Products.Data_products
import com.ababilexpress.saiconsult.Data.Products.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//This repository is used to navigate user from all categories to its products page of categories
class Categ_Prods {
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://ababilexpress.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Products_api::class.java)

    fun getProductsByCategory(
        categoryId: Int,
        onSuccess: (List<Data_products>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val productsCall = retrofitBuilder.getcateprods(category_id = categoryId)
        productsCall.enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let(onSuccess) ?: onFailure("Response body is null")
                } else {
                    onFailure("Failure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                onFailure("Error: ${t.message}")
            }
        })
    }

}
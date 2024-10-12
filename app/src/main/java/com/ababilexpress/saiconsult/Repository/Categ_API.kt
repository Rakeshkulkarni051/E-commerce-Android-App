package com.ababilexpress.saiconsult.Repository
import com.ababilexpress.saiconsult.API_interfaces.categories_api
import com.ababilexpress.saiconsult.Data.Categories
import com.ababilexpress.saiconsult.Data.Data_categories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Categ_API {
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://ababilexpress.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(categories_api::class.java)

    fun getCategories(
        onSuccess: (List<Data_categories>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val categoriesBuilder = retrofitBuilder.getCategories(limit = 10, page = 1, pagination = 1)
        categoriesBuilder.enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { categories ->
                        // Sort categories by a specific property, e.g., name
                        val sortedCategories = categories.sortedBy { it.position }
                        onSuccess(sortedCategories)
                    } ?: onFailure("Response body is null")
                } else {
                    onFailure("Failure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                onFailure("Error: ${t.message}")
            }
        })
    }

}
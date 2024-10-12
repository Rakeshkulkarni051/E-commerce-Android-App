package com.ababilexpress.saiconsult.pages

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.ababilexpress.saiconsult.Adapters.Productsnew_adap
import com.ababilexpress.saiconsult.Data.Products.Data_products
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.Repository.Categ_Prods
import com.ababilexpress.saiconsult.databinding.ActivityCategoriesProdBinding
import com.ababilexpress.saiconsult.databinding.ActivityMainBinding

class CategoriesProd : AppCompatActivity() {

    lateinit var binding: ActivityCategoriesProdBinding
    private val Catgories_prodAPI=Categ_Prods()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        binding= ActivityCategoriesProdBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.rcyCatpro.visibility=View.GONE

        val categoryId = intent.getIntExtra("category_id", -1)
        val categoryName = intent.getStringExtra("category_name")


        binding.back.setOnClickListener {
            finish()
        }

        if (categoryId != -1) {
            loadProducts(categoryId)
        } else {
            Toast.makeText(this, "Invalid category", Toast.LENGTH_SHORT).show()
        }

        binding.cateName.text=categoryName
    }

    private fun loadProducts(categoryId: Int) {
        Catgories_prodAPI.getProductsByCategory( categoryId = categoryId,
            onSuccess = { products ->
                binding.shimmerLayout.visibility=View.GONE
                binding.rcyCatpro.visibility=View.VISIBLE
                setupRecyclerView(products)
            },
            onFailure = { errorMessage ->
                Log.e("ProductsActivity", errorMessage)
            }
        )
    }//Functions end hear

    //setting up recycler view in different method for filter query base updates

    private fun setupRecyclerView(products: List<Data_products>) {

        val gridLayoutManager = GridLayoutManager(this, 2) // 2 columns in the grid
        binding.rcyCatpro.layoutManager = gridLayoutManager
        val adapter = Productsnew_adap(this, products)
        binding.rcyCatpro.adapter = adapter

    }

}
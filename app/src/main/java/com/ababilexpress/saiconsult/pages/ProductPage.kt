package com.ababilexpress.saiconsult.pages

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ababilexpress.saiconsult.Fragments.Cart
import com.ababilexpress.saiconsult.databinding.ActivityProductPageBinding
import com.denzcoskun.imageslider.models.SlideModel

class ProductPage : AppCompatActivity() {

    lateinit var binding: ActivityProductPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        // Initialize view binding
        binding = ActivityProductPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data passed from the previous activity
        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("Description")
        val productId = intent.getStringExtra("productID")
        val cartImage=intent.getStringExtra("cartImage")
        val productPrice = intent.getStringExtra("price")
        val avgReview = intent.getStringExtra("Avgratings")

        val imageUrls = intent.getStringArrayExtra("imageUrls")

        // Set up the image slider
        val imageList = ArrayList<SlideModel>()
        imageUrls?.forEach { url ->
            imageList.add(SlideModel(url))
        }

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)


        // Set the values of the product to the views
        binding.productName.text = productName
        binding.price.text = productPrice
        binding.avgratings.text = "($avgReview reviews)"

        //Converting raw discription of html to string
        val plainTextDescription = Html.fromHtml(productDescription).toString()
        binding.discription.text = plainTextDescription

        //applying quantity increment and decrement
        var quantity = 1
        var cartbadge=0
        binding.tvQuantity.text = quantity.toString()
        binding.cartBadge.text=cartbadge.toString()

        binding.btnIncrease.setOnClickListener {
            quantity++
            binding.tvQuantity.text = quantity.toString()
        }

        binding.btnDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvQuantity.text = quantity.toString()
            }
        }

            binding.backBtn.setOnClickListener {
                finish()
            }

           binding.cartBtn.setOnClickListener {
               cartbadge++
               binding.cartBadge.text = cartbadge.toString() // Update the badge value immediately
               Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show()

               val intent = Intent(this, com.ababilexpress.saiconsult.pages.Cart::class.java)
               intent.putExtra("productName", productName)
               intent.putExtra("price", productPrice)
               intent.putExtra("cartImage", cartImage)
               intent.putExtra("quantity",quantity)
           }

            binding.cartIcon.setOnClickListener {
                    if(cartbadge!=0){
                        val intent = Intent(this, com.ababilexpress.saiconsult.pages.Cart::class.java)
                        intent.putExtra("productName", productName)
                        intent.putExtra("price", productPrice)
                        intent.putExtra("cartImage", cartImage)
                        intent.putExtra("quantity",quantity)
                        startActivity(intent)
                    }
                else{
                        Toast.makeText(this, "Please add item to cart", Toast.LENGTH_SHORT).show()
                    }
            }

            binding.compareBtn.setOnClickListener {
                val intent= Intent(applicationContext,Compare::class.java)
                startActivity(intent)
                //will pass products later
            }
        }
    }

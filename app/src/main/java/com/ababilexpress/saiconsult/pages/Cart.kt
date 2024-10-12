package com.ababilexpress.saiconsult.pages

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.databinding.ActivityCartBinding
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.Locale

class Cart : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    private var quantity: Int = 1
    private var productPrice: Double = 0.0
    private var productName: String? = null
    private var productPriceString: String? = null
    private var cartImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        // Initialize view binding
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data passed from ProductPage
        productName = intent.getStringExtra("productName")
        productPriceString = intent.getStringExtra("price")
        cartImage = intent.getStringExtra("cartImage")

        // Attempt to parse the price
        productPrice = parsePrice(productPriceString) ?: 0.0
        quantity = intent.getIntExtra("quantity", 1) // Use getIntExtra with a default value

        // Initialize views with data
        updateUI()

        // Increase quantity button click listener
        binding.btnIncrease.setOnClickListener {
            quantity++
            updateUI()
        }

        // Decrease quantity button click listener
        binding.btnDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateUI()
            }
        }
    }

    private fun parsePrice(priceString: String?): Double? {
        return try {
            // Remove any currency symbols or non-numeric characters
            val cleanedString = priceString?.replace("[^\\d.]".toRegex(), "")
            cleanedString?.toDoubleOrNull()
        } catch (e: NumberFormatException) {
            null
        }
    }

    private fun updateUI() {
        // Calculate total amount
        val totalAmt = productPrice * quantity

        // Format the total amount
        val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val formattedTotalAmt = formatter.format(totalAmt)

        if (productName.isNullOrEmpty()) {
            // Cart is empty
            binding.cartempty.visibility = View.VISIBLE
            binding.carticon.visibility = View.VISIBLE
            binding.cartscrn.visibility = View.GONE
            binding.checkout.visibility = View.GONE
            binding.subtaotal.visibility = View.GONE
        } else {
            // Cart has items
            binding.cartempty.visibility = View.GONE
            binding.carticon.visibility = View.GONE
            binding.cartscrn.visibility = View.VISIBLE
            binding.checkout.visibility = View.VISIBLE
            binding.subtaotal.visibility = View.VISIBLE

            binding.prodName.text = productName
            binding.prodPrice.text = productPriceString
            binding.tvQuantity.text = quantity.toString() // Set the quantity

            // Use resource string with placeholder
            binding.subtaotal.text = getString(R.string.subtotal_label, formattedTotalAmt)

            cartImage?.let {
                Picasso.get()
                    .load(it)
                    .into(binding.prdImg)

                binding.remove.setOnClickListener{
                    binding.cartempty.visibility = View.VISIBLE
                    binding.carticon.visibility = View.VISIBLE
                    binding.cartscrn.visibility = View.GONE
                    binding.checkout.visibility = View.GONE
                    binding.subtaotal.visibility = View.GONE
                }
            }
        }
    }
}







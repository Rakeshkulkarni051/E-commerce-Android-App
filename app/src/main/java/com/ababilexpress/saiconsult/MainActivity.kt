package com.ababilexpress.saiconsult

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ababilexpress.saiconsult.API_interfaces.categories_api
import com.ababilexpress.saiconsult.Data.Categories
import com.ababilexpress.saiconsult.Fragments.Cart
import com.ababilexpress.saiconsult.Fragments.Home
import com.ababilexpress.saiconsult.Fragments.User
import com.ababilexpress.saiconsult.Fragments.Vendor
import com.ababilexpress.saiconsult.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView

        AddFragment(Home())



        // Set up a listener for item selections
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(Home())
                    true
                }
                R.id.Vendor -> {
                   replaceFragment(Vendor())
                    true
                }
                R.id.cart -> {
                    replaceFragment(Cart())
                    true
                }
                R.id.Account -> {
                    replaceFragment(User())
                    true
                }
                else -> false
            }
        }
    }

   private fun AddFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTraction = fragmentManager.beginTransaction()
        fragmentTraction.add(R.id.fragment_frame, fragment)
        fragmentTraction.commit()
    }

   private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_frame, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}
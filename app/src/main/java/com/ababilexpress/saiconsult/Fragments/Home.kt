package com.ababilexpress.saiconsult.Fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.ababilexpress.saiconsult.Adapters.Categories_adap
import com.ababilexpress.saiconsult.Adapters.Productsnew_adap
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.Repository.Categ_API
import com.ababilexpress.saiconsult.Repository.Prod_API
import com.ababilexpress.saiconsult.databinding.FragmentHomeBinding
import com.ababilexpress.saiconsult.pages.Compare
import com.ababilexpress.saiconsult.pages.login
import com.ababilexpress.saiconsult.pages.signup
import com.google.android.material.navigation.NavigationView

class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var cate_adapter: Categories_adap
    private val Categ_API = Categ_API()
    private val Prod_API=Prod_API()
    private lateinit var newpro_adapter:Productsnew_adap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rcyCateg.visibility = View.GONE
        binding.rcyNewprod.visibility=View.GONE
        binding.rcyFetprod.visibility=View.GONE
        binding.allproRcy.visibility=View.GONE


        //Categories:
        // Initialize the adapter with an empty list
        cate_adapter = Categories_adap(requireActivity(), emptyList())
        // Set layout manager
        binding.rcyCateg.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        // Set the adapter
        binding.rcyCateg.adapter = cate_adapter

        //new Products:
        newpro_adapter= Productsnew_adap(requireActivity(), emptyList())

        binding.rcyNewprod.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        binding.rcyFetprod.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        binding.allproRcy.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)



        loadCategories()

        loadnewprod() //Loading both new and all in same list by API

        loadfetprod()


        binding.menu.setOnClickListener {
            showCustomMenu()
        }
        binding.compare.setOnClickListener {
            val intent=Intent(requireContext(),Compare::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun showCustomMenu() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.slider_menu)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setGravity(Gravity.START)

        val navigationView = dialog.findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.login_menu -> {
                    val intent = Intent(requireContext(), login::class.java)
                    startActivity(intent)
                }

                R.id.signup_menu -> {
                    val intent = Intent(requireContext(), signup::class.java)
                    startActivity(intent)
                }
                // to Add other menu item cases here
            }
            dialog.dismiss() // Dismiss the dialog when an item is clicked
            true
        }
        dialog.show()
    }


    private fun loadCategories() {
        Categ_API.getCategories(
            onSuccess = { categories ->
                // Update the adapter with the fetched categories
                cate_adapter = Categories_adap(requireActivity(), categories)
                binding.rcyCateg.adapter = cate_adapter

                binding.rcyCateg.visibility=View.VISIBLE
                binding.shimmerLayout.visibility=View.GONE
            },
            onFailure = { errorMessage ->
                Log.d("API Response", "Response body is null")
            }
        )
    }

    private fun loadnewprod(){
        Prod_API.getnewprod( onSuccess = { products ->
            // Update your adapter with the fetched products
            newpro_adapter = Productsnew_adap(requireActivity(), products)
            binding.rcyNewprod.adapter=newpro_adapter
            binding.allproRcy.adapter=newpro_adapter

            binding.rcyNewprod.visibility=View.VISIBLE

            binding.shimmerLayoutNewprod.visibility=View.GONE

            binding.shimmerLayoutAllpro.visibility=View.GONE
            binding.allproRcy.visibility=View.VISIBLE



        },
            onFailure = { errorMessage ->
                Log.d("API Response", errorMessage)
            }
        )
    }

    private fun loadfetprod(){
        Prod_API.getfetuprod(onSuccess = { products ->
            newpro_adapter=Productsnew_adap(requireActivity(),products)
            binding.rcyFetprod.adapter=newpro_adapter

            binding.rcyFetprod.visibility=View.VISIBLE

            binding.shimmerLayoutFetpro.visibility=View.GONE

        }, onFailure = { errorMessage ->
            Log.d("API Response", errorMessage)
        }
        )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.ababilexpress.saiconsult.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.databinding.FragmentCartBinding
import com.ababilexpress.saiconsult.databinding.FragmentHomeBinding
import com.ababilexpress.saiconsult.databinding.FragmentVendorBinding

class Vendor : Fragment() {

    private var _binding: FragmentVendorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVendorBinding.inflate(inflater, container, false)
        
        binding.signupBtn.setOnClickListener {
            val url = "https://ababilexpress.com/admin/vendor/new/create"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            startActivity(intent)
        }


    return binding.root
    }
}
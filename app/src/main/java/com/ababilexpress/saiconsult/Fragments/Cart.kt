package com.ababilexpress.saiconsult.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.databinding.FragmentCartBinding
import com.ababilexpress.saiconsult.pages.Cart

class Cart : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

            val intent=Intent(requireContext(),Cart::class.java)
            startActivity(intent)

            requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_frame, Home())
            .addToBackStack(null)
            .commit()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
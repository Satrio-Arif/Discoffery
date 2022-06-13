package com.project.discofferytemp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.discofferytemp.activity.MapsActivity
import com.project.discofferytemp.databinding.FragmentLocationBinding


class Location : Fragment() {
    private  var _binding: FragmentLocationBinding? =null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.homeFragment?.setOnClickListener {
             binding?.progressBar?.visibility =View.VISIBLE
            startActivity(Intent(activity, MapsActivity::class.java))

        }
    }

    override fun onStart() {
        super.onStart()
        binding?.progressBar?.visibility =View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
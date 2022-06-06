package com.project.discofferytemp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.discofferytemp.*
import com.project.discofferytemp.adapter.HomeAdapter
import com.project.discofferytemp.databinding.FragmentHomeBinding
import com.project.discofferytemp.helper.Data
import com.project.discofferytemp.model.Articles
import java.io.File


class Home : Fragment() {
    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private var articlesData = ArrayList<Articles>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesData = setData()
        showData(articlesData)
        binding?.profileImage?.setOnClickListener {

            val intent = Intent(this@Home.context, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding?.homeToPrice?.setOnClickListener {
            Data.flag = 1
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.frame_container, Price()).commit()
        }

        binding?.scanPhoto?.setOnClickListener {
            startCameraX()
        }
    }

    private fun startCameraX() {
        val intent = Intent(activity, CameraActivity::class.java)
        launchCamera2.launch(intent)
    }

    private val launchCamera2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == MainActivity.CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            val intent = Intent(activity, ScanPhoto::class.java)
            intent.putExtra("picture", myFile)
            intent.putExtra(
                "isBackCamera",
                isBackCamera
            )
            startActivity(intent)
        }
    }

    private fun showData(value: ArrayList<Articles>) {
        binding?.rvArticles?.layoutManager = LinearLayoutManager(activity)
        val adapter = HomeAdapter(requireActivity())
        adapter.setData(value)
        binding?.rvArticles?.adapter = adapter
    }

    private fun setData(): ArrayList<Articles> {
        val tempData = ArrayList<Articles>()
        val sumberData = "discoffery"
        val uri = resources.obtainTypedArray(R.array.data_photo).getResourceId(0, -1)
        val dataJudul = resources.getStringArray(R.array.judul)
        for (i in 0..8) {
            val article = Articles(
                sumber = sumberData,
                img = uri,
                judul = dataJudul[i],
                createdAt = "25-05-2022"
            )
            tempData.add(article)
        }
        return tempData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}





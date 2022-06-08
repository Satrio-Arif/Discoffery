package com.project.discofferytemp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.discofferytemp.*
import com.project.discofferytemp.adapter.HomeAdapter
import com.project.discofferytemp.databinding.FragmentHomeBinding
import com.project.discofferytemp.helper.Data
import com.project.discofferytemp.model.Articles
import com.project.discofferytemp.viewmodel.ArticleViewModel
import com.project.discofferytemp.viewmodel.HomeViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryArticle
import com.project.discofferytemp.viewmodel.ViewModelFactoryHome
import java.io.File


class Home : Fragment() {
    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var articlesData:ArrayList<Articles>
    private lateinit var model: HomeViewModel


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
        showLoading(true)
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
        model = ViewModelProvider(this, ViewModelFactoryHome.getInstance()).get(HomeViewModel::class.java)
        model.getArticle()
        model.article.observe(viewLifecycleOwner){

            this.articlesData =it
            for (i in it.indices){
                this.articlesData[i].img = resources.obtainTypedArray(R.array.data_photo).getResourceId(0, -1)
            }
            showData(this.articlesData)
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
        showLoading(false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(Is_load: Boolean) {
        if (Is_load) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }


}





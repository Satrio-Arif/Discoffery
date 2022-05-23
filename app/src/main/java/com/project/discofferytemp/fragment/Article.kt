package com.project.discofferytemp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.discofferytemp.R
import com.project.discofferytemp.adapter.ArticleAdapter
import com.project.discofferytemp.databinding.FragmentArticleBinding
import com.project.discofferytemp.model.Articles


class Article : Fragment() {
    private lateinit var binding:FragmentArticleBinding
    private var articlesData = ArrayList<Articles>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesData =setData()
        showData(articlesData)
    }

    private fun showData(value:ArrayList<Articles>){
        binding.rvArticles.layoutManager =LinearLayoutManager(activity)
        val adapter =ArticleAdapter()
        adapter.setData(value)
        binding.rvArticles.adapter =adapter
    }

    private fun setData():ArrayList<Articles>
    {
        val tempData = ArrayList<Articles>()
        val sumberData ="discoffery"
        val uri =resources.obtainTypedArray(R.array.data_photo).getResourceId(0,-1)
        val dataJudul =resources.getStringArray(R.array.judul)
        for (i in 0..4){
            val article =Articles(sumber = sumberData,img = uri,judul =dataJudul[i],createdAt = "25-05-2022" )
          tempData.add(article)
        }
        return tempData
    }




}
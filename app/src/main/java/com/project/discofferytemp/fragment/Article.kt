package com.project.discofferytemp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.discofferytemp.R
import com.project.discofferytemp.adapter.ArticleAdapter
import com.project.discofferytemp.databinding.FragmentArticleBinding
import com.project.discofferytemp.model.Articles
import com.project.discofferytemp.viewmodel.ArticleViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryArticle


class Article : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding
    private lateinit var model:ArticleViewModel
    private lateinit var article:ArrayList<Articles>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model =ViewModelProvider(this, ViewModelFactoryArticle.getInstance()).get(ArticleViewModel::class.java)
        showLoading(true)
        model.getArticle()

        model.article.observe(viewLifecycleOwner){

           this.article =it
            for (i in it.indices){
                this.article[i].img = resources.obtainTypedArray(R.array.data_photo).getResourceId(0, -1)
            }
            showData(this.article)
        }
    }

    private fun showData(value: ArrayList<Articles>) {
        binding?.rvArticles?.layoutManager = LinearLayoutManager(activity)
        val adapter = ArticleAdapter(requireActivity())
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
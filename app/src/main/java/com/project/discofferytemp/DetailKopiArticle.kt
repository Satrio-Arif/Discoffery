package com.project.discofferytemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.discofferytemp.adapter.ArticleAdapter
import com.project.discofferytemp.databinding.ActivityDetailKopiArticleBinding
import com.project.discofferytemp.databinding.ActivityDetailKopiBinding
import com.project.discofferytemp.model.Articles

class DetailKopiArticle : AppCompatActivity() {
    private lateinit var binding:ActivityDetailKopiArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKopiArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val data = intent.getParcelableExtra<Articles>(ArticleAdapter.DATA) as Articles
        binding.isiArtikel.text =data.Content
        binding.judulArtikel.text =data.Title
    }
}
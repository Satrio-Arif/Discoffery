package com.project.discofferytemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailKopiArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kopi_article)
        supportActionBar?.hide()
    }
}
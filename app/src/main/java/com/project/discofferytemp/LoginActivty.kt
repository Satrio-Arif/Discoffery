package com.project.discofferytemp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.project.discofferytemp.databinding.ActivityLoginActivtyBinding

class LoginActivty : AppCompatActivity() {
    private lateinit var binding : ActivityLoginActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}
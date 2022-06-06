package com.project.discofferytemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.discofferytemp.databinding.ActivityDetailKopiBinding

class DetailKopi : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKopiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKopiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCariKopi.setOnClickListener {
            Toast.makeText(this, "apa", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@DetailKopi, ButtomNavigation::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}
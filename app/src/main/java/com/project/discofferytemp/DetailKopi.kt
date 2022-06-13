package com.project.discofferytemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.project.discofferytemp.databinding.ActivityDetailKopiBinding
import com.project.discofferytemp.model.ArticlesDetail
import com.project.discofferytemp.viewmodel.DetailKopiViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryDetailKopi

class DetailKopi : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKopiBinding
    private lateinit var model:DetailKopiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKopiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data =intent.getIntExtra(ScanPhoto.DATA,0)
        model =ViewModelProvider(this,ViewModelFactoryDetailKopi.getInstance()).get(DetailKopiViewModel::class.java)
        model.getData(data)
        model.data.observe(this){
            showData(it.data.get(0))
        }

    }

    private fun showData(param:ArticlesDetail){
        binding.informasiKopi.text =param.jenis_kopi
        binding.nationality.text =param.origin
        binding.nationality2.text =param.variety
        binding.aboutCoffeedetail.text =param.about
        binding.nationality3.text =param.altitude
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@DetailKopi, ButtomNavigation::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}
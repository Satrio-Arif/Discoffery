package com.project.discofferytemp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.project.discofferytemp.adapter.MapsAdapter
import com.project.discofferytemp.databinding.ActivityViewPlaceBinding
import com.project.discofferytemp.helper.GoogleMaps
import com.project.discofferytemp.viewmodel.MapsViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryMaps


class ViewPlace : AppCompatActivity() {
    private lateinit var binding:ActivityViewPlaceBinding
    private lateinit var mapsViewModel: MapsViewModel
    private var url:String=""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityViewPlaceBinding.inflate(layoutInflater)
        val id =intent.getStringExtra(MapsAdapter.KEY)
        setContentView(binding.root)

        showLoading(true)
        mapsViewModel = ViewModelProvider(this,ViewModelFactoryMaps.getInstance()).get(MapsViewModel::class.java)
        url =GoogleMaps.getPlaceDetail(id?:"")
        mapsViewModel.getDetailStore(url)
        mapsViewModel.placeDetail.observe(this){detailPlace->
            if (!detailPlace.status.equals("Error")){
                showLoading(false)
                val map =Intent(Intent.ACTION_VIEW, Uri.parse(detailPlace.result.url))
                startActivity(map)
                finish()

            }else{
                showToast("Terjadi kesalahan")
                Thread.sleep(1000)
                finish()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoad: Boolean) {
        if (isLoad) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
package com.project.discofferytemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.project.discofferytemp.databinding.ActivityButtomNavigationBinding
import com.project.discofferytemp.fragment.Article
import com.project.discofferytemp.fragment.Home
import com.project.discofferytemp.fragment.Location
import com.project.discofferytemp.fragment.Price
import com.project.discofferytemp.helper.Data
import java.io.File

class ButtomNavigation : AppCompatActivity() {

    private lateinit var binding:ActivityButtomNavigationBinding
    private val transaction = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtomNavigationBinding.inflate(layoutInflater)

        setContentView(binding.root)
        transaction.beginTransaction().add(R.id.frame_container,Home()).commit()

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Nearby->{
                    val fragment =Location()
                    changeFragment(fragment)
                    Thread.sleep(1000)
                    val intent = Intent(this@ButtomNavigation,MapsActivity::class.java)
                    startActivity(intent)
                }
                R.id.Article->{
                    val fragment = Article()

                    changeFragment(fragment)
                }
                R.id.home->{
                    val fragment = Home()
                    changeFragment(fragment)
                }
                R.id.price->{
                    val fragment = Price()
                    changeFragment(fragment)
                }
            }
            true
        }
        binding.fab.setOnClickListener {
            startCameraX()
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launchCamera2.launch(intent)
    }

    private val launchCamera2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == MainActivity.CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            val intent = Intent(this,ScanPhoto::class.java)
            intent.putExtra("picture", myFile)
            intent.putExtra(
                "isBackCamera",
                isBackCamera
            )
            startActivity(intent)

        }
    }

    private fun changeFragment(fragment:Fragment){
        Data.data.add(fragment::class.java.simpleName)
        transaction.beginTransaction().replace(R.id.frame_container,fragment).commit()
    }

    override fun onBackPressed() {

        if (binding.bottomNavigationView.selectedItemId == R.id.home){
            super.onBackPressed()
            finish()
        }else{
            binding.bottomNavigationView.selectedItemId = R.id.home
        }

    }






}

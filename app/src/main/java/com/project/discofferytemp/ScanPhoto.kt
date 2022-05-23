package com.project.discofferytemp

import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.discofferytemp.databinding.ActivityScanPhotoBinding
import java.io.File

class ScanPhoto : AppCompatActivity() {
    private lateinit var binding: ActivityScanPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val file = intent.getSerializableExtra("picture") as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", true)

        if (file != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val result = rotateBitmap(
                    BitmapFactory.decodeFile(file.path),
                    isBackCamera
                )
                binding.previewImageView.setImageBitmap(result)
            } else {
                val result = BitmapFactory.decodeFile(file.path)
                binding.previewImageView.setImageBitmap(result)
            }
        }
    }
}
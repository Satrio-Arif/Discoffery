package com.project.discofferytemp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.ViewModelProvider
import com.project.discofferytemp.databinding.ActivityScanPhotoBinding
import com.project.discofferytemp.viewmodel.ClasificationViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryClasification
import java.io.File
import kotlin.math.roundToInt

class ScanPhoto : AppCompatActivity() {

    private lateinit var binding: ActivityScanPhotoBinding
    private lateinit var model:ClasificationViewModel

    private val classes = arrayOf("Aceh gayo", "Dampit", "Toraja")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanPhotoBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        model =ViewModelProvider(this,ViewModelFactoryClasification.getInstance()).get(ClasificationViewModel::class.java)

        val file = intent.getSerializableExtra("picture2") as File
        //val isBackCamera = intent.getBooleanExtra("isBackCamera", true)

        if (file != null){
            val bitmap1 =BitmapFactory.decodeFile(file.absolutePath).run {
                val exif = ExifInterface(file.absolutePath)
                val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)
                val matrix = Matrix()
                when (orientation) {
                    6 -> {
                        matrix.postRotate(90F)
                    }
                    3 -> {
                        matrix.postRotate(180F)
                    }
                    8 -> {
                        matrix.postRotate(270F)
                    }
                }
                Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
            }

            binding.previewImageView.setImageBitmap(bitmap1)
            val resize = Bitmap.createScaledBitmap(bitmap1,224,224,true)

            model.clasification(resize,application)

            model.data.observe(this){result->
                if (result.isNotEmpty()){
                    val position  = getHighest(result)
                    Toast.makeText(this,classes[position],Toast.LENGTH_SHORT).show()
                    binding.confidenceAcehGayo.text = getString(R.string.aceh_gayo,convertToPersen(result[0]).toString(),"%")
                    binding.confidenceDampit.text = getString(R.string.dampit,convertToPersen(result[1]).toString(),"%")
                    binding.confidenceToraja.text = getString(R.string.toraja,convertToPersen(result[2]).toString(),"%")

                }
            }

        }
    }



    private fun convertToPersen(param:Float):Int{

        val data = ((param * 100.0).roundToInt() / 100.0).toFloat()
        return if (data == 0.0F){
            1
        }else{
            (data*100).roundToInt()
        }
    }
    private fun getHighest(param:FloatArray):Int{
        var max=0.0F
        var pos=0
        for (i in 0 until param.size){
            if (param[i] > max){
                max =param[i]
                pos = i
            }
        }
        return pos
    }
}
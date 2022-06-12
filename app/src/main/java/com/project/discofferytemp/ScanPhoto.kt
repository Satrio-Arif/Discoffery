package com.project.discofferytemp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.ViewModelProvider
import com.project.discofferytemp.databinding.ActivityScanPhotoBinding
import com.project.discofferytemp.helper.Data
import com.project.discofferytemp.viewmodel.ClasificationViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryClasification
import java.io.File
import kotlin.math.roundToInt

class ScanPhoto : AppCompatActivity() {

    private lateinit var binding: ActivityScanPhotoBinding
    private lateinit var model:ClasificationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanPhotoBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        model =ViewModelProvider(this,ViewModelFactoryClasification.getInstance()).get(ClasificationViewModel::class.java)

        val file = intent.getSerializableExtra("picture") as File
        if (file != null){
            val bitmap1 =BitmapFactory.decodeFile(file.absolutePath).run {
                val exif = ExifInterface(file.absolutePath)
                val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)
                val matrix = Matrix()
                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> {
                        matrix.postRotate(90F)
                    }
                    ExifInterface.ORIENTATION_ROTATE_180-> {
                        matrix.postRotate(180F)
                    }
                    ExifInterface.ORIENTATION_ROTATE_270 -> {
                        matrix.postRotate(270F)
                    }
                }
                Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
            }

            binding.previewImageView.setImageBitmap(bitmap1)

            val resize = Bitmap.createScaledBitmap(bitmap1,224,224,true)

            model.clasification(resize,application)
            classification()
        }

        binding.detailprice.setOnClickListener {
            val intent =Intent(this@ScanPhoto,DetailKopi::class.java)
            intent.putExtra(DATA,0)
            startActivity(intent)
        }
        binding.detailprice2.setOnClickListener {
            val intent =Intent(this@ScanPhoto,DetailKopi::class.java)
            intent.putExtra(DATA,1)
            startActivity(intent)
        }
        binding.detailprice3.setOnClickListener {
            val intent =Intent(this@ScanPhoto,DetailKopi::class.java)
            intent.putExtra(DATA,2)
            startActivity(intent)
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
        var max=0F
        var pos=0
        for (i in param.indices){
            if (param[i] > max){
                max =param[i]
                pos = i
            }
        }
        return pos
    }

    private fun classification(){
        model.data.observe(this){result->
            if (result.isNotEmpty()){
                val position  = getHighest(result)
                Toast.makeText(this, Data.classes[position],Toast.LENGTH_SHORT).show()
                binding.confidenceAcehGayo.text = getString(R.string.aceh_gayo,convertToPersen(result[0]).toString(),"%")
                binding.confidenceDampit.text = getString(R.string.dampit,convertToPersen(result[1]).toString(),"%")
                binding.confidenceToraja.text = getString(R.string.toraja,convertToPersen(result[2]).toString(),"%")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@ScanPhoto,ButtomNavigation::class.java)
        intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
    companion object{
        const val DATA ="data"
    }
}
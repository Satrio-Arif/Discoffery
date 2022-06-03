package com.project.discofferytemp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.ViewModelProvider
import com.project.discofferytemp.databinding.ActivityScanPhotoBinding
import com.project.discofferytemp.ml.Kopi
import com.project.discofferytemp.viewmodel.ClasificationViewModel
import com.project.discofferytemp.viewmodel.ViewModelFactoryClasification
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.roundToInt

class ScanPhoto : AppCompatActivity() {
    private lateinit var bitmap:Bitmap
    private lateinit var binding: ActivityScanPhotoBinding
    private lateinit var model:ClasificationViewModel

    val classes = arrayOf("Aceh gayo", "Dampit", "Toraja")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model =ViewModelProvider(this,ViewModelFactoryClasification.getInstance()).get(ClasificationViewModel::class.java)

        val file = intent.getSerializableExtra("picture2") as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", true)

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
            bitmap = BitmapFactory.decodeFile(file.path)
            val resize = Bitmap.createScaledBitmap(bitmap1,224,224,true)
            model.clasification(resize,application)
            model.data.observe(this){result->
                if (result.isNotEmpty()){
                    var position  = getHighest(result)
                    Toast.makeText(this,classes[position],Toast.LENGTH_SHORT).show()
                    binding.confidenceAcehGayo.text = getString(R.string.aceh_gayo,convertToPersen(result[0]).toString(),"%")
                    binding.confidenceDampit.text = getString(R.string.dampit,convertToPersen(result[1]).toString(),"%")
                    binding.confidenceToraja.text = getString(R.string.toraja,convertToPersen(result[2]).toString(),"%")

                }
            }

        }
    }

    private fun clasification(resize: Bitmap?) {

        val model = Kopi.newInstance(this)

        val byteBuffer = ByteBuffer.allocateDirect(4*224*224*3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val arrayOfZeros = IntArray(224*244)
        resize?.getPixels(arrayOfZeros,0,resize.width,0,0,resize.width,resize.height)
        var pixels=0
        for (i in 0..223){
            for (j in 0..223){
                var value = arrayOfZeros[pixels++]
                byteBuffer.putFloat((value shr 16 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((value shr 8 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((value and 0xFF) * (1f / 1))
            }
        }

       // Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        val dataFloat:FloatArray = outputFeature0.floatArray

        var maxPos = 0
        var maxConfidence = 0f
        for (i in 0..dataFloat.size-1) {
            if (dataFloat[i] > maxConfidence){
                maxConfidence =dataFloat[i]
                maxPos =i
            }
            Toast.makeText(this,dataFloat[i].toString(), Toast.LENGTH_SHORT).show()
        }

        Toast.makeText(this,classes[maxPos], Toast.LENGTH_SHORT).show()
        Toast.makeText(this,dataFloat.size.toString(), Toast.LENGTH_SHORT).show()


        // Releases model resources if no longer used.
        model.close()

    }

    private fun convertToPersen(param:Float):Int{

        var data = ((param * 100.0).roundToInt() / 100.0).toFloat()
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
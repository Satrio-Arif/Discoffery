package com.project.discofferytemp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.discofferytemp.databinding.ActivityScanPhotoBinding
import com.project.discofferytemp.ml.Kopi
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.roundToInt

class ScanPhoto : AppCompatActivity() {
    private lateinit var bitmap:Bitmap
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
            bitmap = BitmapFactory.decodeFile(file.path)
            val resize = Bitmap.createScaledBitmap(bitmap,224,224,false)
            clasification(resize)
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
        val classes = arrayOf("Aceh gayo", "Dampit", "Toraja")
        Toast.makeText(this,classes[maxPos], Toast.LENGTH_SHORT).show()
        Toast.makeText(this,dataFloat.size.toString(), Toast.LENGTH_SHORT).show()

        binding.confidenceAcehGayo.text = getString(R.string.aceh_gayo,convertToPersen(dataFloat[0]).toString(),"%")
        binding.confidenceDampit.text = getString(R.string.dampit,convertToPersen(dataFloat[1]).toString(),"%")
        binding.confidenceToraja.text = getString(R.string.toraja,convertToPersen(dataFloat[2]).toString(),"%s")
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
}
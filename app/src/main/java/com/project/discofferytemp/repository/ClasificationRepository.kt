package com.project.discofferytemp.repository

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Color
import android.widget.Toast
import com.project.discofferytemp.ml.Kopi
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.lang.RuntimeException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ClasificationRepository  {

    fun getClasification(bitmap: Bitmap, application: Application): FloatArray {
        var dataArray = FloatArray(3)
        val arrayOfZeros = IntArray(224 * 244)
        val byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        try {
            val model = Kopi.newInstance(application)
            bitmap.getPixels(arrayOfZeros, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
            // var pixels=0
            for (i in 0..223) {
                for (j in 0..223) {
                    val px = bitmap.getPixel(i, j)
                    val r = (Color.red(px) * (1f / 1))
                    val g = (Color.green(px) * (1f / 1))
                    val b = (Color.blue(px) * (1f / 1))
                    byteBuffer.putFloat(r)
                    byteBuffer.putFloat(g)
                    byteBuffer.putFloat(b)
                }
            }
            bitmap.recycle()

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            dataArray = outputFeature0.floatArray

            model.close()

        } catch (e: RuntimeException) {
            throw RuntimeException(e)
        }

        return dataArray
    }

    companion object {
        @Volatile
        private var instance: ClasificationRepository? = null
        fun getInstance(): ClasificationRepository =
            instance ?: synchronized(this) {
                instance ?: ClasificationRepository()
            }.also { instance = it }
    }
}
package com.project.discofferytemp.clasification

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Color
import com.project.discofferytemp.ml.Kopi
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ImageClasification {
    private fun getArrayofRgb(bitmap: Bitmap): ByteBuffer {
        val arrayOfZeros = IntArray(224 * 244)
        val byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        bitmap.getPixels(arrayOfZeros, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

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
        return byteBuffer
    }

    fun clasification(bitmap: Bitmap, application: Application): FloatArray {
        val model = Kopi.newInstance(application)
        val byteBuffer = getArrayofRgb(bitmap)

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        model.close()
        return outputFeature0.floatArray
    }

}
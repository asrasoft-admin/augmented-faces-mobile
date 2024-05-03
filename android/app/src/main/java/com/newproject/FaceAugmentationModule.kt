// FaceAugmentationModule.kt
package com.newproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import org.opencv.android.OpenCVLoader
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import java.io.File
import java.io.FileOutputStream
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.core.Scalar 
import org.opencv.core.Point
import org.opencv.objdetect.CascadeClassifier
import org.opencv.core.MatOfRect
import org.opencv.imgproc.Imgproc


class FaceAugmentationModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    init {
        // Load OpenCV library
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
        }
    }

    override fun getName(): String {
        return "FaceAugmentationModule"
    }

    @ReactMethod
    fun augmentFaces(imagePath: String, promise: Promise) {
        val imageBitmap = BitmapFactory.decodeFile(imagePath)
        val grayBitmap = Mat()
        val faceCascadeFile = File(reactApplicationContext.cacheDir, "haarcascade_frontalface_default.xml")
        val faceCascade = CascadeClassifier(faceCascadeFile.absolutePath)
        val scaleFactor = 1.1
        val minNeighbors = 3
        val faces = MatOfRect()

        // Convert bitmap to grayscale
        Imgproc.cvtColor(imageBitmap, grayBitmap, Imgproc.COLOR_BGR2GRAY)

        // Detect faces
        faceCascade.detectMultiScale(grayBitmap, faces, scaleFactor, minNeighbors, 0, Size(20.0, 20.0))

        // Loop through detected faces and augment them
        for (rect in faces.toArray()) {
            // Your face augmentation logic here
            // For example, you can draw rectangles around detected faces
            Imgproc.rectangle(
                imageBitmap,
                Point(rect.x.toDouble(), rect.y.toDouble()),
                Point((rect.x + rect.width).toDouble(), (rect.y + rect.height).toDouble()),
                Scalar(255.0, 0.0, 0.0),
                2
            )
        }

        // Save the augmented image
        val outputImagePath = reactApplicationContext.cacheDir.absolutePath + "/augmented_image.jpg"
        val outputStream = FileOutputStream(outputImagePath)
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()

        // Resolve promise with augmented image path
        promise.resolve(outputImagePath)
    }
}

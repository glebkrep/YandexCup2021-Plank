package com.glebkrep.yandexcup.plank.poseDetection.ml

import android.graphics.Bitmap
import com.glebkrep.yandexcup.plank.poseDetection.data.Person

interface PoseDetector : AutoCloseable {

    fun estimateSinglePose(bitmap: Bitmap): Person

    fun lastInferenceTimeNanos(): Long
}

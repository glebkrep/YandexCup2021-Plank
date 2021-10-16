package com.glebkrep.yandexcup.plank.poseDetection.data

import android.graphics.PointF

data class KeyPoint(val bodyPart: BodyPart, var coordinate: PointF, val score: Float)

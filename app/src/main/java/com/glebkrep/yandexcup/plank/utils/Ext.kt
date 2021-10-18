package com.glebkrep.yandexcup.plank.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Patterns
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}


fun Long.millisToSeconds():String{
    val seconds = this.toDouble()/1000f
    val decimal = BigDecimal(seconds).setScale(2, RoundingMode.HALF_EVEN)
    return "$decimal s"
}
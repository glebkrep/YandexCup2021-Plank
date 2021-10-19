package com.glebkrep.yandexcup.plank.utils

import java.math.BigDecimal
import java.math.RoundingMode

fun Long.millisToSeconds(): String {
    val seconds = this.toDouble() / 1000f
    val decimal = BigDecimal(seconds).setScale(2, RoundingMode.HALF_EVEN)
    return "$decimal s"
}
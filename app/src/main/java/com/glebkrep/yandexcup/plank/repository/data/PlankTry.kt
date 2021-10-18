package com.glebkrep.yandexcup.plank.repository.data

data class PlankTry(
    val id:Int,
    val startTimestamp:Long,
    val endTimestamp:Long,
    val tryLength:Long = endTimestamp-startTimestamp
)
package com.glebkrep.yandexcup.plank.utils

import android.util.Log
import com.glebkrep.yandexcup.plank.BuildConfig

object Debug {
    fun log(any:Any?){
        if (BuildConfig.DEBUG){
            Log.e("Yoga.Debug:",any.toString())
        }
    }
}
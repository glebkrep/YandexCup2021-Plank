package com.glebkrep.yandexcup.plank.ui.pages.plankExercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.glebkrep.yandexcup.plank.repository.data.PlankTry

class PlankExerciseVM(application:Application):AndroidViewModel(application) {

    private var _tries: MutableLiveData<List<PlankTry>> = MutableLiveData(listOf())
    val tries:LiveData<List<PlankTry>> = _tries
}

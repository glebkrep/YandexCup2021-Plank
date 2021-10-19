package com.glebkrep.yandexcup.plank.ui.home.pages.plankExercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.glebkrep.yandexcup.plank.data.PlankTry
import com.glebkrep.yandexcup.plank.data.PlankTryRepository
import com.glebkrep.yandexcup.plank.data.PlankTryRoomDatabase

class PlankExerciseVM(application: Application) : AndroidViewModel(application) {
    val tries: LiveData<List<PlankTry>>


    private var plankTryRepository: PlankTryRepository? = null

    init {
        plankTryRepository = PlankTryRepository(
            PlankTryRoomDatabase.getDatabase(getApplication()).plankTryDao()
        )
        tries = plankTryRepository!!.getAllTries()
    }


}
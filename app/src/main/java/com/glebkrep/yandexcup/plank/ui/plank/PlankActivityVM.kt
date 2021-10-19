package com.glebkrep.yandexcup.plank.ui.plank

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.glebkrep.yandexcup.plank.data.PlankTry
import com.glebkrep.yandexcup.plank.data.PlankTryRepository
import com.glebkrep.yandexcup.plank.data.PlankTryRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlankActivityVM(application: Application) : AndroidViewModel(application) {
    private var plankTryRepository: PlankTryRepository? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            plankTryRepository = PlankTryRepository(
                PlankTryRoomDatabase.getDatabase(getApplication()).plankTryDao()
            )
        }
    }

    private var plankStartTimestamp: Long? = null

    fun plankStart(timestamp: Long) {
        plankStartTimestamp = timestamp
    }

    fun plankEnd(timestamp: Long) {
        val plankStart = plankStartTimestamp ?: return
        val plankTry = PlankTry(startTimestamp = plankStart, endTimestamp = timestamp)
        viewModelScope.launch(Dispatchers.IO) {
            plankTryRepository?.insert(plankTry)
        }
        plankStartTimestamp = null
    }
}
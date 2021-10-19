package com.glebkrep.yandexcup.plank.data


class PlankTryRepository(private val plankTryDao: PlankTryDao) {
    fun getAllTries() = plankTryDao.getAllItems()

    suspend fun insert(plankTry: PlankTry) {
        plankTryDao.insert(plankTry)
    }
}
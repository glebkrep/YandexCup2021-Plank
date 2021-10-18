package com.glebkrep.yandexcup.plank.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlankTryDao {
    @Query("select * from planktry_table order by start_time asc")
    fun getAllItems(): LiveData<List<PlankTry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(breathingItem: PlankTry): Long
}

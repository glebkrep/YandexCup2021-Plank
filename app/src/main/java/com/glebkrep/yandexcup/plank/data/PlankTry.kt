package com.glebkrep.yandexcup.plank.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planktry_table")
data class PlankTry(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name = "start_time")
    val startTimestamp:Long,
    @ColumnInfo(name = "end_time")
    val endTimestamp:Long,
    @ColumnInfo(name = "try_time")
    val tryTime:Long = endTimestamp-startTimestamp
)
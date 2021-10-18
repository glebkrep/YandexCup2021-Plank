package com.glebkrep.yandexcup.plank.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlankTry::class], version = 1)
abstract class PlankTryRoomDatabase : RoomDatabase() {

    abstract fun plankTryDao(): PlankTryDao


    companion object {
        @Volatile
        private var INSTANCE: PlankTryRoomDatabase? = null

        fun getDatabase(context: Context): PlankTryRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlankTryRoomDatabase::class.java, "local_db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    return instance
                }

            }
        }
    }
}
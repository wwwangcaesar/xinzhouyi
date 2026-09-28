package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.DivinationRecord

@Database(entities = [DivinationRecord::class], version = 1, exportSchema = false)
abstract class TianYanDatabase : RoomDatabase() {
    abstract fun divinationDao(): DivinationDao

    companion object {
        @Volatile
        private var INSTANCE: TianYanDatabase? = null

        fun getInstance(context: Context): TianYanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TianYanDatabase::class.java,
                    "tianyan_divination.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

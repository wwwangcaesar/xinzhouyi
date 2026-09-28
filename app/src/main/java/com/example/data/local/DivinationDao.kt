package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.DivinationRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface DivinationDao {
    @Query("SELECT * FROM divination_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<DivinationRecord>>

    @Query("SELECT * FROM divination_records WHERE type = :type ORDER BY timestamp DESC")
    fun getRecordsByType(type: String): Flow<List<DivinationRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: DivinationRecord): Long

    @Query("DELETE FROM divination_records WHERE id = :id")
    suspend fun deleteRecordById(id: Long)

    @Query("DELETE FROM divination_records")
    suspend fun clearAll()
}

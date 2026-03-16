package com.guzzoline.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TripLogDao {
    @Query("SELECT * FROM trip_log ORDER BY timestamp DESC")
    fun getAll(): Flow<List<TripLogEntity>>

    @Query("SELECT * FROM trip_log ORDER BY timestamp DESC")
    suspend fun getAllOnce(): List<TripLogEntity>

    @Insert
    suspend fun insert(entry: TripLogEntity)

    @Query("DELETE FROM trip_log WHERE id = :id")
    suspend fun deleteById(id: Int)
}

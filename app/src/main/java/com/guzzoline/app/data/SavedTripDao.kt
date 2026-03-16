package com.guzzoline.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedTripDao {
    @Query("SELECT * FROM saved_trips ORDER BY name ASC")
    fun getAll(): Flow<List<SavedTripEntity>>

    @Insert
    suspend fun insert(trip: SavedTripEntity)

    @Query("DELETE FROM saved_trips WHERE id = :id")
    suspend fun deleteById(id: Int)
}

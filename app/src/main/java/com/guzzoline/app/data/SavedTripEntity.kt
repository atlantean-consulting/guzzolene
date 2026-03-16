package com.guzzoline.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_trips")
data class SavedTripEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val origin: String,
    val destination: String,
    val distance: Double
)

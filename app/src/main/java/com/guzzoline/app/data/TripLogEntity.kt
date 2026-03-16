package com.guzzoline.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_log")
data class TripLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tripName: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val mpg: Double,
    val gasPrice: Double,
    val totalCost: Double,
    val timestamp: Long = System.currentTimeMillis()
)

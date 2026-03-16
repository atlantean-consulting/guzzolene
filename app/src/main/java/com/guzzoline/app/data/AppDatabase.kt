package com.guzzoline.app.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TripLogEntity::class, SavedTripEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripLogDao(): TripLogDao
    abstract fun savedTripDao(): SavedTripDao
}

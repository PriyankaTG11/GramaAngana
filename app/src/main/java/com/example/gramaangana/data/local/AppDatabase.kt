package com.example.gramaangana.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gramaangana.model.MaintenanceItem

@Database(
    entities = [MaintenanceItem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun maintenanceDao(): MaintenanceDao
}
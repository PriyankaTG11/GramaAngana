package com.example.gramaangana.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maintenance")
data class MaintenanceItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val itemName: String,

    val requiredAmount: Int,

    val collectedAmount: Int
)
package com.example.gramaangana.data.local

import androidx.room.*
import com.example.gramaangana.model.MaintenanceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MaintenanceDao {

    @Insert
    suspend fun insertItem(item: MaintenanceItem)

    @Query("SELECT * FROM maintenance")
    fun getAllItems(): Flow<List<MaintenanceItem>>

    @Update
    suspend fun updateItem(item: MaintenanceItem)
}
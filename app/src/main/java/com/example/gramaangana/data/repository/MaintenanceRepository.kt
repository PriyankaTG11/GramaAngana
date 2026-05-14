package com.example.gramaangana.data.repository

import com.example.gramaangana.data.local.MaintenanceDao
import com.example.gramaangana.model.MaintenanceItem
import kotlinx.coroutines.flow.Flow

class MaintenanceRepository(
    private val dao: MaintenanceDao
) {

    fun getItems(): Flow<List<MaintenanceItem>> {
        return dao.getAllItems()
    }

    suspend fun insertItem(
        item: MaintenanceItem
    ) {
        dao.insertItem(item)
    }

    suspend fun updateItem(
        item: MaintenanceItem
    ) {
        dao.updateItem(item)
    }
}
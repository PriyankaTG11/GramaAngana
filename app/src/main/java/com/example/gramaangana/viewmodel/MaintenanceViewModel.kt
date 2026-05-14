package com.example.gramaangana.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gramaangana.data.repository.MaintenanceRepository
import com.example.gramaangana.model.MaintenanceItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MaintenanceViewModel(
    private val repository: MaintenanceRepository
) : ViewModel() {

    private val _items =
        MutableStateFlow<List<MaintenanceItem>>(emptyList())

    val items: StateFlow<List<MaintenanceItem>> = _items

    fun loadItems() {

        viewModelScope.launch {

            repository.getItems().collect {

                _items.value = it
            }
        }
    }

    fun addSampleItems() {

        viewModelScope.launch {

            repository.insertItem(
                MaintenanceItem(
                    itemName = "Fan Repair",
                    requiredAmount = 500,
                    collectedAmount = 200
                )
            )

            repository.insertItem(
                MaintenanceItem(
                    itemName = "Chair Purchase",
                    requiredAmount = 1000,
                    collectedAmount = 400
                )
            )
        }
    }

    fun pledgeSupport(item: MaintenanceItem) {

        viewModelScope.launch {

            val updatedItem =
                item.copy(
                    collectedAmount =
                        item.collectedAmount + 100
                )

            repository.updateItem(updatedItem)
        }
    }
}
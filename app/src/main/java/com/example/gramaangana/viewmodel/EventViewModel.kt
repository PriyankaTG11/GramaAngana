package com.example.gramaangana.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gramaangana.data.repository.EventRepository
import com.example.gramaangana.model.Event

class EventViewModel : ViewModel() {

    private val repository = EventRepository()

    var eventList =
        mutableStateOf<List<Event>>(emptyList())

    fun loadEvents() {

        repository.getEvents {

            eventList.value = it
        }
    }

    fun insertSampleEvents() {

        repository.addSampleEvents()
    }
}
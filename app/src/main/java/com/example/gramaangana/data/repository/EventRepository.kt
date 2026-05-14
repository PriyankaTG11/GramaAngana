package com.example.gramaangana.data.repository

import com.example.gramaangana.model.Event
import com.google.firebase.firestore.FirebaseFirestore

class EventRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getEvents(
        onResult: (List<Event>) -> Unit
    ) {

        db.collection("events")
            .get()

            .addOnSuccessListener { result ->

                val eventList =
                    mutableListOf<Event>()

                for (document in result) {

                    val event =
                        document.toObject(Event::class.java)

                    eventList.add(event)
                }

                onResult(eventList)
            }
    }

    fun addSampleEvents() {

        val sampleEvents = listOf(

            Event(
                title = "Health Camp",
                date = "20/06/2026",
                description =
                    "Free health checkup for villagers"
            ),

            Event(
                title = "Sports Practice",
                date = "22/06/2026",
                description =
                    "Kabaddi practice for youth"
            ),

            Event(
                title = "Village Meeting",
                date = "25/06/2026",
                description =
                    "Discussion about road development"
            )
        )

        for (event in sampleEvents) {

            db.collection("events")
                .add(event)
        }
    }
}
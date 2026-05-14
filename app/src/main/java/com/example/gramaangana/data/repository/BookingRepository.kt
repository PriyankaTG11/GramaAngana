package com.example.gramaangana.data.repository

import com.example.gramaangana.model.Booking
import com.google.firebase.firestore.FirebaseFirestore

class BookingRepository {

    private val firestore =
        FirebaseFirestore.getInstance()

    fun addBooking(

        booking: Booking,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit
    ) {

        val document =

            firestore.collection("bookings")
                .document()

        val bookingWithId =

            booking.copy(
                id = document.id
            )

        document.set(bookingWithId)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Unknown Error"
                )
            }
    }

    fun getBookings(
        onResult: (List<Booking>) -> Unit
    ) {

        firestore.collection("bookings")

            .get()

            .addOnSuccessListener { result ->

                val bookings =

                    result.documents.mapNotNull {

                        it.toObject(
                            Booking::class.java
                        )
                    }

                onResult(bookings)
            }
    }

    fun updateBookingStatus(

        bookingId: String,

        status: String,

        onSuccess: () -> Unit = {},

        onFailure: (String) -> Unit = {}
    ) {

        firestore.collection("bookings")

            .document(bookingId)

            .update("status", status)

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Update Failed"
                )
            }
    }

    fun deleteBooking(

        bookingId: String,

        onSuccess: () -> Unit = {},

        onFailure: (String) -> Unit = {}
    ) {

        firestore.collection("bookings")

            .document(bookingId)

            .delete()

            .addOnSuccessListener {

                onSuccess()
            }

            .addOnFailureListener {

                onFailure(
                    it.message ?: "Delete Failed"
                )
            }
    }
}
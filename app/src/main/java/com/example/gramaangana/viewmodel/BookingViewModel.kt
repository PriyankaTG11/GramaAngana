package com.example.gramaangana.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gramaangana.data.repository.BookingRepository
import com.example.gramaangana.model.Booking

class BookingViewModel : ViewModel() {

    var bookingList = mutableStateOf<List<Booking>>(emptyList())
    private val repository = BookingRepository()

    var message = mutableStateOf("")

    fun createBooking(booking: Booking) {

        repository.addBooking(

            booking = booking,

            onSuccess = {
                message.value = "Booking Request Submitted"
            },

            onFailure = {
                message.value = it
            }
        )
    }
    fun loadBookings() {

        repository.getBookings {

            bookingList.value = it
        }
    }
    fun updateStatus(

        bookingId: String,

        status: String
    ) {

        repository.updateBookingStatus(

            bookingId,

            status,

            onSuccess = {

                message.value =
                    "Booking Updated"

                loadBookings()
            },

            onFailure = {

                message.value = it
            }
        )
    }

}
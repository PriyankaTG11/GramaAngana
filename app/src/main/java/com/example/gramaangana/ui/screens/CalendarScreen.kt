package com.example.gramaangana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramaangana.model.Booking
import com.example.gramaangana.ui.components.GenerateQRCode
import com.example.gramaangana.viewmodel.BookingViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel = viewModel()
) {

    LaunchedEffect(true) {
        bookingViewModel.loadBookings()
    }

    val bookings = bookingViewModel.bookingList.value

    var currentMonth by remember {
        mutableStateOf(YearMonth.now())
    }

    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val daysInMonth = currentMonth.lengthOfMonth()

    val selectedBookings =
        bookings.filter {
            it.date == selectedDate.toString()
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F8E9))
            .padding(16.dp)
    ) {

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32)
            ),

            shape = RoundedCornerShape(16.dp),

            onClick = {
                navController.popBackStack()
            }
        ) {

            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Community Hall Calendar",

            style =
                MaterialTheme.typography.headlineMedium,

            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32)
                ),

                shape = RoundedCornerShape(16.dp),

                onClick = {
                    currentMonth =
                        currentMonth.minusMonths(1)
                }
            ) {

                Text("<")
            }

            Text(
                text =
                    "${currentMonth.month.getDisplayName(
                        TextStyle.FULL,
                        Locale.getDefault()
                    )} ${currentMonth.year}",

                style =
                    MaterialTheme.typography.titleLarge
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D32)
                ),

                shape = RoundedCornerShape(16.dp),

                onClick = {
                    currentMonth =
                        currentMonth.plusMonths(1)
                }
            ) {

                Text(">")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceAround
        ) {

            listOf(
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri",
                "Sat"
            ).forEach {

                Text(text = it)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column {

            var day = 1

            repeat(5) {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceAround
                ) {

                    repeat(7) {

                        if (day <= daysInMonth) {

                            val currentDate =
                                currentMonth.atDay(day)

                            val hasBooking =
                                bookings.any {

                                    it.date ==
                                            currentDate.toString()

                                            &&

                                            it.status == "Approved"
                                }

                            DayItem(
                                day = day,

                                isSelected =
                                    selectedDate == currentDate,

                                hasBooking = hasBooking,

                                onClick = {
                                    selectedDate = currentDate
                                }
                            )

                            day++
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text =
                "Bookings for $selectedDate",

            style =
                MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {

            items(selectedBookings) { booking ->

                BookingCard(booking)
            }
        }
    }
}

@Composable
fun DayItem(
    day: Int,
    isSelected: Boolean,
    hasBooking: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .size(48.dp)

            .clip(CircleShape)

            .background(

                when {

                    hasBooking ->
                        Color(0xFFEF5350)

                    isSelected ->
                        Color(0xFF66BB6A)

                    else ->
                        Color(0xFFE0E0E0)
                }
            )

            .clickable {
                onClick()
            },

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = day.toString()
        )
    }
}

@Composable
fun BookingCard(
    booking: Booking
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        shape = RoundedCornerShape(20.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text =
                    "Name: ${booking.userName}"
            )

            Text(
                text =
                    "Time: ${booking.timeSlot}"
            )

            Text(
                text =
                    "Purpose: ${booking.purpose}"
            )

            Text(
                text =
                    "Status: ${booking.status}",

                color = when (booking.status) {

                    "Approved" ->
                        Color(0xFF2E7D32)

                    "Rejected" ->
                        Color.Red

                    else ->
                        Color(0xFFFF9800)
                }
            )

            if (booking.status == "Approved") {

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                Text(
                    text =
                        "Booking Verification QR",

                    color =
                        Color(0xFF2E7D32)
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                GenerateQRCode(

                    text =
                        "User: ${booking.userName}\nDate: ${booking.date}",

                    modifier =
                        Modifier.size(180.dp)
                )
            }
        }
    }
}
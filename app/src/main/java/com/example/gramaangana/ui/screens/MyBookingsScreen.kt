package com.example.gramaangana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramaangana.model.Booking
import com.example.gramaangana.ui.components.GenerateQRCode
import com.example.gramaangana.viewmodel.BookingViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MyBookingsScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel = viewModel()
) {

    val currentUser =
        FirebaseAuth.getInstance().currentUser

    LaunchedEffect(true) {

        bookingViewModel.loadBookings()
    }

    val myBookings =
        bookingViewModel.bookingList.value.filter {

            it.userName ==
                    currentUser?.email
        }

    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(

                Brush.verticalGradient(
                    listOf(
                        Color(0xFFE8F5E9),
                        Color.White
                    )
                )
            )

            .padding(20.dp)
    ) {

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {

            Text("Back")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "📖 My Bookings",

            fontSize = 30.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text =
                "Track your hall booking requests.",

            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (myBookings.isEmpty()) {

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(24.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "📭",

                        fontSize = 48.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Text(
                        text =
                            "No Bookings Yet",

                        fontWeight =
                            FontWeight.Bold,

                        fontSize = 22.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )

                    Text(
                        text =
                            "Your booking requests will appear here.",

                        color = Color.Gray
                    )
                }
            }

        } else {

            LazyColumn {

                items(myBookings) { booking ->

                    MyBookingCard(booking)
                }
            }
        }
    }
}

@Composable
fun MyBookingCard(
    booking: Booking
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),

        shape =
            RoundedCornerShape(24.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),

        colors =
            CardDefaults.cardColors(
                containerColor = Color.White
            )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text =
                    "📅 Date: ${booking.date}"
            )

            Text(
                text =
                    "⏰ Time: ${booking.timeSlot}"
            )

            Text(
                text =
                    "📝 Purpose: ${booking.purpose}"
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
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
                },

                fontWeight =
                    FontWeight.Bold
            )

            if (booking.status == "Approved") {

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                Text(
                    text =
                        "Booking QR",

                    color =
                        Color(0xFF2E7D32),

                    fontWeight =
                        FontWeight.SemiBold
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                GenerateQRCode(

                    text =
                        "Booking Date: ${booking.date}",

                    modifier =
                        Modifier.size(180.dp)
                )
            }
        }
    }
}
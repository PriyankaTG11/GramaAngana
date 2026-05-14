package com.example.gramaangana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.gramaangana.viewmodel.BookingViewModel

@Composable
fun AdminScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel = viewModel()
) {

    LaunchedEffect(true) {
        bookingViewModel.loadBookings()
    }

    val bookings =
        bookingViewModel.bookingList.value

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
            },

            shape =
                RoundedCornerShape(14.dp)
        ) {

            Text("Back")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "👨‍💼 Admin Panel",

            fontSize = 30.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text =
                "Manage booking requests and approvals.",

            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (bookings.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),

                contentAlignment =
                    Alignment.Center
            ) {

                CircularProgressIndicator(
                    color = Color(0xFF2E7D32)
                )
            }

        } else {

            LazyColumn {

                items(bookings) { booking ->

                    AdminBookingCard(

                        booking = booking,

                        onApprove = {

                            if (booking.id.isNotEmpty()) {

                                bookingViewModel.updateStatus(

                                    booking.id,

                                    "Approved"
                                )
                            }
                        },

                        onReject = {

                            if (booking.id.isNotEmpty()) {

                                bookingViewModel.updateStatus(

                                    booking.id,

                                    "Rejected"
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AdminBookingCard(

    booking: Booking,

    onApprove: () -> Unit,

    onReject: () -> Unit
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
                    booking.userName,

                fontSize = 22.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Color(0xFF1B5E20)
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text =
                    "📅 Date: ${booking.date}"
            )

            Text(
                text =
                    "⏰ Time Slot: ${booking.timeSlot}"
            )

            Text(
                text =
                    "📝 Purpose: ${booking.purpose}"
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            StatusChip(
                status = booking.status
            )

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Button(

                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                Color(0xFF2E7D32)
                        ),

                    shape =
                        RoundedCornerShape(16.dp),

                    onClick = {
                        onApprove()
                    }
                ) {

                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )

                    Text("Approve")
                }

                Button(

                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                Color.Red
                        ),

                    shape =
                        RoundedCornerShape(16.dp),

                    onClick = {
                        onReject()
                    }
                ) {

                    Icon(
                        Icons.Default.Close,
                        contentDescription = null
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )

                    Text("Reject")
                }
            }
        }
    }
}

@Composable
fun StatusChip(
    status: String
) {

    val color =
        when (status) {

            "Approved" ->
                Color(0xFF2E7D32)

            "Rejected" ->
                Color.Red

            else ->
                Color(0xFFFF9800)
        }

    Surface(

        color = color.copy(alpha = 0.15f),

        shape =
            RoundedCornerShape(50)
    ) {

        Row(
            modifier = Modifier.padding(
                horizontal = 14.dp,
                vertical = 8.dp
            ),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Icon(

                imageVector = when (status) {

                    "Approved" ->
                        Icons.Default.CheckCircle

                    "Rejected" ->
                        Icons.Default.Close

                    else ->
                        Icons.Default.Pending
                },

                contentDescription = null,

                tint = color
            )

            Spacer(
                modifier =
                    Modifier.width(6.dp)
            )

            Text(
                text = status,

                color = color,

                fontWeight =
                    FontWeight.SemiBold
            )
        }
    }
}
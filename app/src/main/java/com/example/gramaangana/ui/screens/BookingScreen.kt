package com.example.gramaangana.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramaangana.model.Booking
import com.example.gramaangana.viewmodel.BookingViewModel
import java.time.LocalDate
import java.util.Calendar

@Composable
fun BookingScreen(
    navController: NavController,
    bookingViewModel: BookingViewModel = viewModel()
) {

    var userName by remember {
        mutableStateOf("")
    }

    var purpose by remember {
        mutableStateOf("")
    }

    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    var selectedSlot by remember {
        mutableStateOf("Morning")
    }

    val slots = listOf(
        "Morning",
        "Afternoon",
        "Evening"
    )

    val message =
        bookingViewModel.message.value

    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(

        context,

        { _: DatePicker, year: Int, month: Int, day: Int ->

            selectedDate =
                LocalDate.of(
                    year,
                    month + 1,
                    day
                )
        },

        calendar.get(Calendar.YEAR),

        calendar.get(Calendar.MONTH),

        calendar.get(Calendar.DAY_OF_MONTH)
    )

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

            .verticalScroll(
                rememberScrollState()
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
            text = "Book Community Hall",

            fontSize = 30.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text =
                "Reserve the hall for meetings and activities.",

            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(

            shape =
                RoundedCornerShape(28.dp),

            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),

            colors =
                CardDefaults.cardColors(
                    containerColor = Color.White
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp)
            ) {

                OutlinedTextField(

                    value = userName,

                    onValueChange = {
                        userName = it
                    },

                    label = {
                        Text("Your Name")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Person,
                            contentDescription = null
                        )
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(16.dp)
                )

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                OutlinedTextField(

                    value = purpose,

                    onValueChange = {
                        purpose = it
                    },

                    label = {
                        Text("Purpose")
                    },

                    leadingIcon = {

                        Icon(
                            Icons.Default.Description,
                            contentDescription = null
                        )
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(16.dp)
                )

                Spacer(
                    modifier =
                        Modifier.height(22.dp)
                )

                Text(
                    text = "Booking Date",

                    fontWeight =
                        FontWeight.SemiBold
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Card(

                    modifier = Modifier
                        .fillMaxWidth(),

                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                Color(0xFFF1F8E9)
                        ),

                    shape =
                        RoundedCornerShape(18.dp),

                    onClick = {

                        datePickerDialog.show()
                    }
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = null,

                            tint =
                                Color(0xFF2E7D32)
                        )

                        Spacer(
                            modifier =
                                Modifier.width(12.dp)
                        )

                        Text(
                            text =
                                selectedDate.toString(),

                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                Text(
                    text = "Select Time Slot",

                    fontWeight =
                        FontWeight.SemiBold
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                slots.forEach { slot ->

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        RadioButton(

                            selected =
                                selectedSlot == slot,

                            onClick = {
                                selectedSlot = slot
                            }
                        )

                        Text(text = slot)
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(28.dp)
                )

                Button(

                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                Color(0xFF2E7D32)
                        ),

                    shape =
                        RoundedCornerShape(18.dp),

                    modifier =
                        Modifier.fillMaxWidth(),

                    onClick = {

                        val booking = Booking(

                            userName = userName,

                            purpose = purpose,

                            date =
                                selectedDate.toString(),

                            timeSlot =
                                selectedSlot,

                            status = "Pending"
                        )

                        bookingViewModel.createBooking(
                            booking
                        )
                    }
                ) {

                    Text(
                        text =
                            "Submit Booking Request",

                        fontSize = 18.sp
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                if (message.isNotEmpty()) {

                    Card(

                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    Color(0xFFE8F5E9)
                            ),

                        shape =
                            RoundedCornerShape(16.dp)
                    ) {

                        Text(

                            text = message,

                            modifier =
                                Modifier.padding(16.dp),

                            color =
                                Color(0xFF2E7D32)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
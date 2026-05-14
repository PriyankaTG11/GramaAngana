package com.example.gramaangana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MaintenanceScreen(
    navController: NavController
) {

    var contribution by remember {
        mutableStateOf("")
    }

    var totalFund by remember {
        mutableStateOf(18500)
    }

    val targetFund = 50000

    val progress =
        totalFund.toFloat() / targetFund.toFloat()

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
            text = "Maintenance Fund",

            fontSize = 30.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text =
                "Support community hall maintenance and improvements.",

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
                    .padding(24.dp)
            ) {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Build,
                        contentDescription = null,

                        tint = Color(0xFF2E7D32)
                    )

                    Spacer(
                        modifier =
                            Modifier.width(10.dp)
                    )

                    Text(
                        text =
                            "Current Maintenance Goal",

                        fontWeight =
                            FontWeight.Bold,

                        fontSize = 18.sp
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                Text(
                    text =
                        "₹$totalFund Raised",

                    fontSize = 32.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Color(0xFF2E7D32)
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(
                    text =
                        "Target: ₹$targetFund",

                    color = Color.Gray
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                LinearProgressIndicator(

                    progress = { progress },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp),

                    color = Color(0xFF2E7D32),

                    trackColor =
                        Color(0xFFC8E6C9)
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(
                    text =
                        "${(progress * 100).toInt()}% Goal Completed",

                    color = Color.Gray
                )
            }
        }

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
                    .padding(24.dp)
            ) {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.VolunteerActivism,
                        contentDescription = null,

                        tint = Color(0xFF2E7D32)
                    )

                    Spacer(
                        modifier =
                            Modifier.width(10.dp)
                    )

                    Text(
                        text =
                            "Contribute to Fund",

                        fontWeight =
                            FontWeight.Bold,

                        fontSize = 18.sp
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(22.dp)
                )

                OutlinedTextField(

                    value = contribution,

                    onValueChange = {
                        contribution = it
                    },

                    label = {
                        Text("Enter Amount")
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(18.dp)
                )

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
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

                        val amount =
                            contribution.toIntOrNull()

                        if (amount != null) {

                            totalFund += amount

                            contribution = ""
                        }
                    }
                ) {

                    Text(
                        text =
                            "Contribute Now",

                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
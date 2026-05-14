package com.example.gramaangana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gramaangana.navigation.Routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(
    navController: NavController
) {

    val auth = FirebaseAuth.getInstance()

    val currentUser =
        FirebaseAuth.getInstance().currentUser

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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome 👋",

            fontSize = 18.sp,

            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text =
                currentUser?.email
                    ?: "Community User",

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text =
                "Manage your community hall activities easily.",

            color = Color.Gray,

            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        DashboardCard(
            title = "Request Booking",

            subtitle =
                "Book community hall slots",

            icon = Icons.Default.Home,

            iconBackground =
                Color(0xFFE3F2FD),

            onClick = {

                navController.navigate(
                    Routes.Booking.route
                )
            }
        )

        DashboardCard(
            title = "View Calendar",

            subtitle =
                "Check hall availability",

            icon = Icons.Default.CalendarMonth,

            iconBackground =
                Color(0xFFE8F5E9),

            onClick = {

                navController.navigate(
                    Routes.Calendar.route
                )
            }
        )

        DashboardCard(
            title = "My Bookings",

            subtitle =
                "Track your booking requests",

            icon = Icons.Default.ListAlt,

            iconBackground =
                Color(0xFFFFF3E0),

            onClick = {

                navController.navigate(
                    Routes.MyBookings.route
                )
            }
        )

        DashboardCard(
            title = "Maintenance Jar",

            subtitle =
                "Support hall maintenance",

            icon = Icons.Default.Build,

            iconBackground =
                Color(0xFFF3E5F5),

            onClick = {

                navController.navigate(
                    Routes.Maintenance.route
                )
            }
        )

        DashboardCard(
            title = "Event Board",

            subtitle =
                "See community events",

            icon = Icons.Default.Event,

            iconBackground =
                Color(0xFFFFEBEE),

            onClick = {

                navController.navigate(
                    Routes.Events.route
                )
            }
        )

        if (
            currentUser?.email ==
            "admin@gmail.com"
        ) {

            DashboardCard(
                title = "Admin Panel",

                subtitle =
                    "Approve bookings",

                icon = Icons.Default.AdminPanelSettings,

                iconBackground =
                    Color(0xFFE0F7FA),

                onClick = {

                    navController.navigate(
                        Routes.Admin.route
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

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

                auth.signOut()

                navController.navigate(
                    Routes.Login.route
                ) {

                    popUpTo(
                        Routes.Home.route
                    ) {

                        inclusive = true
                    }
                }
            }

        )

        {
            Icon(
                Icons.Default.Logout,
                contentDescription = null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text = "Logout",

                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun DashboardCard(

    title: String,

    subtitle: String,

    icon: androidx.compose.ui.graphics.vector.ImageVector,

    iconBackground: Color,

    onClick: () -> Unit
) {

    Card(

        onClick = onClick,

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(60.dp)

                    .background(
                        iconBackground,
                        CircleShape
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    icon,
                    contentDescription = null,

                    tint = Color(0xFF1B5E20)
                )
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column {

                Text(
                    text = title,

                    fontSize = 20.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Color(0xFF1B5E20)
                )

                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )

                Text(
                    text = subtitle,

                    color = Color.Gray,

                    fontSize = 14.sp
                )
            }
        }
    }
}
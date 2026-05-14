package com.example.gramaangana.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gramaangana.ui.screens.*
import com.example.gramaangana.ui.screens.AdminScreen
import com.example.gramaangana.ui.screens.SplashScreen
import com.example.gramaangana.ui.screens.LoginScreen
import com.example.gramaangana.ui.screens.MyBookingsScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }
        composable(Routes.Login.route) {
            LoginScreen(navController)
        }
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }

        composable(Routes.Booking.route) {
            BookingScreen(navController)
        }

        composable(Routes.Calendar.route) {
            CalendarScreen(navController)
        }

        composable(Routes.Maintenance.route) {
            MaintenanceScreen(navController)
        }

        composable(Routes.Events.route) {
            EventBoardScreen(navController)
        }
        composable(Routes.Admin.route) {
            AdminScreen(navController)
        }
        composable(Routes.MyBookings.route) {

            MyBookingsScreen(navController)
        }
    }
}
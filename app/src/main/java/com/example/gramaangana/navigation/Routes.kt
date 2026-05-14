package com.example.gramaangana.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Home : Routes("home")

    object Booking : Routes("booking")

    object Calendar : Routes("calendar")

    object Maintenance : Routes("maintenance")

    object Events : Routes("events")

    object Admin : Routes("admin")
    object Login : Routes("login")
    object MyBookings : Routes("my_bookings")
}
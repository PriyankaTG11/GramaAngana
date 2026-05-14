package com.example.gramaangana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gramaangana.model.Event
import com.example.gramaangana.viewmodel.EventViewModel

@Composable
fun EventBoardScreen(
    navController: NavController,
    eventViewModel: EventViewModel = viewModel()
) {

    LaunchedEffect(Unit) {

        eventViewModel.insertSampleEvents()

        eventViewModel.loadEvents()
    }

    val events = eventViewModel.eventList.value

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
            text = "Community Event Board",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(events) { event ->

                EventCard(event)
            }
        }
    }
}

@Composable
fun EventCard(
    event: Event
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = event.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Date: ${event.date}"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = event.description
            )
        }
    }
}
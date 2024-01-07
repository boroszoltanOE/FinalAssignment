package com.zoltanboros.finalassignment.ui.PlannedLakes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zoltanboros.finalassignment.model.Lake

@Composable
fun PlannedScreen(
    plannedViewModel: PlannedViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    if (plannedViewModel.state.plannedLakes.isNotEmpty()) {
        LazyColumn {
            items(plannedViewModel.state.plannedLakes) { plannedLake ->
                PlannedLakeItem(plannedLake = plannedLake)
            }
        }
    } else {
        // Display a message when there are no planned lakes
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No planned lakes yet", color = Color.Gray)
        }
    }
}

@Composable
fun PlannedLakeItem(plannedLake: Lake) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = plannedLake.title, fontWeight = FontWeight.Bold)
            Text(text = "Type: ${plannedLake.type}")
            Text(text = "Rating: ${plannedLake.rating}")

            // Add any other information related to planned lakes

            Spacer(modifier = Modifier.height(8.dp))

            // You can include buttons or actions related to planned lakes here
        }
    }
}
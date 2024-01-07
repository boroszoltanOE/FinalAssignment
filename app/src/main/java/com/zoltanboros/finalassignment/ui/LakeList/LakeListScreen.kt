package com.zoltanboros.finalassignment.ui.LakeList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zoltanboros.finalassignment.R
import com.zoltanboros.finalassignment.model.Lake

@Composable
fun LakeListScreen(
    lakeListViewModel: LakeListViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val lakes = lakeListViewModel.state.lakes

    LazyColumn {
        items(lakes) { lake ->
            LakeListItem(
                lake = lake,
                onAddClick = {
                    lakeListViewModel.updateLake(lake)
                }
            )
        }
    }
}
@Composable
fun LakeListItem(
    lake: Lake,
    onAddClick: (Lake) -> Unit,
) {
    var snackbarVisible by remember { mutableStateOf(false) }


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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = lake.title, fontWeight = FontWeight.Bold)
                    Text(text = "Type: ${lake.type}")
                    Text(text = "Rating: ${lake.rating}")
                }
                // Add button here
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // More Info button
                    IconButton(
                        onClick = {
                            // Show the Snackbar with the lake description
                            snackbarVisible = true
                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Add button
                    IconButton(
                        onClick = {
                            onAddClick(lake)

                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }

    // Show the Snackbar when snackbarVisible is true
    if (snackbarVisible) {
        Snackbar(
            action = {
                // Optionally, you can add an action to close the Snackbar
                TextButton(onClick = { snackbarVisible = false }) {
                    Text("Close")
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            // Show the lake description in the Snackbar
            Text(text = lake.desc)
        }
    }
}


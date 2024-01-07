package com.zoltanboros.finalassignment.ui.CountryList

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zoltanboros.finalassignment.R
import com.zoltanboros.finalassignment.model.Country

@Composable
fun CountryListScreen(
    countryListViewModel: CountryListViewModel = viewModel(),
    selectedSuccess: () -> Unit,
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
){
    val countries = countryListViewModel.state.countries
    LazyColumn {
        items(countries) { country ->
            CountryListItem(
                country = country,
                onItemClick = {
                    countryListViewModel.selectedItem(country)
                    selectedSuccess()
                }
            )
        }
    }
}

@Composable
fun CountryListItem(country: Country, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = getFlagResourceId(country.name)),
                contentDescription = "Country Flag",
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Display country information
            Column {
                Text(text = country.name, fontWeight = Bold)
            }
        }
    }
}

@DrawableRes
fun getFlagResourceId(countryName: String): Int {
    return when (countryName) {
        "Hungary" -> R.drawable.hungary
        "Germany" -> R.drawable.germany
        "Netherlands" -> R.drawable.netherlands
        "France" -> R.drawable.france
        else -> R.drawable.hungary
    }
}
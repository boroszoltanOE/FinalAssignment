package com.zoltanboros.finalassignment.ui.CountryList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanboros.finalassignment.model.Country
import com.zoltanboros.finalassignment.repository.Repository
import com.zoltanboros.finalassignment.singleton.Graph
import com.zoltanboros.finalassignment.ui.HelperViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryListViewModel(
    private val repository: Repository = Graph.repository,
    private val helperViewModel: HelperViewModel
): ViewModel(){
    var state by mutableStateOf(CountryListState())
        private set

    init {
        getItems()
    }

    private fun getItems(){
        viewModelScope.launch {
            repository.countries.collectLatest {
                state = state.copy(
                    countries = it
                )
            }
        }
    }

    fun selectedItem(country: Country){
        helperViewModel.selectedCountryId.value = country.id
    }

    fun deleteItem(country: Country)
    {
        viewModelScope.launch {
            repository.deleteCountry(country)
        }
    }

}

data class CountryListState(
    val countries: List<Country> = emptyList()
)
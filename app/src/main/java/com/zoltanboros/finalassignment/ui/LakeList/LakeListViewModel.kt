package com.zoltanboros.finalassignment.ui.LakeList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanboros.finalassignment.model.Lake
import com.zoltanboros.finalassignment.repository.Repository
import com.zoltanboros.finalassignment.singleton.Graph
import com.zoltanboros.finalassignment.ui.HelperViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LakeListViewModel(
    private val repository: Repository = Graph.repository,
    private val helperViewModel: HelperViewModel
): ViewModel() {
    var state by mutableStateOf(LakeListState())
        private set
    init {
        getItems(helperViewModel.selectedCountryId.value ?: 0)
    }

    private fun getItems(countryId: Int) {
            viewModelScope.launch {
                repository.getLakesForCountry(countryId).collectLatest {
                    state = state.copy(
                        lakes = it
                    )
                }
            }
    }

    fun updateLake(lake: Lake) {
        viewModelScope.launch {
            repository.updateLake(lake.copy(selected = true))
        }
    }


    fun deleteLake(lake: Lake) {
        viewModelScope.launch {
            repository.deleteLake(lake)
        }
    }
}

data class LakeListState(
    val lakes: List<Lake> = emptyList()
)
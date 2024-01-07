package com.zoltanboros.finalassignment.ui.PlannedLakes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanboros.finalassignment.model.Lake
import com.zoltanboros.finalassignment.repository.Repository
import com.zoltanboros.finalassignment.singleton.Graph
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlannedViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel()
{
    var state by mutableStateOf(PlannedLakesState())
        private set

    init {
        getItems()
    }

    private fun getItems(){
        viewModelScope.launch {
            repository.selectedLakes.collectLatest {
                state = state.copy(
                    plannedLakes = it
                )
            }
        }
    }
    fun clearSelectedLakes() {
        viewModelScope.launch {
            repository.clearSelectedLakesInDatabase()
            state = state.copy(
                plannedLakes = state.plannedLakes.map { it.copy(selected = false) }
            )
        }
    }
}

data class PlannedLakesState(
    val plannedLakes: List<Lake> = emptyList()
)
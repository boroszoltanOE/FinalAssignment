package com.zoltanboros.finalassignment.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HelperViewModel : ViewModel() {
    val selectedCountryId = mutableStateOf<Int?>(null)
}
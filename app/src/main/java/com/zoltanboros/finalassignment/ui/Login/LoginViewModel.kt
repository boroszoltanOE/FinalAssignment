package com.zoltanboros.finalassignment.ui.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zoltanboros.finalassignment.data.MyConfiguration
import com.zoltanboros.finalassignment.data.MyConfiguration.loggedInUser
import com.zoltanboros.finalassignment.utils.MockupUser

class LoginViewModel: ViewModel() {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    init {

    }


    fun updateUserName(username: String) {
        this.username = username
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun login() : Boolean {
        MyConfiguration.loggedInUser = MockupUser.getUser(username, password)
        if (loggedInUser != null) {
            password = ""
            username = ""
        }
        return MyConfiguration.loggedInUser != null
    }
}
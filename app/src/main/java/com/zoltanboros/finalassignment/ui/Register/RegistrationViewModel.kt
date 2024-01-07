package com.zoltanboros.finalassignment.ui.Register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zoltanboros.finalassignment.model.User
import com.zoltanboros.finalassignment.utils.MockupUser

class RegistrationViewModel : ViewModel() {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var passwordConf by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set

    fun updateUserName(username: String) {
        this.username = username
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun updatePasswordConf(password: String) {
        this.passwordConf = password
    }
    fun updateEmail(email: String) {
        this.email = email
    }

    fun registrate(): Boolean {
        val user = User(
            id = User.GlobalState.getNextInt(),
            userName = username,
            email = email,
            password = password
        ) // Create a user object from the current state

        // Check if the user already exists in the list
        if (MockupUser.getUsers().toMutableList().any { it.userName == user.userName || it.email == user.email }) {
            // User already exists, registration failed
            return false
        }
        if (password != passwordConf){
            return false
        }
        MockupUser.addUser(user)
        // MockupUser.setUsers(existingUsers) // Uncomment if you have a method to set users in MockupUser

        // Clear the form fields after successful registration
        password = ""
        username = ""
        email = ""
        passwordConf = ""

        return true
    }
}
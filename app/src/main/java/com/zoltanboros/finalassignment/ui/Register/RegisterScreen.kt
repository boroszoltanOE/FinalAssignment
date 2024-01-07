package com.zoltanboros.finalassignment.ui.Register

import PasswordTextField
import UsernameTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zoltanboros.finalassignment.R

@Composable
fun RegisterScreen(
    registrationViewModel: RegistrationViewModel = viewModel(),
    registrationSuccessFull: () -> Unit,
    modifier: Modifier = Modifier
) {
    var registrationResultMessage by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White) // Set your preferred background color
    ) {
        Spacer(Modifier.height(16.dp))

        UsernameTextField(
            userName = registrationViewModel.username,
            onValueChange = { registrationViewModel.updateUserName(it) }
        )

        Spacer(Modifier.height(16.dp))

        PasswordTextField(
            password = registrationViewModel.password,
            onValueChange = { registrationViewModel.updatePassword(it) }
        )

        Spacer(Modifier.height(16.dp))

        PasswordConfTextField(
            password = registrationViewModel.passwordConf,
            onValueChange = { registrationViewModel.updatePasswordConf(it) }
        )

        Spacer(Modifier.height(16.dp))

        EmailTextField(
            email = registrationViewModel.email,
            onValueChange = { registrationViewModel.updateEmail(it) }
        )

        Spacer(Modifier.height(16.dp))

        RegisterButton {
            if (registrationViewModel.registrate()) {
                registrationSuccessFull()
            } else {
                registrationResultMessage = "Unmatched password or existing user"
            }
        }

        Spacer(Modifier.height(16.dp))

        registrationResultMessage?.let {message ->
            Text(
                text = message,
                color = Color.Red,
                modifier = Modifier.padding(16.dp),
                )
            Button(
                onClick = { registrationResultMessage = null },
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text("OK")
            }
        }
        // Additional UI elements as needed
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(email: String, onValueChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.email)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black // Set your preferred text color
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray, // Set your preferred focused color
            unfocusedIndicatorColor = Color.Gray // Set your preferred text color
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordConfTextField(password: String, onValueChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.passwordagain)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black // Set your preferred text color
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray, // Set your preferred focused color
            unfocusedIndicatorColor = Color.Gray
        )
    )
}

@Composable
fun RegisterButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray, // Set your preferred button color
            contentColor = Color.White // Set your preferred text color
        )
    ) {
        Icon(imageVector = Icons.Default.Send, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(stringResource(R.string.registrate))
    }
}
@Composable
@Preview
fun RegisterScreenPreview() {
    // Create a sample RegistrationViewModel
    val registrationViewModel = RegistrationViewModel()

    // Provide a preview using the @Preview annotation
    RegisterScreen(registrationViewModel = registrationViewModel, registrationSuccessFull = {})
}
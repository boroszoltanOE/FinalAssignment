
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zoltanboros.finalassignment.R
import com.zoltanboros.finalassignment.ui.Login.LoginViewModel
import com.zoltanboros.finalassignment.ui.PlannedLakes.PlannedViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    plannedViewModel: PlannedViewModel = viewModel(),
    loginSuccessFull: () -> Unit,
    Registration: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White) // Set your preferred background color
    ) {
        Spacer(Modifier.height(16.dp))

        UsernameTextField(
            userName = loginViewModel.username,
            onValueChange = { loginViewModel.updateUserName(it) }
        )

        Spacer(Modifier.height(16.dp))

        PasswordTextField(
            password = loginViewModel.password,
            onValueChange = { loginViewModel.updatePassword(it) }
        )

        Spacer(Modifier.height(10.dp))

        LoginButton {
            if (loginViewModel.login()) {
                plannedViewModel.clearSelectedLakes()
                loginSuccessFull()
            }
        }

        Spacer(Modifier.height(10.dp))

        RegistrationButton {
            Registration()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameTextField(userName: String, onValueChange: (String) -> Unit) {
    TextField(
        value = userName,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.username)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black // Set your preferred text color
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Gray, // Set your preferred focused color
            unfocusedIndicatorColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(password: String, onValueChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.password)) },
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
fun LoginButton(onClick: () -> Unit) {
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
        Text(stringResource(R.string.login))
    }
}

@Composable
fun RegistrationButton(onClick: () -> Unit) {
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
        Text(stringResource(R.string.registrate))
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    // Create a sample LoginViewModel
    val loginViewModel = LoginViewModel()

    // Provide a preview using the @Preview annotation
    LoginScreen(
        loginViewModel = loginViewModel,
        loginSuccessFull = {},
        Registration = {}
    )
}
package com.zoltanboros.finalassignment

import LoginScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zoltanboros.finalassignment.data.MyConfiguration
import com.zoltanboros.finalassignment.ui.CountryList.CountryListScreen
import com.zoltanboros.finalassignment.ui.CountryList.CountryListViewModel
import com.zoltanboros.finalassignment.ui.HelperViewModel
import com.zoltanboros.finalassignment.ui.LakeList.LakeListScreen
import com.zoltanboros.finalassignment.ui.LakeList.LakeListViewModel
import com.zoltanboros.finalassignment.ui.PlannedLakes.PlannedScreen
import com.zoltanboros.finalassignment.ui.Register.RegisterScreen

enum class TripAppScreen(@StringRes val title: Int) {
    Login(title = R.string.app_name),
    Registration(title = R.string.registration),
    CountryList(title = R.string.countryList),
    LakeList(title = R.string.lakeList),
    PlannedLakes(title = R.string.plannedList),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripAppBar(
    currentScreen: TripAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navigateToPlanned: () -> Unit,
    showExit: Boolean,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        navigationIcon = {
            if (canNavigateBack && currentScreen.title != TripAppScreen.CountryList.title && currentScreen.title != TripAppScreen.Login.title) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            // You can add additional actions here based on your requirements
            if (currentScreen == TripAppScreen.LakeList || currentScreen == TripAppScreen.CountryList) {
                IconButton(onClick = navigateToPlanned) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                }
            }
            if (showExit) {
                IconButton(onClick = {showDialog = true}) {
                    Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
                }
            }
        },
        modifier = modifier
    )


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Logout") },
            text = { Text("Are you sure?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        onLogout()
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("No")
                }
            }
        )
    }
}
@Composable
fun TripApp(navController: NavHostController = rememberNavController()) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = TripAppScreen.valueOf(
        backStackEntry?.destination?.route ?: TripAppScreen.Login.name
    )
    var showExitAction by remember {
        mutableStateOf(false)
    }
    val helperViewModel = viewModel<HelperViewModel>()
    Scaffold(
        topBar = {
            TripAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                             navigateUp(navController)
                },
                showExit = showExitAction,
                navigateToPlanned = {
                    navController.navigate(TripAppScreen.PlannedLakes.name)
                },
                onLogout = { onLogout(navController)}
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = TripAppScreen.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = TripAppScreen.Login.name) {
                showExitAction = false
                LoginScreen(
                    loginSuccessFull = {
                        navController.navigate(TripAppScreen.CountryList.name)
                    },
                    Registration = {
                        navController.navigate(TripAppScreen.Registration.name)
                    }
                )
            }
            composable(route = TripAppScreen.Registration.name) {
                showExitAction = false
                RegisterScreen(
                    registrationSuccessFull = { navController.navigate(TripAppScreen.Login.name) })

            }
            composable(route = TripAppScreen.CountryList.name) {
                showExitAction = true
                CountryListScreen(
                    countryListViewModel = CountryListViewModel(helperViewModel = helperViewModel),
                    selectedSuccess = {
                        navController.navigate(TripAppScreen.LakeList.name)
                    }
                )
            }
            composable(route = TripAppScreen.LakeList.name) {
                showExitAction = true
                LakeListScreen(
                    lakeListViewModel = LakeListViewModel(helperViewModel = helperViewModel)
                )
            }
            composable(route = TripAppScreen.PlannedLakes.name) {
                showExitAction = true
                PlannedScreen()
            }
        }
    }

}
fun navigateUp(navController: NavHostController) {
    if (navController.previousBackStackEntry?.destination?.route.equals(TripAppScreen.Login.name)) {
        MyConfiguration.loggedInUser = null
    }
    navController.navigateUp()
}
fun onLogout(navController: NavHostController) {
    MyConfiguration.loggedInUser = null
    navController.navigate(TripAppScreen.Login.name)
}
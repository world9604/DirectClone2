package com.example.directclone2.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.directclone2.ui.screen.connecteddevices.ConnectedDevicesScreen
import com.example.directclone2.ui.screen.display.DisplayScreen
import com.example.directclone2.ui.components.BasicAppBar
import com.example.directclone2.ui.screen.apps.AppsScreen
import com.example.directclone2.ui.screen.battery.BatteryScreen
import com.example.directclone2.ui.screen.locationandsecurity.LocationAndSecurityScreen
import com.example.directclone2.ui.screen.networkandinternet.NetworkAndInternetScreen
import com.example.directclone2.ui.screen.main.MainScreen
import com.example.directclone2.ui.screen.settings.SettingsScreen
import com.example.directclone2.ui.screen.sound.SoundScreen
import com.example.directclone2.ui.screen.system.SystemScreen

@Composable
fun DirectCloneApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: Screen.BackupContent.route

    Scaffold(
        topBar = {
            Column {
                BasicAppBar(
                    routeOfCurrentScreen = currentScreen,
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.BackupContent.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.BackupContent.route) {
                MainScreen(
                    modifier = Modifier.fillMaxHeight(),
                    onAppClicked = { navController.navigate(Screen.Settings.route) })
            }
            composable(route = Screen.Settings.route) {
                SettingsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    navigate = { screen ->
                        val profileId = navController.currentBackStackEntry?.savedStateHandle?.get<String>(DirectCloneArgs.PROFILE_ID_ARG)
                        navController.currentBackStackEntry?.savedStateHandle?.remove<String>(DirectCloneArgs.PROFILE_ID_ARG)
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, profileId)
                        Log.d("DirectCloneApp", "SettingsScreen profileId: $profileId")
                        navController.navigate("${screen.route}?${DirectCloneArgs.PROFILE_ID_ARG}=${profileId ?: ""}")
                    })
            }
            composable(route = Screen.ConnectedDevices.routeWithArgs) {
                ConnectedDevicesScreen(
                    modifier = Modifier.fillMaxHeight(),
                    passProfileId = { id ->
                        Log.d("DirectCloneApp", "passProfileId : $id")
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, id)
                    }
                )
            }
            composable(route = Screen.Display.routeWithArgs) {
                DisplayScreen(
                    modifier = Modifier.fillMaxHeight(),
                    passProfileId = { id ->
                        Log.d("DirectCloneApp", "passProfileId : $id")
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, id)
                    }
                )
            }
            composable(route = Screen.System.routeWithArgs) {
                SystemScreen(
                    modifier = Modifier.fillMaxHeight(),
                    passProfileId = { id ->
                        Log.d("DirectCloneApp", "passProfileId : $id")
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, id)
                    }
                )
            }
            composable(route = Screen.LocationAndSecurity.routeWithArgs) {
                LocationAndSecurityScreen(
                    modifier = Modifier.fillMaxHeight(),
                    passProfileId = { id ->
                        Log.d("DirectCloneApp", "passProfileId : $id")
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, id)
                    }
                )
            }
            composable(route = Screen.Battery.routeWithArgs) {
                BatteryScreen(
                    modifier = Modifier.fillMaxHeight(),
                    passProfileId = { id ->
                        Log.d("DirectCloneApp", "passProfileId : $id")
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, id)
                    }
                )
            }
            composable(route = Screen.Apps.routeWithArgs) {
                AppsScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.Sound.routeWithArgs) {
                SoundScreen(
                    modifier = Modifier.fillMaxHeight(),
                    passProfileId = { id ->
                        Log.d("DirectCloneApp", "passProfileId : $id")
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, id)
                    }
                )
            }
            composable(route = Screen.NetworkAndInternet.routeWithArgs) {
                NetworkAndInternetScreen(
                    modifier = Modifier.fillMaxHeight(),
                    passProfileId = { id ->
                        Log.d("DirectCloneApp", "passProfileId : $id")
                        navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, id)
                    }
                )
            }
        }
    }
}
package com.example.directclone2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    navigateBack = {
                        //navController.previousBackStackEntry?.savedStateHandle?.set(DirectCloneArgs.PROFILE_ID_ARG, currentProfileId)
                        navController.popBackStack()
                    }
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
            composable(route = Screen.Settings.routeWithArgs) { entry ->
                val profileId = entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                SettingsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    navigate = { screen ->
                        navController.navigate("${screen.route}?${DirectCloneArgs.PROFILE_ID_ARG}=${profileId ?: ""}")
                    })
            }
            composable(
                route = Screen.ConnectedDevices.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })
            ) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                ConnectedDevicesScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(
                route = Screen.Display.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                DisplayScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(
                route = Screen.System.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                SystemScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(
                route = Screen.LocationAndSecurity.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                LocationAndSecurityScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(
                route = Screen.Battery.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                BatteryScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(
                route = Screen.Apps.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                AppsScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(
                route = Screen.Sound.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                SoundScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(
                route = Screen.NetworkAndInternet.routeWithArgs,
                arguments = listOf(navArgument(DirectCloneArgs.PROFILE_ID_ARG) {
                    type = NavType.StringType; nullable = true
                })) { entry ->
                entry.arguments?.getString(DirectCloneArgs.PROFILE_ID_ARG)
                NetworkAndInternetScreen(modifier = Modifier.fillMaxHeight())
            }
        }
    }
}
package com.example.directclone2.ui

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
import com.example.directclone2.ui.components.AppBar
import com.example.directclone2.ui.screen.apps.AppsScreen
import com.example.directclone2.ui.screen.battery.BatteryScreen
import com.example.directclone2.ui.screen.locationandsecurity.LocationAndSecurityScreen
import com.example.directclone2.ui.screen.networkandinternet.NetworkAndInternetScreen
import com.example.directclone2.ui.screen.main.MainScreen
import com.example.directclone2.ui.screen.settings.SettingsScreen
import com.example.directclone2.ui.screen.sound.SoundScreen
import com.example.directclone2.ui.screen.system.SystemScreen
import com.example.directclone2.ui.screen.main.MainUiState.NavigationRes

@Composable
fun DirectCloneApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationRes.valueOf(
        backStackEntry?.destination?.route ?: NavigationRes.Apps.name)
    Scaffold(
        topBar = {
            Column {
                AppBar(currentScreen = currentScreen)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRes.Backup.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = NavigationRes.Backup.name) {
                MainScreen(
                    modifier = Modifier.fillMaxHeight(),
                    onAppClicked = { navController.navigate(NavigationRes.Settings.name) })
            }
            composable(route = NavigationRes.Settings.name) {
                SettingsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    navController = navController)
            }
            composable(route = NavigationRes.ConnectedDevices.name) {
                ConnectedDevicesScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = NavigationRes.Display.name) {
                DisplayScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = NavigationRes.System.name) {
                SystemScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = NavigationRes.LocationAndSecurity.name) {
                LocationAndSecurityScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = NavigationRes.Battery.name) {
                BatteryScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = NavigationRes.Apps.name) {
                AppsScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = NavigationRes.Sound.name) {
                SoundScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = NavigationRes.NetworkAndInternet.name) {
                NetworkAndInternetScreen(modifier = Modifier.fillMaxHeight())
            }
        }
    }
}
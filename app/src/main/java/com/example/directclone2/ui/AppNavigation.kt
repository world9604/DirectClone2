package com.example.directclone2.ui

import androidx.annotation.StringRes
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
import com.example.directclone2.R
import com.example.directclone2.ui.components.AppBar
import com.example.directclone2.ui.screen.apps.AppsScreen
import com.example.directclone2.ui.screen.battery.BatteryScreen
import com.example.directclone2.ui.screen.locationandsecurity.LocationAndSecurityScreen
import com.example.directclone2.ui.screen.networkandinternet.NetworkAndInternetScreen
import com.example.directclone2.ui.screen.main.MainScreen
import com.example.directclone2.ui.screen.SettingsScreen
import com.example.directclone2.ui.screen.sound.SoundScreen
import com.example.directclone2.ui.screen.system.SystemScreen

enum class AppNavigation(@StringRes val title: Int) {
    Backup(title = R.string.app_name),
    Sync(title = R.string.app_name),
    Settings(title = R.string.settings_name),
    Sound(title = R.string.sound_label),
    Battery(title = R.string.battery_label),
    LocationAndSecurity(title = R.string.location_security_label),
    ConnectedDevices(title = R.string.connected_devices_name),
    Apps(title = R.string.apps_label),
    Display(title = R.string.display_label),
    System(title = R.string.system_label),
    NetworkAndInternet(title = R.string.network_internet_label),
}

@Composable
fun DirectCloneApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppNavigation.valueOf(
        backStackEntry?.destination?.route ?: AppNavigation.Apps.name
    )

    Scaffold(
        topBar = {
            Column {
                AppBar(
                    currentScreen = currentScreen)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppNavigation.Backup.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = AppNavigation.Backup.name) {
                MainScreen(
                    modifier = Modifier.fillMaxHeight(),
                    onAppClicked = { navController.navigate(AppNavigation.Settings.name) },
                )
            }
            composable(route = AppNavigation.Settings.name) {
                SettingsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    onBackupClicked = { navController.navigate(AppNavigation.Backup.name) },
                    onSyncClicked = { navController.navigate(AppNavigation.Sync.name) },
                    onSettingsClicked = { navController.navigate(AppNavigation.Settings.name) },
                    onSoundClicked = { navController.navigate(AppNavigation.Sound.name) },
                    onBatteryClicked = { navController.navigate(AppNavigation.Battery.name) },
                    onLocationAndSecurityClicked = { navController.navigate(AppNavigation.LocationAndSecurity.name) },
                    onConnectedDevicesClicked = { navController.navigate(AppNavigation.ConnectedDevices.name) },
                    onAppsClicked = { navController.navigate(AppNavigation.Apps.name) },
                    onDisplayClicked = { navController.navigate(AppNavigation.Display.name) },
                    onSystemClicked = { navController.navigate(AppNavigation.System.name) },
                    onNetworkAndInternetClicked = { navController.navigate(AppNavigation.NetworkAndInternet.name) },
                )
            }
            composable(route = AppNavigation.ConnectedDevices.name) {
                ConnectedDevicesScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = AppNavigation.Display.name) {
                DisplayScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = AppNavigation.System.name) {
                SystemScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = AppNavigation.LocationAndSecurity.name) {
                LocationAndSecurityScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = AppNavigation.Battery.name) {
                BatteryScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = AppNavigation.Apps.name) {
                AppsScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = AppNavigation.Sound.name) {
                SoundScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = AppNavigation.NetworkAndInternet.name) {
                NetworkAndInternetScreen(modifier = Modifier.fillMaxHeight())
            }
        }
    }
}
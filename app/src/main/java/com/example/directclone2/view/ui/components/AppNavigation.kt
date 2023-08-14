package com.example.directclone2.view.ui.components

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
import com.example.directclone2.view.ConnectedDevicesScreen
import com.example.directclone2.view.DisplayScreen
import com.example.directclone2.R
import com.example.directclone2.view.AppsScreen
import com.example.directclone2.view.BatteryScreen
import com.example.directclone2.view.LocationAndSecurityScreen
import com.example.directclone2.view.NetworkAndInternetScreen
import com.example.directclone2.view.main.MainScreen
import com.example.directclone2.viewmodel.SettingViewModel
import com.example.directclone2.view.SettingsScreen
import com.example.directclone2.view.SoundScreen
import com.example.directclone2.view.SystemScreen

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
    vm: SettingViewModel,
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
                    vm = vm,
                    onAppClicked = { navController.navigate(AppNavigation.Settings.name) },
                )
            }
            composable(route = AppNavigation.Settings.name) {
                SettingsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
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
                ConnectedDevicesScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
            composable(route = AppNavigation.Display.name) {
                DisplayScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
            composable(route = AppNavigation.System.name) {
                SystemScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
            composable(route = AppNavigation.LocationAndSecurity.name) {
                LocationAndSecurityScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
            composable(route = AppNavigation.Battery.name) {
                BatteryScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
            composable(route = AppNavigation.Apps.name) {
                AppsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
            composable(route = AppNavigation.Sound.name) {
                SoundScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
            composable(route = AppNavigation.NetworkAndInternet.name) {
                NetworkAndInternetScreen(
                    modifier = Modifier.fillMaxHeight(),
                    vm = vm,
                )
            }
        }
    }
}
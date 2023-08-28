package com.example.directclone2.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.directclone2.ui.components.BasicAppBar
import com.example.directclone2.ui.screen.apps.AppsScreen
import com.example.directclone2.ui.screen.battery.BatteryScreen
import com.example.directclone2.ui.screen.connecteddevices.ConnectedDevicesScreen
import com.example.directclone2.ui.screen.display.DisplayScreen
import com.example.directclone2.ui.screen.locationandsecurity.LocationAndSecurityScreen
import com.example.directclone2.ui.screen.main.AppItem
import com.example.directclone2.ui.screen.main.MainScreen
import com.example.directclone2.ui.screen.main.startActivity
import com.example.directclone2.ui.screen.networkandinternet.NetworkAndInternetScreen
import com.example.directclone2.ui.screen.settings.SettingsScreen
import com.example.directclone2.ui.screen.sound.SoundScreen
import com.example.directclone2.ui.screen.system.SystemScreen


@Composable
fun DirectCloneApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: Screen.BackupContent.route
    val context = LocalContext.current

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
                    onAppClicked = {
                        try {
                            it.startActivity(context, navController)
                        } catch(e: Exception) {
                            //todo : 예외 처리
                            Log.d("DirectCloneApp", "This application does not exist. message : ${e.message}")
                        }
                    })
            }
            composable(route = Screen.Settings.route) {
                SettingsScreen(
                    modifier = Modifier.fillMaxHeight(),
                    navigate = { navController.navigate(it.route) })
            }
            composable(route = Screen.ConnectedDevices.route) {
                ConnectedDevicesScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.Display.route) {
                DisplayScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.System.route) {
                SystemScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.LocationAndSecurity.route) {
                LocationAndSecurityScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.Battery.route) {
                BatteryScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.Apps.route) {
                AppsScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.Sound.route) {
                SoundScreen(modifier = Modifier.fillMaxHeight())
            }
            composable(route = Screen.NetworkAndInternet.route) {
                NetworkAndInternetScreen(modifier = Modifier.fillMaxHeight())
            }
        }
    }
}
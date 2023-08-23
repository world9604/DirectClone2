package com.example.directclone2.ui

import androidx.annotation.StringRes
import com.example.directclone2.R


object DirectCloneArgs {
    const val PROFILE_ID_ARG = "profile_id"
}

/*
enum class NavigationRes(@StringRes val title: Int, val isInSettingsScreen: Boolean) {
    Backup(title = R.string.app_name, false),
    Sync(title = R.string.app_name, false),
    Settings(title = R.string.settings_name, false),
    NetworkAndInternet(title = R.string.network_internet_label, true),
    ConnectedDevices(title = R.string.connected_devices_name, true),
    Battery(title = R.string.battery_label, true),
    Display(title = R.string.display_label, true),
    Sound(title = R.string.sound_label, true),
    Apps(title = R.string.apps_label, true),
    LocationAndSecurity(title = R.string.location_security_label, true),
    System(title = R.string.system_label, true),
}
 */

sealed class Screen(val route: String, @StringRes val resourceId: Int, val isInSettingScreen: Boolean) {
    object BackupContent: Screen("backup_content", R.string.app_name, false)
    object SyncContent: Screen("sync_content", R.string.app_name, false)
    object Settings: Screen("settings", R.string.settings_name, false) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object NetworkAndInternet: Screen("network_internet", R.string.network_internet_label, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object ConnectedDevices: Screen("connected_devices", R.string.connected_devices_name, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object Battery: Screen("battery", R.string.battery_label, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object Display: Screen("display", R.string.display_label, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object Sound: Screen("sound", R.string.sound_label, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object Apps: Screen("apps", R.string.apps_label, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object LocationAndSecurity: Screen("location_security", R.string.location_security_label, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
    object System: Screen("system", R.string.system_label, true) {
        val routeWithArgs = "$route?${DirectCloneArgs.PROFILE_ID_ARG}={${DirectCloneArgs.PROFILE_ID_ARG}}"
        //val routeWithArgs = "$route/{${DirectCloneArgs.PROFILE_ID_ARG}}"
    }
}

val screens = listOf(
    Screen.BackupContent,
    Screen.SyncContent,
    Screen.Settings,
    Screen.NetworkAndInternet,
    Screen.ConnectedDevices,
    Screen.Battery,
    Screen.Display,
    Screen.Sound,
    Screen.Apps,
    Screen.LocationAndSecurity,
    Screen.System,
)
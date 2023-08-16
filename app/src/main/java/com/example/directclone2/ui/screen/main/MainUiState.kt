package com.example.directclone2.ui.screen.main

import androidx.annotation.StringRes
import com.example.directclone2.R
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class MainUiState(
    val backupFileName: String = "",
    val isInternalStorage: Boolean = false,
    val backupFileSaveLocation: String = "",
    val usePassword: Boolean = false,
    val password: String = "",
    val confirmPassword: String = "",
    val openBackupDialog: Boolean = false,
    val openSetPasswordDialog: Boolean = false,
    val openBackupResultDialog: Boolean = false,
    val isCompletedCreateBackupFile: Boolean = false,
) {
    enum class TabContent(val index: Int, @StringRes val textRes: Int) {
        Backup(0, R.string.backup_content_title), Sync(1, R.string.sync_content_title)
    }

    enum class SettingsRes(@StringRes val title: Int) {
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
}

fun <T: Any> MainUiState.update(fieldToBeUpdated: String, value: T): MainUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as MainUiState
}
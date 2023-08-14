package com.example.directclone2.viewmodel

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.directclone2.R
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.view.ui.components.AppNavigation
import com.example.directclone2.view.AppItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SettingViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "SettingViewModel"
    }

    var uiState by mutableStateOf(ProfileUiState())
        private set

    fun getScreenBrightnessForText(): Int {
        return (uiState.screenBrightness * 100f).toInt()
    }

    fun getBatteryLowWarningLevelForText(): Int {
        return (uiState.batteryLowWarningLevel * 100f).toInt()
    }

    fun getBatteryCriticalWarningLevelForText(): Int {
        return (uiState.batteryCriticalWarningLevel * 100f).toInt()
    }

    fun updateIsOkSmartChargingInfo() = viewModelScope.launch {
        uiState = uiState.copy(isOkSmartChargingInfo = !uiState.isOkSmartChargingInfo)
    }

    fun updateScreenLock(value: Int) = viewModelScope.launch {
        var tmp = uiState.copy(screenLock = value)
        uiState = when (value) {
            ScreenLock.None.resIdOfTitle or ScreenLock.Swipe.resIdOfTitle -> tmp.copy(screenLockPin = "", screenLockPassword = "")
            ScreenLock.Pin.resIdOfTitle -> tmp.copy(screenLockPassword = "")
            ScreenLock.Password.resIdOfTitle -> tmp.copy(screenLockPin = "")
            else -> tmp
        }
    }

    fun updateSystemDate(date: Long) = viewModelScope.launch {
        val systemDate = millisecondsToDateFormat(date)
        uiState = uiState.copy(systemDate = systemDate)
    }

    private fun millisecondsToDateFormat(currentTime: Long): String {
        val dateFormat = SimpleDateFormat("yyyy - MM - dd", Locale.getDefault())
        return dateFormat.format(Date(currentTime))
    }

    fun updateSystemTime(hour: Int, minute: Int, is24hour: Boolean) = viewModelScope.launch {
        val formattedTime = "%02d:%02d".format(hour, minute)
        uiState = uiState.copy(systemTime = formattedTime)
    }

    fun openBackupResultDialog() {
        if (uiState.usePassword) {
            uiState = uiState.copy(openSetPasswordDialog = true)
        } else {
            uiState = uiState.copy(openBackupResultDialog = true)
            save()
        }
    }

    fun <T: Any> update(field: String, value: T) = viewModelScope.launch {
        uiState = uiState.update(field, value)
    }

    fun save() = viewModelScope.launch {
        Log.d(TAG, "SettingViewModel::save\nuiState : $uiState")
        repo.create(uiState)
    }

    enum class TabContent(val index: Int, @StringRes val textRes: Int) {
        Backup(0, R.string.backup_content_title), Sync(1, R.string.sync_content_title)
    }

    val tabs = TabContent.values()

    var currentTab by mutableStateOf(TabContent.Backup)
        private set

    fun updateTab(tab: TabContent) {
        currentTab = tab
    }

    private val _settingItems = getSettingsItems().toMutableStateList()
    val settingItems: List<Pair<String, AppNavigation>>
        get() = _settingItems

    private val _appsForBackup = getAppItems().toMutableStateList()
    val appsForBackup: List<AppItem>
        get() = _appsForBackup

    fun haveAtLeastOneAppToBackUp() = appsForBackup.any { it.selected }

    fun matchPasswordAndConfirmPassword() = uiState.run {
        password.isNotEmpty() && (password == confirmPassword)
    }

    fun remove(item: AppItem) = viewModelScope.launch {
        _appsForBackup.remove(item)
    }

    fun changeAppSelected(item: AppItem, selected: Boolean) = viewModelScope.launch {
        appsForBackup.find { it.appName == item.appName }?.let {
            it.selected = selected
        }
    }

    fun getNumOfSelectedApps() = appsForBackup.count{ it.selected }
}

private fun getAppItems() = listOf (
    AppItem(appName = "Program Buttons", isPreInstalledApp = true),
    AppItem(appName = "Scan Settings", isPreInstalledApp = true),
    AppItem(appName = "Settings", isPreInstalledApp = true),
    AppItem(appName = "EmKiosk", isPreInstalledApp = false),
    AppItem(appName = "EmKiosk Config", isPreInstalledApp = false),
    AppItem(appName = "EmKiosk Config2", isPreInstalledApp = false),
    AppItem(appName = "EmKiosk Config3", isPreInstalledApp = false),
    AppItem(appName = "EmKiosk Config4", isPreInstalledApp = false),
    AppItem(appName = "EmKiosk Config5", isPreInstalledApp = false)
)

private fun getSettingsItems() = listOf (
    Pair("Network & Internet", AppNavigation.NetworkAndInternet),
    Pair("Connected devices", AppNavigation.ConnectedDevices),
    Pair("Battery", AppNavigation.Battery),
    Pair("Display", AppNavigation.Display),
    Pair("Sound", AppNavigation.Sound),
    Pair("Apps", AppNavigation.Apps),
    Pair("Location & Security", AppNavigation.LocationAndSecurity),
    Pair("System", AppNavigation.System),
)
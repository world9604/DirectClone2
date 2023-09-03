package com.example.directclone2.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.directclone2.DirectCloneApplication
import com.example.directclone2.model.IProfileRepository
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.screen.battery.BatteryUiState
import com.example.directclone2.ui.screen.battery.update
import com.example.directclone2.ui.screen.connecteddevices.ConnectedDevicesUiState
import com.example.directclone2.ui.screen.connecteddevices.update
import com.example.directclone2.ui.screen.display.DisplayUiState
import com.example.directclone2.ui.screen.display.update
import com.example.directclone2.ui.screen.locationandsecurity.LocationAndSecurityUiState
import com.example.directclone2.ui.screen.locationandsecurity.update
import com.example.directclone2.ui.screen.main.AppItem
import com.example.directclone2.ui.screen.main.BackupFile
import com.example.directclone2.ui.screen.main.MainUiState
import com.example.directclone2.ui.screen.main.update
import com.example.directclone2.ui.screen.networkandinternet.NetworkAndInternetUiState
import com.example.directclone2.ui.screen.networkandinternet.update
import com.example.directclone2.ui.screen.sound.SoundUiState
import com.example.directclone2.ui.screen.sound.update
import com.example.directclone2.ui.screen.system.SystemUiState
import com.example.directclone2.ui.screen.system.update
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SettingViewModel (
    private val repo: IProfileRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    companion object {
        const val TAG = "SettingViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                val savedStateHandle = createSavedStateHandle()
                SettingViewModel((application).container.profileRepository, savedStateHandle)
            }
        }
    }

    private val _workingProfileId: MutableStateFlow<String?> = MutableStateFlow(null)
    val workingProfileId: StateFlow<String?> = _workingProfileId

    private val _backupFiles: MutableStateFlow<List<BackupFile>> = MutableStateFlow(listOf())
    val backupFiles: StateFlow<List<BackupFile>> = _backupFiles

    var mainUiState by mutableStateOf(MainUiState())
        private set

    init {
        observeProfileId()
        observeFiles()
        updateFileName()
    }

    private fun observeProfileId() = viewModelScope.launch {
        repo.getWorkingProfileIdStream().collect {
            Log.d(ProfileRepository.TAG, "collect in getWorkingProfileIdStream() : $it")
            _workingProfileId.value = it
            return@collect
        }
    }

    private fun observeFiles() = viewModelScope.launch {
        repo.getFilesStream().collect {
            it.forEach { file ->
                Log.d(ProfileRepository.TAG, "collect in getBackupFileNameStream" +
                        "id : ${file.profileId}, name : ${file.file.name}," +
                        "date : ${file.createdDate}, password : ${file.password}")
            }
            mainUiState = mainUiState.copy(backupFiles = it)
            return@collect
        }
    }

    private fun updateFileName() = viewModelScope.launch {
        Log.d(TAG, "updateFileName: ${workingProfileId.value}")
        Log.d(TAG, "updateFileName: ${mainUiState.fileName}")
        if(mainUiState.fileName.isNotBlank()) return@launch
        val profileId = workingProfileId.value ?: repo.createProfile()
        mainUiState = mainUiState.copy(
            fileName = repo.updateBackupFileInfo(profileId),
            directoryForBackup = repo.getBackupFileDirectory(),
            appsForBackup = repo.getBackupApps())
    }

    var batteryUiState by mutableStateOf(BatteryUiState())
        private set

    fun <T: Any> updateBattery(field: String, value: T) {
        batteryUiState = batteryUiState.update(field, value)
        viewModelScope.launch {
            val profileId = workingProfileId.value ?: repo.createProfile()
            repo.updateBattery(
                profileId,
                batteryUiState.smartCharging.name,
                getBatteryLowWarningLevelForText().toString(),
                getBatteryCriticalWarningLevelForText().toString())
        }
    }

    fun updateIsOkSmartChargingInfo() = viewModelScope.launch {
        batteryUiState = batteryUiState.copy(isOkSmartChargingInfo = !batteryUiState.isOkSmartChargingInfo)
    }

    fun getBatteryLowWarningLevelForText(): Int {
        return (batteryUiState.batteryLowWarningLevel * 100f).toInt()
    }

    fun getBatteryCriticalWarningLevelForText(): Int {
        return (batteryUiState.batteryCriticalWarningLevel * 100f).toInt()
    }

    var connectedDevicesUiState by mutableStateOf(ConnectedDevicesUiState())
        private set

    fun <T: Any> updateConnectedDevices(field: String, value: T) {
        connectedDevicesUiState = connectedDevicesUiState.update(field, value)
        viewModelScope.launch {
            val profileId = workingProfileId.value ?: repo.createProfile()
            repo.updateConnectedDevices(
                profileId,
                connectedDevicesUiState.nfc,
                connectedDevicesUiState.bluetooth
            )
        }
    }

    var displayUiState by mutableStateOf(DisplayUiState())
        private set

    fun <T: Any> updateDisplay(field: String, value: T) {
        displayUiState = displayUiState.update(field, value)
        viewModelScope.launch {
            val profileId = workingProfileId.value ?: repo.createProfile()
            repo.updateDisplay(
                profileId,
                getScreenBrightnessForText().toString(),
                displayUiState.autoScreenRotate,
                displayUiState.adaptiveBrightness,
                displayUiState.systemFontSize.name,
                displayUiState.systemDisplaySize.name,
                displayUiState.touchSensitivity.name,
            )
        }
    }

    fun getScreenBrightnessForText(): Int {
        return (displayUiState.screenBrightness * 100f).toInt()
    }

    var locationAndSecurityUiState by mutableStateOf(LocationAndSecurityUiState())
        private set

    fun <T: Any> updateScreenLock(field: String, value: T) {
        locationAndSecurityUiState = locationAndSecurityUiState.update(field, value)
        viewModelScope.launch {
            val profileId = workingProfileId.value ?: repo.createProfile()
            repo.updateScreenLock(
                profileId,
                locationAndSecurityUiState.useLocation,
                locationAndSecurityUiState.currentScreenLockPinOrPassword,
                locationAndSecurityUiState.screenLock.name,
                locationAndSecurityUiState.screenLockPin.ifEmpty { locationAndSecurityUiState.screenLockPassword },
                locationAndSecurityUiState.screenLockMessage,
                locationAndSecurityUiState.lockAfterScreenTimeout.name,
                locationAndSecurityUiState.powerButtonInstantlyLocks.name
            )
        }
    }

    fun updateScreenLock(value: Int) = viewModelScope.launch {
        locationAndSecurityUiState = when (value) {
            LocationAndSecurityUiState.ScreenLock.None.resIdOfTitle -> {
                locationAndSecurityUiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.None,
                    screenLockPin = "", screenLockPassword = "")
            }
            LocationAndSecurityUiState.ScreenLock.Swipe.resIdOfTitle -> {
                locationAndSecurityUiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.Swipe,
                    screenLockPin = "", screenLockPassword = "")
            }
            LocationAndSecurityUiState.ScreenLock.Pin.resIdOfTitle -> {
                locationAndSecurityUiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.Pin,
                    screenLockPassword = "")
            }
            LocationAndSecurityUiState.ScreenLock.Password.resIdOfTitle -> {
                locationAndSecurityUiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.Password,
                    screenLockPin = "")
            }
            else -> locationAndSecurityUiState
        }
    }

    fun <T: Any> updateMain(field: String, value: T) {
        mainUiState = mainUiState.update(field, value)
    }

    fun save() = viewModelScope.launch {
        val profileId = workingProfileId.value ?: repo.createProfile()
        repo.createBackupFile(profileId, mainUiState.password)
        delay(3000)
        mainUiState = mainUiState.copy(isCompletedCreateBackupFile = true)
    }

    val tabs = MainUiState.TabContent.values()

    var currentTab by mutableStateOf(MainUiState.TabContent.Backup)
        private set

    fun updateTab(tab: MainUiState.TabContent) {
        currentTab = tab
    }

    fun haveAtLeastOneAppToBackUp() = mainUiState.appsForBackup.isNotEmpty()

    fun getNumOfSelectedApps() = mainUiState.appsForBackup.count{ it.selected }

    fun matchPasswordAndConfirmPassword() = mainUiState.run {
        password.isNotEmpty() && (password == confirmPassword)
    }

    fun changeAppSelected(item: AppItem, selected: Boolean) = viewModelScope.launch {
        item.selected = selected
        val apps = mainUiState.appsForBackup.toMutableList()
        apps.add(item)
        val profileId = workingProfileId.value ?: repo.createProfile()
        Log.d(TAG, "profileId in changeAppSelected: $profileId")
        repo.updateBackupApps(profileId, apps)
        mainUiState = mainUiState.copy(appsForBackup = apps)
    }

    fun openBackupResultDialog() {
        if (mainUiState.usePassword) {
            mainUiState = mainUiState.copy(openSetPasswordDialog = true)
        } else {
            mainUiState = mainUiState.copy(openBackupResultDialog = true)
            save()
        }
    }

    fun initIsCompletedCreateBackupFile() {
        mainUiState = mainUiState.copy(isCompletedCreateBackupFile = false)
    }

    var networkAndInternetUiState by mutableStateOf(NetworkAndInternetUiState())
        private set

    fun <T: Any> updateNetworkAndInternet(field: String, value: T) {
        networkAndInternetUiState = networkAndInternetUiState.update(field, value)
        Log.d(TAG, "sharedProfileId 1: ${workingProfileId.value}")
        viewModelScope.launch {
            val profileId = workingProfileId.value ?: repo.createProfile()
            repo.updateNetworkAndInternet(
                profileId,
                networkAndInternetUiState.wifi,
                //uiState.savedNetworks,
                networkAndInternetUiState.dataServer,
                networkAndInternetUiState.roaming,
                networkAndInternetUiState.ethernet,
                networkAndInternetUiState.vpn,
            )
        }
    }

    var soundUiState by mutableStateOf(SoundUiState())
        private set

    fun <T: Any> updateSound(field: String, value: T) {
        soundUiState = soundUiState.update(field, value)
        viewModelScope.launch {
            val profileId = workingProfileId.value ?: repo.createProfile()
            repo.updateSound(
                profileId,
                soundUiState.vibrateOnTouch.name,
                soundUiState.conversations.name,
                soundUiState.messages.name,
                soundUiState.calls.name,
                floatToString(soundUiState.musicVolume),
                floatToString(soundUiState.ringVolume),
                floatToString(soundUiState.callVolume),
                floatToString(soundUiState.notificationVolume),
                floatToString(soundUiState.alarmVolume),
                soundUiState.alarms,
                soundUiState.mediaSounds,
                soundUiState.touchSounds,
                soundUiState.reminders,
                soundUiState.calendarEvents,
                soundUiState.dialPadTones,
                soundUiState.screenLockingSounds,
                soundUiState.chargingSoundsAndVibration,
                soundUiState.advancedTouchSounds,
                soundUiState.touchVibration,
            )
        }
    }

    private fun floatToString(value: Float): String {
        return (value * 100f).toInt().toString()
    }

    var systemUiState by mutableStateOf(SystemUiState())
        private set

    fun <T: Any> updateSystem(field: String, value: T) {
        systemUiState = systemUiState.update(field, value)
        viewModelScope.launch {
            val profileId = workingProfileId.value ?: repo.createProfile()
            repo.updateSystem(
                profileId,
                systemUiState.languages,
                systemUiState.spellChecker,
                systemUiState.spellCheckLanguage,
                systemUiState.defaultSpellChecker.name,
                //uiState.spellCheckPointerSpeed,
                systemUiState.useNetworkProvidedTime,
                systemUiState.systemDate,
                systemUiState.systemTime,
                systemUiState.useNetworkProvidedTimeZone,
                systemUiState.timeZone.toString(),
                systemUiState.use24hourFormat,
                systemUiState.ntpServer,
            )
        }
    }

    fun updateSystemDate(date: Long) = viewModelScope.launch {
        val systemDate = millisecondsToDateFormat(date)
        systemUiState = systemUiState.copy(systemDate = systemDate)
    }

    private fun millisecondsToDateFormat(currentTime: Long): String {
        val dateFormat = SimpleDateFormat("yyyy - MM - dd", Locale.getDefault())
        return dateFormat.format(Date(currentTime))
    }

    fun updateSystemTime(hour: Int, minute: Int, is24hour: Boolean) = viewModelScope.launch {
        val formattedTime = "%02d:%02d".format(hour, minute)
        systemUiState = systemUiState.copy(systemTime = formattedTime)
    }

    fun checkPassword() {
        if(mainUiState.passwordForRestoreOrClone.isNotBlank()
            and (mainUiState.password == mainUiState.passwordForRestoreOrClone)) {
            mainUiState = mainUiState.copy(isMatchedPassword = true)
        }
    }

    fun restore() {
        mainUiState = mainUiState.copy(isRestoring = true)
        viewModelScope.launch {
            repo.restore(mainUiState.selectedId)
        }
    }
}
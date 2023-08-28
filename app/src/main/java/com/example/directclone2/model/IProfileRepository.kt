package com.example.directclone2.model

import com.example.directclone2.model.data.Profile
import com.example.directclone2.ui.screen.main.AppItem
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {

    suspend fun createProfile(): String

    suspend fun getProfile(id: String): Profile?
    fun getWorkingProfileStream(): Flow<Profile?>
    fun getWorkingProfileIdStream(): Flow<String?>

    suspend fun getBackupFileDirectory(): String

    suspend fun updateBattery(profileId: String, smartCharging: String, batteryLowWarningLevel: String,
                              batteryCriticalWarningLevel: String)

    suspend fun updateConnectedDevices(profileId: String, nfc: Boolean, bluetooth: Boolean)

    suspend fun updateDisplay(profileId: String, screenBrightness: String, autoScreenRotate: Boolean,
                              adaptiveBrightness: Boolean, systemFontSize: String,
                              systemDisplaySize: String, touchSensitivity: String)

    suspend fun updateScreenLock(profileId: String, useLocation: Boolean, currentScreenLockPinOrPassword: String,
                                 screenLock: String, screenLockPinOrPassword: String,
                                 screenLockMessage: String, lockAfterScreenTimeout: String,
                                 powerButtonInstantlyLocks: String)

    suspend fun updateNetworkAndInternet(profileId: String, wifi: Boolean, dataServer: Boolean,
                                         roaming: Boolean, ethernet: String, vpn: String)

    suspend fun updateSound(
        profileId: String,
        vibrateOnTouch: String,
        conversations: String,
        messages: String,
        calls: String,
        musicVolume: String,
        ringVolume: String,
        callVolume: String,
        notificationVolume: String,
        alarmVolume: String,
        alarms: Boolean,
        mediaSounds: Boolean,
        touchSounds: Boolean,
        reminders: Boolean,
        calendarEvents: Boolean,
        dialPadTones: Boolean,
        screenLockingSounds: Boolean,
        chargingSoundsAndVibration: Boolean,
        advancedTouchSounds: Boolean,
        touchVibration: Boolean
    )

    suspend fun updateSystem(
        profileId: String,
        languages: String,
        spellChecker: Boolean,
        spellCheckLanguage: String,
        defaultSpellChecker: String,
        useNetworkProvidedTime: Boolean,
        systemDate: String,
        systemTime: String,
        useNetworkProvidedTimeZone: Boolean,
        timeZone: String,
        use24hourFormat: Boolean,
        ntpServer: String
    )

    suspend fun createBackupFile(id: String, password: String): Boolean

    suspend fun updateBackupApps(id: String, appName: String)

    suspend fun getBackupApps(): List<AppItem>
}
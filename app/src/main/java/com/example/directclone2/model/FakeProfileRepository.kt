package com.example.directclone2.model

import com.example.directclone2.model.data.Profile

class FakeProfileRepository: IProfileRepository {

    override suspend fun create(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(id: String): Profile? {
        TODO("Not yet implemented")
    }

    override suspend fun getBackupFileDirectory(profileId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateFileInfo(profileId: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateBattery(
        profileId: String,
        smartCharging: String,
        batteryLowWarningLevel: String,
        batteryCriticalWarningLevel: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateConnectedDevices(
        profileId: String,
        nfc: Boolean,
        bluetooth: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDisplay(
        profileId: String,
        screenBrightness: String,
        autoScreenRotate: Boolean,
        adaptiveBrightness: Boolean,
        systemFontSize: String,
        systemDisplaySize: String,
        touchSensitivity: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateScreenLock(
        profileId: String,
        useLocation: Boolean,
        currentScreenLockPinOrPassword: String,
        screenLock: String,
        screenLockPinOrPassword: String,
        screenLockMessage: String,
        lockAfterScreenTimeout: String,
        powerButtonInstantlyLocks: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNetworkAndInternet(
        profileId: String,
        wifi: Boolean,
        dataServer: Boolean,
        roaming: Boolean,
        ethernet: String,
        vpn: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSound(
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
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSystem(
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
    ) {
        TODO("Not yet implemented")
    }
}
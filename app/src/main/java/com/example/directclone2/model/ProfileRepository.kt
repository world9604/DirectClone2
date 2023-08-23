package com.example.directclone2.model

import com.example.directclone2.model.data.Profile
import com.example.directclone2.model.data.ProfileDao
import com.example.directclone2.model.usecase.MakeBackupFileNameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.UUID

@ExperimentalCoroutinesApi
@FlowPreview
class ProfileRepository (
    private val localDataSource: ProfileDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    //private val scope: CoroutineScope,
): IProfileRepository {

    companion object {
        @Volatile
        private var instance: ProfileRepository? = null
        fun getInstance(localDataSource: ProfileDao) =
            instance ?: synchronized(ProfileRepository::class.java) {
                instance ?: ProfileRepository(localDataSource).also {
                    instance = it
                }
            }
    }

    private fun Boolean.toEnableOrDisable(): String = if (this) "Enable" else "Disable"

    private fun Boolean.toOnOrOff(): String = if (this) "ON" else "OFF"

    override suspend fun create(): String {
        val profileId = withContext(dispatcher) {
            UUID.randomUUID().toString()
        }
        localDataSource.insert(Profile(id = profileId))
        return profileId
    }

    override suspend fun getBackupFileDirectory(profileId: String)
            = getProfile(profileId)?.filePath ?: throw Exception("Profile (id : $profileId) not found")
    override suspend fun getProfile(id: String): Profile? {
        return localDataSource.getProfile(id)
    }

    override suspend fun updateBattery(profileId: String, smartCharging: String, batteryLowWarningLevel: String,
                              batteryCriticalWarningLevel: String) {
        val profile = getProfile(profileId)?.copy(
            smartCharging = smartCharging,
            batteryLowWarningLevel = batteryLowWarningLevel,
            batteryCriticalWarningLevel = batteryCriticalWarningLevel
        ) ?: throw Exception("Profile (id $profileId) not found")
        localDataSource.update(profile)
    }

    override suspend fun updateConnectedDevices(profileId: String, nfc: Boolean, bluetooth: Boolean) {
        val profile = getProfile(profileId)?.copy(
            nfc = nfc.toOnOrOff(),
            bluetooth = bluetooth.toOnOrOff(),
        ) ?: throw Exception("Profile (id $profileId) not found")
        localDataSource.update(profile)
    }

    override suspend fun updateDisplay(profileId: String, screenBrightness: String, autoScreenRotate: Boolean,
                      adaptiveBrightness: Boolean, systemFontSize: String,
                      systemDisplaySize: String, touchSensitivity: String) {
        val profile = getProfile(profileId)?.copy(
            screenBrightness = screenBrightness,
            autoScreenRotate = autoScreenRotate.toEnableOrDisable(),
            adaptiveBrightness = adaptiveBrightness.toEnableOrDisable(),
            systemFontSize = systemFontSize,
            systemDisplaySize = systemDisplaySize,
            touchSensitivity = touchSensitivity,
        ) ?: throw Exception("Profile (id $profileId) not found")
        localDataSource.update(profile)
    }

    override suspend fun updateScreenLock(profileId: String, useLocation: Boolean, currentScreenLockPinOrPassword: String,
                                 screenLock: String, screenLockPinOrPassword: String,
                                 screenLockMessage: String, lockAfterScreenTimeout: String,
                                 powerButtonInstantlyLocks: String) {
        val profile = getProfile(profileId)?.copy(
            useLocation = useLocation.toOnOrOff(),
            currentScreenLockPinOrPassword = currentScreenLockPinOrPassword,
            screenLock = screenLock,
            screenLockPinOrPassword = screenLockPinOrPassword,
            screenLockMessage = screenLockMessage,
            lockAfterScreenTimeout = lockAfterScreenTimeout,
            powerButtonInstantlyLocks = powerButtonInstantlyLocks
        ) ?: throw Exception("Profile (id $profileId) not found")
        localDataSource.update(profile)
    }

    override suspend fun updateNetworkAndInternet(profileId: String, wifi: Boolean, dataServer: Boolean,
                                         roaming: Boolean, ethernet: String, vpn: String) {
        val profile = getProfile(profileId)?.copy(
            wifi = wifi.toOnOrOff(),
            dataServer = dataServer.toOnOrOff(),
            roaming = roaming.toOnOrOff(),
            vpn = vpn
        ) ?: throw Exception("Profile (id $profileId) not found")
        localDataSource.update(profile)
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
        val profile = getProfile(profileId)?.copy(
            vibrateOnTouch = vibrateOnTouch,
            musicVolume = musicVolume,
            ringVolume = ringVolume,
            callVolume = callVolume,
            notificationVolume = notificationVolume,
            alarmVolume = alarmVolume,
        ) ?: throw Exception("Profile (id $profileId) not found")
        localDataSource.update(profile)
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
        val profile = getProfile(profileId)?.copy(
            languages = languages,
            spellChecker = spellChecker,
            spellCheckLanguage = spellCheckLanguage,
            defaultSpellChecker = defaultSpellChecker,
            ntpServer = ntpServer,
            useNetworkProvidedTime = useNetworkProvidedTime.toOnOrOff(),
            systemDate = systemDate,
            systemTime = systemTime,
            useNetworkProvidedTimeZone = useNetworkProvidedTimeZone.toOnOrOff(),
            timeZone = timeZone,
            use24hourFormat = use24hourFormat.toOnOrOff()
        ) ?: throw Exception("Profile (id $profileId) not found")
        localDataSource.update(profile)
    }

    override suspend fun updateFileInfo(profileId: String, password: String) {
        val now = Calendar.getInstance().time
        val fileName = withContext(dispatcher) {
            MakeBackupFileNameUseCase("modelName", "partNum", now)
        }
        val profile = Profile(
            id = profileId,
            fileName = fileName,
            filePath = "/storage/emulated/0/",
            createdDate = now,
            password = password
        )
        localDataSource.update(profile)
    }
}
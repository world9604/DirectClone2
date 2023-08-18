package com.example.directclone2.model

import com.example.directclone2.model.data.LocalProfile
import com.example.directclone2.model.usecase.MakeBackupFileNameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.Calendar

@ExperimentalCoroutinesApi
@FlowPreview
class ProfileRepository (
    private val localDataSource: ProfileDiskDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    //private val scope: CoroutineScope,
) {
    private val accessMutex = Mutex()
    private var profile = LocalProfile()

    companion object {
        @Volatile
        private var instance: ProfileRepository? = null
        fun getInstance(localDataSource: ProfileDiskDataSource) =
            instance ?:   synchronized(ProfileRepository::class.java) {
                instance ?: ProfileRepository(localDataSource).also {
                    instance = it
                }
            }
    }

    /*
    fun observeAll() : Flow<BatteryUiState> {
        return localDataSource.observeAll().map {
            it.toExternal()
        }
    }
     */

    private fun Boolean.toEnableOrDisable(): String = if (this) "Enable" else "Disable"

    private fun Boolean.toOnOrOff(): String = if (this) "ON" else "OFF"

    suspend fun updateBattery(smartCharging: String, batteryLowWarningLevel: String,
                       batteryCriticalWarningLevel: String) = accessMutex.withLock {
        withContext(dispatcher) {
            val battery = profile.localBattery.copy(
                batteryCriticalWarningLevel = batteryCriticalWarningLevel,
                batteryLowWarningLevel = batteryLowWarningLevel
            )
            val system = profile.localSystem.copy(
                smartCharging = smartCharging
            )
            profile = profile.copy(localBattery = battery, localSystem = system)
        }
    }

    suspend fun updateConnectedDevices(nfc: Boolean, bluetooth: Boolean) = accessMutex.withLock {
        withContext(dispatcher) {
            val wireless = profile.localWireless.copy(
                nfc = nfc.toOnOrOff(),
                bluetooth = bluetooth.toOnOrOff()
            )
            profile = profile.copy(localWireless = wireless)
        }
    }

    suspend fun updateDisplay(screenBrightness: String, autoScreenRotate: Boolean,
                      adaptiveBrightness: Boolean, systemFontSize: String,
                      systemDisplaySize: String, touchSensitivity: String) = accessMutex.withLock {
        withContext(dispatcher) {
            val system = profile.localSystem.copy(
                screenBrightness = screenBrightness,
                autoScreenRotate = autoScreenRotate.toEnableOrDisable(),
                adaptiveBrightness = adaptiveBrightness.toEnableOrDisable(),
                systemFontSize = systemFontSize,
                systemDisplaySize = systemDisplaySize,
                touchSensitivity = touchSensitivity,
            )
            profile = profile.copy(localSystem = system)
        }
    }

    suspend fun updateScreenLock(useLocation: Boolean, currentScreenLockPinOrPassword: String,
                                 screenLock: String, screenLockPinOrPassword: String,
                                 screenLockMessage: String, lockAfterScreenTimeout: String,
                                 powerButtonInstantlyLocks: String)
    = accessMutex.withLock {
        withContext(dispatcher) {
            val localWireless = profile.localWireless.copy(useLocation = useLocation.toOnOrOff())
            val localScreenLock = profile.localScreenLock.copy(
                currentScreenLockPinOrPassword = currentScreenLockPinOrPassword,
                screenLock = screenLock,
                screenLockPinOrPassword = screenLockPinOrPassword,
                screenLockMessage = screenLockMessage,
                lockAfterScreenTimeout = lockAfterScreenTimeout,
                powerButtonInstantlyLocks = powerButtonInstantlyLocks
            )
            profile = profile.copy(localWireless = localWireless,
                localScreenLock = localScreenLock)
        }
    }

    suspend fun updateNetworkAndInternet(wifi: Boolean, dataServer: Boolean,
                                         roaming: Boolean, ethernet: String, vpn: String)
    = accessMutex.withLock {
        withContext(dispatcher) {
            val wireless = profile.localWireless.copy(
                wifi = wifi.toOnOrOff(),
                dataServer = dataServer.toOnOrOff(),
                roaming = roaming.toOnOrOff(),
                vpn = vpn
            )
            profile = profile.copy(localWireless = wireless)
        }
    }

    suspend fun updateSound(
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
    ) = accessMutex.withLock {
        withContext(dispatcher) {
            val system = profile.localSystem.copy(
                vibrateOnTouch = vibrateOnTouch,
                musicVolume = musicVolume,
                ringVolume = ringVolume,
                callVolume = callVolume,
                notificationVolume = notificationVolume,
                alarmVolume = alarmVolume,
            )
            profile = profile.copy(localSystem = system)
        }
    }

    suspend fun updateSystem(
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
    ) = accessMutex.withLock {
        withContext(dispatcher) {
            val system = profile.localSystem.copy(
                languages = languages,
                spellChecker = spellChecker,
                spellCheckLanguage = spellCheckLanguage,
                defaultSpellChecker = defaultSpellChecker,
                ntpServer = ntpServer
            )
            val date = profile.localDateTime.copy(
                useNetworkProvidedTime = useNetworkProvidedTime.toOnOrOff(),
                systemDate = systemDate,
                systemTime = systemTime,
                useNetworkProvidedTimeZone = useNetworkProvidedTimeZone.toOnOrOff(),
                timeZone = timeZone,
                use24hourFormat = use24hourFormat.toOnOrOff()
            )
            profile = profile.copy(localSystem = system, localDateTime = date)
        }
    }

    suspend fun create(password: String) = accessMutex.withLock {
        withContext(dispatcher) {
            val now = Calendar.getInstance().time
            val fileName = MakeBackupFileNameUseCase("modelName", "partNum", now)
            localDataSource.create(fileName, password, now)
            localDataSource.upsert(profile)
        }
    }

    fun getBackupFileDirectory() = localDataSource.getParentFileName()
}
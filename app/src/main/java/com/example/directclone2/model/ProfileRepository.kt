package com.example.directclone2.model

import com.example.directclone2.model.data.LocalProfile
import com.example.directclone2.model.data.toExternal
import com.example.directclone2.ui.screen.battery.BatteryUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
@FlowPreview
class ProfileRepository (
    private val localDataSource: ProfileDiskDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    //private val scope: CoroutineScope,
) {
    private val accessMutex = Mutex()
    private var profile = LocalProfile()

    fun observeAll() : Flow<BatteryUiState> {
        return localDataSource.observeAll().map {
            it.toExternal()
        }
    }

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
            localDataSource.upsert(profile)
        }
    }

    suspend fun updateConnectedDevices(nfc: Boolean, bluetooth: Boolean) = accessMutex.withLock {
        withContext(dispatcher) {
            val wireless = profile.localWireless.copy(
                nfc = nfc.toOnOrOff(),
                bluetooth = bluetooth.toOnOrOff()
            )
            profile = profile.copy(localWireless = wireless)
            localDataSource.upsert(profile)
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
            localDataSource.upsert(profile)
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
            localDataSource.upsert(profile)
        }
    }
    private fun Boolean.toEnableOrDisable(): String = if (this) "Enable" else "Disable"
    private fun Boolean.toOnOrOff(): String = if (this) "ON" else "OFF"
}
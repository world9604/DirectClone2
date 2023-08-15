package com.example.directclone2.model

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.directclone2.model.data.LocalBattery
import com.example.directclone2.model.data.LocalProfile
import com.example.directclone2.model.data.LocalSystem
import com.example.directclone2.model.data.toLocal
import com.example.directclone2.model.data.toExternal
import com.example.directclone2.ui.screen.battery.BatteryUiState
import com.example.directclone2.ui.screen.connecteddevices.ConnectedDevicesUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
@FlowPreview
class ProfileRepository (
    private val localDataSource: ProfileDiskDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    //private val scope: CoroutineScope,
) {
    val localProfile = LocalProfile()

    fun observeAll() : Flow<BatteryUiState> {
        return localDataSource.observeAll().map {
            it.toExternal()
        }
    }

    suspend fun update(smartCharging: String, batteryLowWarningLevel: String,
                       batteryCriticalWarningLevel: String) {
        withContext(dispatcher) {
            localDataSource.upsertBattery(smartCharging, batteryLowWarningLevel,
                batteryCriticalWarningLevel)
        }
    }
}
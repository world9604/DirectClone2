package com.example.directclone2.ui.screen.battery

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
import com.example.directclone2.ui.DirectCloneArgs
import kotlinx.coroutines.launch

class BatteryViewModel (
    private val repo: IProfileRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    companion object {
        const val TAG = "BatteryViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                val savedStateHandle = createSavedStateHandle()
                BatteryViewModel((application).container.profileRepository, savedStateHandle)
            }
        }
    }

    private val sharedProfileId =
        savedStateHandle.getStateFlow(DirectCloneArgs.PROFILE_ID_ARG, "")

    var uiState by mutableStateOf(BatteryUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            sharedProfileId.value.ifBlank {
                savedStateHandle[DirectCloneArgs.PROFILE_ID_ARG] = repo.create()
            }
            repo.updateBattery(
                sharedProfileId.value,
                uiState.smartCharging.name,
                getBatteryLowWarningLevelForText().toString(),
                getBatteryCriticalWarningLevelForText().toString())
        }
    }

    fun updateIsOkSmartChargingInfo() = viewModelScope.launch {
        uiState = uiState.copy(isOkSmartChargingInfo = !uiState.isOkSmartChargingInfo)
    }

    fun getBatteryLowWarningLevelForText(): Int {
        return (uiState.batteryLowWarningLevel * 100f).toInt()
    }

    fun getBatteryCriticalWarningLevelForText(): Int {
        return (uiState.batteryCriticalWarningLevel * 100f).toInt()
    }
}
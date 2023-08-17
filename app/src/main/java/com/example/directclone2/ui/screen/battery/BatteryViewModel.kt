package com.example.directclone2.ui.screen.battery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.directclone2.DirectCloneApplication
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.screen.main.MainViewModel
import kotlinx.coroutines.launch

class BatteryViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "BatteryViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                BatteryViewModel((application).container.profileRepository)
            }
        }
    }

    var uiState by mutableStateOf(BatteryUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            repo.updateBattery(
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
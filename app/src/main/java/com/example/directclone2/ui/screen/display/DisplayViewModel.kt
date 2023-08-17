package com.example.directclone2.ui.screen.display

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
import com.example.directclone2.ui.screen.battery.BatteryViewModel
import com.example.directclone2.ui.screen.connecteddevices.ConnectedDevicesViewModel
import kotlinx.coroutines.launch

class DisplayViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "DisplayViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                DisplayViewModel((application).container.profileRepository)
            }
        }
    }

    var uiState by mutableStateOf(DisplayUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            repo.updateDisplay(
                getScreenBrightnessForText().toString(),
                uiState.autoScreenRotate,
                uiState.adaptiveBrightness,
                uiState.systemFontSize.name,
                uiState.systemDisplaySize.name,
                uiState.touchSensitivity.name,
            )
        }
    }

    fun getScreenBrightnessForText(): Int {
        return (uiState.screenBrightness * 100f).toInt()
    }
}
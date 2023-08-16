package com.example.directclone2.ui.screen.sound

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.directclone2.DirectCloneApplication
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.screen.battery.BatteryViewModel
import kotlinx.coroutines.launch

class SoundViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "SoundViewModel"
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return BatteryViewModel(
                    (application as DirectCloneApplication).container.profileRepository) as T
            }
        }
    }

    var uiState by mutableStateOf(SoundUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            repo.updateSound(
                uiState.vibrateOnTouch.name,
                uiState.conversations.name,
                uiState.messages.name,
                uiState.calls.name,
                floatToString(uiState.musicVolume),
                floatToString(uiState.ringVolume),
                floatToString(uiState.callVolume),
                floatToString(uiState.notificationVolume),
                floatToString(uiState.alarmVolume),
                uiState.alarms,
                uiState.mediaSounds,
                uiState.touchSounds,
                uiState.reminders,
                uiState.calendarEvents,
                uiState.dialPadTones,
                uiState.screenLockingSounds,
                uiState.chargingSoundsAndVibration,
                uiState.advancedTouchSounds,
                uiState.touchVibration,
            )
        }
    }

    private fun floatToString(value: Float): String {
        return (value * 100f).toInt().toString()
    }
}
package com.example.directclone2.ui.screen.system

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.directclone2.DirectCloneApplication
import com.example.directclone2.model.IProfileRepository
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.DirectCloneArgs
import com.example.directclone2.ui.screen.battery.BatteryViewModel
import com.example.directclone2.ui.screen.sound.SoundViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SystemViewModel (
    private val repo: IProfileRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    companion object {
        const val TAG = "SystemViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                val savedStateHandle = createSavedStateHandle()
                SystemViewModel((application).container.profileRepository, savedStateHandle)
            }
        }
    }

    private val sharedProfileId =
        savedStateHandle.getStateFlow(DirectCloneArgs.PROFILE_ID_ARG, "")

    var uiState by mutableStateOf(SystemUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            sharedProfileId.value.ifBlank {
                savedStateHandle[DirectCloneArgs.PROFILE_ID_ARG] = repo.create()
            }
            repo.updateSystem(
                sharedProfileId.value,
                uiState.languages,
                uiState.spellChecker,
                uiState.spellCheckLanguage,
                uiState.defaultSpellChecker.name,
                //uiState.spellCheckPointerSpeed,
                uiState.useNetworkProvidedTime,
                uiState.systemDate,
                uiState.systemTime,
                uiState.useNetworkProvidedTimeZone,
                uiState.timeZone.toString(),
                uiState.use24hourFormat,
                uiState.ntpServer,
            )
        }
    }

    fun updateSystemDate(date: Long) = viewModelScope.launch {
        val systemDate = millisecondsToDateFormat(date)
        uiState = uiState.copy(systemDate = systemDate)
    }

    private fun millisecondsToDateFormat(currentTime: Long): String {
        val dateFormat = SimpleDateFormat("yyyy - MM - dd", Locale.getDefault())
        return dateFormat.format(Date(currentTime))
    }

    fun updateSystemTime(hour: Int, minute: Int, is24hour: Boolean) = viewModelScope.launch {
        val formattedTime = "%02d:%02d".format(hour, minute)
        uiState = uiState.copy(systemTime = formattedTime)
    }
}
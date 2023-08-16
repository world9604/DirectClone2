package com.example.directclone2.ui.screen.system

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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SystemViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "SystemViewModel"
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

    var uiState by mutableStateOf(SystemUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            repo.updateSystem(
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
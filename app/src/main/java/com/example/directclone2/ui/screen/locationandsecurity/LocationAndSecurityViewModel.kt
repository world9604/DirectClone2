package com.example.directclone2.ui.screen.locationandsecurity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.directclone2.DirectCloneApplication
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.screen.battery.BatteryViewModel
import kotlinx.coroutines.launch

class LocationAndSecurityViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "LocationAndSecurityViewModel"
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

    var uiState by mutableStateOf(LocationAndSecurityUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            repo.updateScreenLock(
                uiState.useLocation,
                uiState.currentScreenLockPinOrPassword,
                uiState.screenLock.name,
                uiState.screenLockPin.ifEmpty { uiState.screenLockPassword },
                uiState.screenLockMessage,
                uiState.lockAfterScreenTimeout.name,
                uiState.powerButtonInstantlyLocks.name
            )
        }
    }

    fun updateScreenLock(value: Int) = viewModelScope.launch {
        uiState = when (value) {
            LocationAndSecurityUiState.ScreenLock.None.resIdOfTitle -> {
                uiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.None,
                        screenLockPin = "", screenLockPassword = "")
            }
            LocationAndSecurityUiState.ScreenLock.Swipe.resIdOfTitle -> {
                uiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.Swipe,
                    screenLockPin = "", screenLockPassword = "")
            }
            LocationAndSecurityUiState.ScreenLock.Pin.resIdOfTitle -> {
                uiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.Pin,
                    screenLockPassword = "")
            }
            LocationAndSecurityUiState.ScreenLock.Password.resIdOfTitle -> {
                uiState.copy(screenLock = LocationAndSecurityUiState.ScreenLock.Password,
                    screenLockPin = "")
            }
            else -> uiState
        }
    }
}
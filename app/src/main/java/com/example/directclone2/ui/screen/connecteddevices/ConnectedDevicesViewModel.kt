package com.example.directclone2.ui.screen.connecteddevices

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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConnectedDevicesViewModel (
    private val repo: IProfileRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    companion object {
        const val TAG = "ConnectedDevicesViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                val savedStateHandle = createSavedStateHandle()
                ConnectedDevicesViewModel((application).container.profileRepository, savedStateHandle)
            }
        }
    }

    private val sharedProfileId =
        savedStateHandle.getStateFlow(DirectCloneArgs.PROFILE_ID_ARG, "")

    var uiState by mutableStateOf(ConnectedDevicesUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            sharedProfileId.value.ifBlank {
                savedStateHandle[DirectCloneArgs.PROFILE_ID_ARG] = repo.create()
            }
            repo.updateConnectedDevices(
                sharedProfileId.value!!,
                uiState.nfc,
                uiState.bluetooth
            )
        }
    }
}
package com.example.directclone2.ui.screen.networkandinternet

import android.util.Log
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
import com.example.directclone2.ui.screen.main.MainViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkAndInternetViewModel (
    private val repo: IProfileRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    companion object {
        //const val TAG = "NetworkAndInternetViewModel"
        const val TAG = "DirectCloneAppNavGraph"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                val savedStateHandle = createSavedStateHandle()
                NetworkAndInternetViewModel((application).container.profileRepository, savedStateHandle)
            }
        }
    }

    private val sharedProfileId =
            savedStateHandle.getStateFlow(DirectCloneArgs.PROFILE_ID_ARG, "")

    var uiState by mutableStateOf(NetworkAndInternetUiState())
        private set

    fun <T: Any> update(field: String, value: T) = viewModelScope.launch {
        uiState = uiState.update(field, value)
        Log.d(TAG, "sharedProfileId 1: ${sharedProfileId.value}")
        viewModelScope.launch {
            sharedProfileId.value.ifBlank {
                savedStateHandle[DirectCloneArgs.PROFILE_ID_ARG] = repo.create()
                Log.d(TAG, "sharedProfileId 2: ${sharedProfileId.value}")
            }
            repo.updateNetworkAndInternet(
                sharedProfileId.value,
                uiState.wifi,
                //uiState.savedNetworks,
                uiState.dataServer,
                uiState.roaming,
                uiState.ethernet,
                uiState.vpn,
            )
        }
    }
}
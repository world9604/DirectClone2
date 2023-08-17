package com.example.directclone2.ui.screen.networkandinternet

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
import com.example.directclone2.ui.screen.main.MainViewModel
import kotlinx.coroutines.launch

class NetworkAndInternetViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "NetworkAndInternetViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                NetworkAndInternetViewModel((application).container.profileRepository)
            }
        }
    }

    var uiState by mutableStateOf(NetworkAndInternetUiState())
        private set

    fun <T: Any> update(field: String, value: T) = viewModelScope.launch {
        uiState = uiState.update(field, value)
        viewModelScope.launch {
            repo.updateNetworkAndInternet(
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
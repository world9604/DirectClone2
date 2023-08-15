package com.example.directclone2.ui.screen.networkandinternet

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

class NetworkAndInternetViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "NetworkAndInternetViewModel"
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

    var uiState by mutableStateOf(NetworkAndInternetUiState())
        private set

    fun <T: Any> update(field: String, value: T) = viewModelScope.launch {
        uiState = uiState.update(field, value)
    }
}
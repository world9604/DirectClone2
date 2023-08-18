package com.example.directclone2.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.directclone2.DirectCloneApplication
import com.example.directclone2.model.ProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel (
    private val repo: ProfileRepository
): ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DirectCloneApplication)
                MainViewModel((application).container.profileRepository)
            }
        }
    }

    var uiState by mutableStateOf(MainUiState())
        private set

    fun <T: Any> update(field: String, value: T) {
        uiState = uiState.update(field, value)
    }

    fun save() = viewModelScope.launch {
        repo.create(uiState.password)
        delay(3000)
        uiState = uiState.copy(isCompletedCreateBackupFile = true)
    }

    val tabs = MainUiState.TabContent.values()

    var currentTab by mutableStateOf(MainUiState.TabContent.Backup)
        private set

    fun updateTab(tab: MainUiState.TabContent) {
        currentTab = tab
    }

    private val _appsForBackup = getAppItems().toMutableStateList()
    val appsForBackup: List<AppItem>
        get() = _appsForBackup

    private fun getAppItems() = listOf (
        AppItem(appName = "Program Buttons", isPreInstalledApp = true),
        AppItem(appName = "Scan Settings", isPreInstalledApp = true),
        AppItem(appName = "Settings", isPreInstalledApp = true),
        AppItem(appName = "EmKiosk", isPreInstalledApp = false),
        AppItem(appName = "EmKiosk Config", isPreInstalledApp = false),
        AppItem(appName = "EmKiosk Config2", isPreInstalledApp = false),
        AppItem(appName = "EmKiosk Config3", isPreInstalledApp = false),
        AppItem(appName = "EmKiosk Config4", isPreInstalledApp = false),
        AppItem(appName = "EmKiosk Config5", isPreInstalledApp = false)
    )

    fun haveAtLeastOneAppToBackUp() = appsForBackup.any { it.selected }

    fun matchPasswordAndConfirmPassword() = uiState.run {
        password.isNotEmpty() && (password == confirmPassword)
    }

    fun remove(item: AppItem) = viewModelScope.launch {
        _appsForBackup.remove(item)
    }

    fun changeAppSelected(item: AppItem, selected: Boolean) = viewModelScope.launch {
        appsForBackup.find { it.appName == item.appName }?.let {
            it.selected = selected
        }
    }

    fun getNumOfSelectedApps() = appsForBackup.count{ it.selected }

    fun openBackupResultDialog() {
        if (uiState.usePassword) {
            uiState = uiState.copy(openSetPasswordDialog = true)
        } else {
            uiState = uiState.copy(openBackupResultDialog = true)
            save()
        }
    }

    init {
        initBackupFileSaveLocation()
    }

    private fun initBackupFileSaveLocation() {
        uiState = uiState.copy(parentSaveDirectoryForBackupFile = repo.getBackupDirectory())
    }

    fun initIsCompletedCreateBackupFile() {
        uiState = uiState.copy(isCompletedCreateBackupFile = false)
    }
}
package com.example.directclone2.ui.screen.main

data class MainUiState(
    val backupFileName: String = "",
    val isInternalStorage: Boolean = false,
    val backupFileSaveLocation: String = "",
    val usePassword: Boolean = false,
    val password: String = "",
    val confirmPassword: String = "",
    val openBackupDialog: Boolean = false,
    val openSetPasswordDialog: Boolean = false,
    val openBackupResultDialog: Boolean = false,
    val isCompletedCreateBackupFile: Boolean = false,
)
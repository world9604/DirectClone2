package com.example.directclone2.ui.screen.main

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.directclone2.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class MainUiState(
    val backupFiles: List<BackupFile> = listOf(BackupFile(), BackupFile()),
    val isInternalStorage: Boolean = true,
    val parentSaveDirectoryForBackupFile: String = "",
    val usePassword: Boolean = false,
    val password: String = "",
    val confirmPassword: String = "",
    val openBackupDialog: Boolean = false,
    val openSetPasswordDialog: Boolean = false,
    val openBackupResultDialog: Boolean = false,
    val isCompletedCreateBackupFile: Boolean = false,
) {
    enum class TabContent(val index: Int, @StringRes val textRes: Int) {
        Backup(0, R.string.backup_content_title), Sync(1, R.string.sync_content_title)
    }
}

fun <T: Any> MainUiState.update(fieldToBeUpdated: String, value: T): MainUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as MainUiState
}

data class BackupFile(
    val file: File = File(""),
    val createdDate: Date = Date(),
    val password: String = "",
) {
    val name: String by lazy { file.name }
    val savedLocation: String by lazy { file.absolutePath }
    val formattedCreatedDate: String by lazy { SimpleDateFormat("yyyy/MM/dd HH:mm").format(createdDate) }
}

data class AppItem (
    val packageName: String = "",
    val appName: String = "",
    val isPreInstalledApp: Boolean = false,
    val initialSelected: Boolean = false
) {
    var selected: Boolean by mutableStateOf(initialSelected)
}
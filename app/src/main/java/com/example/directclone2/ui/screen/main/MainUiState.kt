package com.example.directclone2.ui.screen.main

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.directclone2.R
import com.example.directclone2.model.data.LocalBackupApp
import com.example.directclone2.model.data.toLocal
import com.example.directclone2.ui.Screen
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class MainUiState(
    val selectedId: String = "",
    val fileName: String = "",
    val backupFiles: List<BackupFile> = listOf(BackupFile(), BackupFile()),
    val appsForBackup: List<AppItem> = listOf(),
    val isInternalStorage: Boolean = true,
    val directoryForBackup: String = "",
    val usePassword: Boolean = false,
    val password: String = "",
    val passwordForRestoreOrClone: String = "",
    val confirmPassword: String = "",
    val openBackupDialog: Boolean = false,
    val openSetPasswordDialog: Boolean = false,
    val openBackupResultDialog: Boolean = false,
    val isCompletedCreateBackupFile: Boolean = false,
    val isMatchedPassword: Boolean = false,
    val openRestoreDialog: Boolean = false,
    val openDirectCloneDialog: Boolean = false,
    val openDeleteDialog: Boolean = false,
    val isRestoring: Boolean = false
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
    val profileId: String = "",
    val file: File = File(""),
    val createdDate: Date = Date(),
    val password: String = "",
) {
    val name: String by lazy { file.name }
    val savedLocation: String by lazy { file.absolutePath }
    val formattedCreatedDate: String by lazy { SimpleDateFormat("yyyy/MM/dd HH:mm").format(createdDate) }
}

fun AppItem.startActivity(context: Context, navController: NavController) {
    when (appName) {
        "Settings" -> { navController.navigate(Screen.Settings.route) }
        else -> {
            val packageName = this.packageName.ifBlank {
                throw PackageNotExistException(this.packageName)
            }
            val activityName = this.activityName.ifBlank {
                throw ActivityNotExistException(this.activityName)
            }
            context.startActivity(
                Intent().apply {
                    action = Intent.ACTION_MAIN
                    component = ComponentName(packageName, activityName)
                }
            )
        }
    }
}

class PackageNotExistException(
    packageName: String,
): Exception("This package($packageName) does not exist.")

class ActivityNotExistException(
    activityName: String,
): Exception("This activity($activityName) does not exist.")

class AppItem(
    val appName: String,
    val activityName: String = "",
    val packageName: String = "",
    val sourceDir: String = "",
    val isPreInstalledApp: Boolean = false,
    private val initialSelected: Boolean = false) {
    var selected: Boolean by mutableStateOf(initialSelected)
}
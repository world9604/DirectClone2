package com.example.directclone2.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.components.ButtonInCommonUi
import com.example.directclone2.ui.components.CardDividerInCommonUi
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CheckBoxInCommonUi
import com.example.directclone2.ui.components.OutlinedTextFieldInCommonUi
import java.io.File
import java.util.Locale

@Composable
fun BackupContent(
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel(factory = MainViewModel.Factory),
    onAppClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            var isClickPreInstallAppBtn by rememberSaveable { mutableStateOf(false) }
            var isClickedInstalledAppBtn by rememberSaveable { mutableStateOf(false) }
            vm.appsForBackup.filter { it.isPreInstalledApp }?.let {
                AppsForBackupList(
                    title = "Enterprise Apps and System settings",
                    subTitle = "(Backup File Selection)",
                    apps = it,
                    clicked = isClickPreInstallAppBtn,
                    onClick = { isClickPreInstallAppBtn = !isClickPreInstallAppBtn },
                    vm = vm,
                    onAppClicked = onAppClicked)
            }
            vm.appsForBackup.filter { !it.isPreInstalledApp }?.let {
                AppsForBackupList(
                    title = "installed Apps",
                    subTitle = "(Automatic installation when adding APK file to Direct Clone > Apps)",
                    apps = it,
                    clicked = isClickedInstalledAppBtn,
                    onClick = { isClickedInstalledAppBtn = !isClickedInstalledAppBtn },
                    vm = vm,
                    onAppClicked = onAppClicked)
            }
        }
        if (vm.uiState.openBackupDialog) BackupDialog(vm)
        if (vm.uiState.openSetPasswordDialog) SetPasswordDialog(vm)
        if (vm.uiState.openBackupResultDialog) BackupResultDialog(vm)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        ButtonInCommonUi(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(16.dp),
            enabled = vm.haveAtLeastOneAppToBackUp(),
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                vm.update("openBackupDialog", true)
                vm.initIsCompletedCreateBackupFile()
            }
        ) {
            Text(
                text = stringResource(R.string.backup_content_backup_btn,
                    vm.getNumOfSelectedApps()).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
private fun BackupResultDialog(vm: MainViewModel) {
    data class MultiComponent(val x: String, val y: String, val z: String, val a: Color)
    val (title, subTitle, btnText, btnColor) = when (vm.uiState.isCompletedCreateBackupFile) {
        true -> MultiComponent(
            stringResource(R.string.backup_content_backup_result_dialog_title_label),
            stringResource(R.string.backup_content_backup_result_dialog_sub_title_label),
            stringResource(R.string.backup_content_backup_result_dialog_btn_text_label).uppercase(Locale.getDefault()),
            MaterialTheme.colorScheme.primary)
        false -> MultiComponent(
            stringResource(R.string.backup_content_backup_result_dialog_title_label_when_progress),
            stringResource(R.string.backup_content_backup_result_dialog_sub_title_label_when_progress),
            stringResource(R.string.backup_content_backup_result_dialog_btn_text_label_when_progress).uppercase(Locale.getDefault()),
            MaterialTheme.colorScheme.onTertiaryContainer)
    }

    Dialog(onDismissRequest = {}) {
        Box(
            Modifier.background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 42.dp, bottom = 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        text = title)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 28.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(subTitle.isBlank()) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary)
                    } else {
                        Text(
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            text = subTitle)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonInCommonUi(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {vm.update("openBackupResultDialog", false)},
                        containerColor = btnColor
                    ) {
                        Text(style = MaterialTheme.typography.displayLarge,
                            text = btnText)
                    }
                }
            }
        }
    }
}

@Composable
private fun SetPasswordDialog(vm: MainViewModel) {
    Dialog(onDismissRequest = {}) {
        Box(
            Modifier.background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp, bottom = 16.dp, start = 13.45.dp, end = 13.45.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(Modifier.padding(bottom = 20.dp)) {
                    Text(
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        text = "Set Password")
                }
                Row(Modifier.padding(bottom = 8.dp)) {
                    OutlinedTextFieldInCommonUi(
                        modifier = Modifier.fillMaxWidth(),
                        value = vm.uiState.password,
                        onValueChange = {vm.update("password", it)},
                        placeholderText = "Enter password.")
                }
                Row(Modifier.padding(bottom = 28.dp)) {
                    OutlinedTextFieldInCommonUi(
                        modifier = Modifier.fillMaxWidth(),
                        value = vm.uiState.confirmPassword,
                        onValueChange = {vm.update("confirmPassword", it)},
                        placeholderText = "Confirm password.")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ButtonInCommonUi(
                        modifier = Modifier
                            .height(48.dp)
                            .width(144.dp)
                            .padding(end = 8.dp),
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        onClick = { vm.update("openSetPasswordDialog", false) }
                    ) {
                        Text(
                            style = MaterialTheme.typography.displayLarge,
                            text = "CANCEL".uppercase(Locale.getDefault()))
                    }
                    ButtonInCommonUi(
                        modifier = Modifier
                            .height(48.dp)
                            .width(144.dp),
                        enabled = vm.matchPasswordAndConfirmPassword(),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            vm.update("openSetPasswordDialog", false)
                            vm.update("openBackupResultDialog", true)
                            vm.save()
                        }
                    ) {
                        Text(
                            style = MaterialTheme.typography.displayLarge,
                            text = "OK".uppercase(Locale.getDefault()))
                    }
                }
            }
        }
    }
}

@Composable
private fun BackupDialog(vm: MainViewModel) {
    Dialog(onDismissRequest = {}) {
        Box(
            Modifier.background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 13.45.dp, end = 13.45.dp, top = 16.dp, bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        text = "Backup")
                }
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "Would you like to start a backup?")
                Column(
                    modifier = Modifier.padding(top = 12.dp),
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "• File name : ")
                    Row(Modifier.padding(start = 24.dp, top = 3.dp)) {
                        Text(
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            //text = "• ${vm.uiState.backupFile.name}")
                            text = "• ")
                    }
                }
                Column(
                    modifier = Modifier.padding(top = 12.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "• Storage Location Selection : ")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, top = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Row(
                            modifier = Modifier.padding(end = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 6.dp),
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.secondary,
                                    unselectedColor = MaterialTheme.colorScheme.tertiary),
                                selected = vm.uiState.isInternalStorage,
                                onClick = {vm.update("isInternalStorage", true)})
                            Text(
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = "Internal storage")
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 6.dp),
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.secondary,
                                    unselectedColor = MaterialTheme.colorScheme.tertiary),
                                selected = !vm.uiState.isInternalStorage,
                                onClick = {vm.update("isInternalStorage", false)})
                            Text(
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = "External storage")
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(top = 12.dp),
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "• Save to : ")
                    Row(Modifier.padding(start = 24.dp, top = 3.dp)) {
                        Text(
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            text = "• ${vm.uiState.parentSaveDirectoryForBackupFile}")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "• Set password : ")
                    CheckBoxInCommonUi(
                        checked = vm.uiState.usePassword,
                        onCheckedChange = { vm.update("usePassword", it) })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonInCommonUi(
                        modifier = Modifier
                            .height(48.dp)
                            .width(144.dp),
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        onClick = {vm.update("openBackupDialog", false)}
                    ) {
                        Text(
                            style = MaterialTheme.typography.displayLarge,
                            text = "CANCEL".uppercase(Locale.getDefault()))
                    }
                    ButtonInCommonUi(
                        modifier = Modifier
                            .height(48.dp)
                            .width(144.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            vm.update("openBackupDialog", false)
                            vm.openBackupResultDialog() }
                    ) {
                        Text(
                            style = MaterialTheme.typography.displayLarge,
                            text = "OK".uppercase(Locale.getDefault()))
                    }
                }
            }
        }
    }
}

@Composable
private fun AppsForBackupList(
    title: String,
    subTitle: String,
    onClick: () -> Unit,
    apps: List<AppItem>,
    clicked: Boolean = true,
    vm: MainViewModel,
    onAppClicked: () -> Unit = {},
)  {
    AppsForBackupToggleCard(title, subTitle, clicked, onClick)

    if (!clicked) return

    CardViewInCommonUi(modifier = Modifier.fillMaxWidth()) {
        for (app in apps) {
            AppsForBackupItem(
                app = app,
                vm = vm,
                onAppClicked = onAppClicked
            )
        }
    }
}

@Composable
fun AppsForBackupItem(
    app: AppItem,
    vm: MainViewModel,
    onAppClicked: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 11.dp)
            .clickable { onAppClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            CheckBoxInCommonUi(
                checked = app.selected,
                onCheckedChange = {vm.changeAppSelected(app, it)})
            Text(text = app.appName)
        }
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Select to back up Program Buttons"
        )
    }
    CardDividerInCommonUi(Modifier.fillMaxWidth())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppsForBackupToggleCard(
    title: String,
    subTitle: String,
    clicked: Boolean,
    onClick: () -> Unit,
) {
    var backgroundColor: Color
    var titleTextStyle: TextStyle
    var subTitleTextStyle: TextStyle
    var iconVector: ImageVector

    if (clicked) {
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant
        titleTextStyle = MaterialTheme.typography.labelLarge
        subTitleTextStyle = MaterialTheme.typography.labelSmall
        iconVector = Icons.Filled.ExpandLess
    } else {
        backgroundColor = MaterialTheme.colorScheme.secondary
        titleTextStyle = MaterialTheme.typography.labelLarge
        subTitleTextStyle = MaterialTheme.typography.labelSmall
        iconVector = Icons.Filled.ExpandMore
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = title, style = titleTextStyle)
                    Text(text = subTitle, style = subTitleTextStyle)
                }
                Icon(imageVector = iconVector, contentDescription = null)
            }
        }
    }
}

@Preview(group = "Work", heightDp = 800, showBackground = true)
@Composable
fun BackupContentPreview() {
    BackupContent(vm = MainViewModel(ProfileRepository.getInstance(ProfileDiskDataSource.getInstance(File("")))))
}
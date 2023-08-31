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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.FakeProfileRepository
import com.example.directclone2.ui.SettingViewModel
import com.example.directclone2.ui.components.ButtonInCommonUi
import com.example.directclone2.ui.components.CardDividerInCommonUi
import com.example.directclone2.ui.components.OutlinedTextFieldInCommonUi
import com.example.directclone2.ui.components.myiconpack.MyIconPack
import com.example.directclone2.ui.components.SecondaryToggleCardInCommonUi
import com.example.directclone2.ui.components.myiconpack.IconCloneLock
import com.example.directclone2.ui.components.myiconpack.IconDelete
import com.example.directclone2.ui.components.myiconpack.IconRestore
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun SyncContent(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(factory = SettingViewModel.Factory),
) {
    val backupFiles = remember { vm.mainUiState.backupFiles }
    val hasBackupFile = backupFiles.isNotEmpty()
    var clickedOffset by remember { mutableStateOf(Offset.Zero) }
    var isClickedBackupFile by remember { mutableStateOf(false) }
    var clickedBackupFile by remember { mutableStateOf(BackupFile()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                text = stringResource(R.string.empty_backup_item))
        }
        if (hasBackupFile) {
            var openStorageCard by remember { mutableStateOf(true) }
            SecondaryToggleCardInCommonUi(
                modifier = Modifier,
                text = "Internal Storage",
                isOpen = openStorageCard,
                onClickItem = {openStorageCard = !openStorageCard}
            ) { modifier ->
                backupFiles.forEach { file ->
                    Column(
                        modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 11.dp)
                            .clickable {
                                clickedBackupFile = file
                                isClickedBackupFile = !isClickedBackupFile }
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                modifier = Modifier.weight(weight = 1f, fill = false),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                text = "${file.name}"
                            )
                            IconButton(
                                modifier = Modifier
                                    .onGloballyPositioned { clickedOffset = it.positionInRoot() }
                                    .then(Modifier.size(24.dp)),
                                onClick = { }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Backup File Setting",
                                    tint = MaterialTheme.colorScheme.onTertiary)
                            }
                        }
                        Text(
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            text = "${file.formattedCreatedDate}"
                        )
                    }
                    CardDividerInCommonUi(modifier = modifier)
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    text = "Backup File Not Found."
                )
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonInCommonUi(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                enabled = hasBackupFile,
                onClick = {vm.save()}
            ) {
                Text(
                    text = stringResource(R.string.sync_content_start_direct_clone_service_btn)
                        .uppercase(Locale.getDefault()),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleLarge)
            }
        }
    }

    if(isClickedBackupFile) BackupFileOptionBox(
        clickedOffset
    ) { restoreDialog, directCloneDialog, deleteDialog ->
        vm.updateMain("restoreDialog", restoreDialog)
        vm.updateMain("openDirectCloneDialog", directCloneDialog)
        vm.updateMain("openDeleteDialog", deleteDialog)
    }

    if(vm.mainUiState.openRestoreDialog) RestoreDialog(clickedBackupFile, vm) {
        vm.updateMain("openRestoreDialog", false)
    }
    if(vm.mainUiState.openDirectCloneDialog) DirectCloneDialog(clickedBackupFile, vm) {
        vm.updateMain("openDirectCloneDialog", false)
    }
    if(vm.mainUiState.openDeleteDialog) DeleteDialog(clickedBackupFile)
}

@Composable
fun BackupFileOptionBox(
    offset: Offset,
    whichDialogClicked: (
        restoreDialog: Boolean, directCloneDialog: Boolean, deleteDialog: Boolean) -> Unit
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    Box(
        Modifier
            .fillMaxSize()
            .offset {
                IntOffset(
                    x = offset.x.roundToInt() - size.width,
                    y = offset.y.roundToInt() - (size.height / 2)
                )
            },
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(4.dp)
                )
                .onGloballyPositioned { size = it.size }
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .clickable { whichDialogClicked(true, false, false) },
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = MyIconPack.IconRestore,
                    contentDescription = "Restore Icon",
                    tint = MaterialTheme.colorScheme.onBackground)
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Restore")
            }
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .clickable { whichDialogClicked(false, true, false) },
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = MyIconPack.IconCloneLock,
                    contentDescription = "CloneLock Icon",
                    tint = MaterialTheme.colorScheme.onBackground)
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Direct Clone")
            }
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                    .clickable { whichDialogClicked(false, false, true) },
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = MyIconPack.IconDelete,
                    contentDescription = "Delete Icon",
                    tint = MaterialTheme.colorScheme.onBackground)
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Delete")
            }
        }
    }
}

@Composable
fun RestoreDialog(
    backupFile: BackupFile,
    vm: SettingViewModel,
    dismissRequest: () -> Unit
) {
    val isMatchedPassword = remember { vm.mainUiState.isMatchedPassword }
    val subTitle = when {
        (isMatchedPassword) -> "This file is encrypted"
        else -> "When the restoration is complete,\n" +
                "it will restart. Would you like to start it?"
    }

    Dialog(onDismissRequest = { dismissRequest() }) {
        Box(
            Modifier.background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp))
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 42.dp, bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        text = "Restore")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        text = subTitle)
                }
                Row(Modifier.padding(top = 12.dp)) {
                    if (isMatchedPassword) {
                        Column {
                            Text(
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                text = "• Restoration File Location : ")
                            Text(
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = "• ${backupFile.file.absolutePath}")
                        }
                    } else {
                        OutlinedTextFieldInCommonUi(
                            modifier = Modifier.fillMaxWidth(),
                            value = vm.mainUiState.passwordForRestoreOrClone,
                            onValueChange = { vm.updateMain("passwordForRestoreOrClone", it) },
                            placeholderText = "Enter password.")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 28.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ButtonInCommonUi(
                        modifier = Modifier
                            .height(48.dp)
                            .width(144.dp)
                            .padding(end = 8.dp),
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        onClick = { dismissRequest() }
                    ) {
                        Text(
                            style = MaterialTheme.typography.displayLarge,
                            text = "CANCEL".uppercase(Locale.getDefault()))
                    }
                    ButtonInCommonUi(
                        modifier = Modifier
                            .height(48.dp)
                            .width(144.dp),
                        enabled = false,
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = { if (isMatchedPassword) vm.restore() else vm.checkPassword() }
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
fun DirectCloneDialog(backupFile: BackupFile, vm: SettingViewModel, dismissRequest: () -> Unit) {

}

@Composable
fun DeleteDialog(backupFile: BackupFile) {

}

@Preview(group="Test", heightDp = 800, showBackground = true)
@Composable
fun SyncPreview() {
    SyncContent(vm = SettingViewModel(FakeProfileRepository(), SavedStateHandle()))
}
package com.example.directclone2.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.FakeProfileRepository
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.NavigationDestination
import com.example.directclone2.ui.components.ButtonInCommonUi
import com.example.directclone2.ui.components.CardDividerInCommonUi
import com.example.directclone2.ui.components.SecondaryToggleCardInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi
import java.io.File
import java.util.Locale

@Composable
fun SyncContent(
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel(factory = MainViewModel.Factory),
) {
    var hasBackupFile = vm.uiState.isCompletedCreateBackupFile
    hasBackupFile = true
    Column(
        modifier = modifier
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
            SecondaryToggleCardInCommonUi(
                modifier = modifier,
                text = "Internal Storage"
            ) { modifier ->
                vm.uiState.backupFiles.forEach { file ->
                    Column(
                        modifier = modifier.padding(start = 16.dp, end = 16.dp,
                            top = 16.dp, bottom = 11.dp),
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                text = "${file.name}")
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Backup File Setting")
                        }
                        Text(
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            text = "${file.formattedCreatedDate}")
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
                    text = "Backup File Not Found.")
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            ButtonInCommonUi(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
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
}

@Preview(group="Test", heightDp = 800, showBackground = true)
@Composable
fun SyncPreview() {
    SyncContent(vm = MainViewModel(FakeProfileRepository(), SavedStateHandle()))
}
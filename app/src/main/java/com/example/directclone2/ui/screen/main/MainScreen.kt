package com.example.directclone2.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import java.io.File

@Preview(group="Work", showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(
        vm = SettingViewModel(
            ProfileRepository(ProfileDiskDataSource(File("/storage/emulated/0/Profile.json")))))
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(),
    onAppClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Column {
                Tabs(vm)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        when (vm.currentTab) {
            SettingViewModel.TabContent.Backup -> {
                BackupContent(
                    modifier = modifier.padding(innerPadding),
                    vm = vm,
                    onAppClicked = onAppClicked
                )
            }
            SettingViewModel.TabContent.Sync -> {
                SyncContent(
                    modifier = modifier.padding(innerPadding),
                    vm = vm,
                    //apps = vm.apps,
                )
            }
        }
    }
}
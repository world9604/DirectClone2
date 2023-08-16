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
import com.example.directclone2.ui.screen.battery.BatteryViewModel
import java.io.File

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel(factory = MainViewModel.Factory),
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
            MainUiState.TabContent.Backup -> {
                BackupContent(
                    modifier = modifier.padding(innerPadding),
                    vm = vm,
                    onAppClicked = onAppClicked
                )
            }
            MainUiState.TabContent.Sync -> {
                SyncContent(
                    modifier = modifier.padding(innerPadding),
                    vm = vm,
                )
            }
        }
    }
}

@Preview(group="Work", showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(vm = MainViewModel(ProfileRepository(ProfileDiskDataSource(File("/storage/emulated/0/Profile.json")))))
}
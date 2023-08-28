package com.example.directclone2.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.model.FakeProfileRepository
import com.example.directclone2.ui.SettingViewModel


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(factory = SettingViewModel.Factory),
    onAppClicked: (app: AppItem) -> Unit = {}
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
                    onAppClicked = onAppClicked)
            }
            MainUiState.TabContent.Sync -> {
                SyncContent(
                    modifier = modifier.padding(innerPadding),
                    vm = vm)
            }
        }
    }
}

@Preview(group="Work", showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(vm = SettingViewModel(FakeProfileRepository(), SavedStateHandle()))
}
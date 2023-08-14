package com.example.directclone2.view.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.viewmodel.SettingViewModel
import com.example.directclone2.view.AppItem
import com.example.directclone2.view.ui.components.CardDividerInCommonUi
import com.example.directclone2.view.ui.components.CheckBoxInCommonUi
import java.io.File
import java.util.Locale

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
package com.example.directclone2.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.viewmodel.SettingViewModel
import com.example.directclone2.view.ui.components.AppNavigation
import java.io.File

@Composable
fun SettingsScreen (
    vm: SettingViewModel = viewModel(),
    modifier: Modifier = Modifier,
    onBackupClicked: () -> Unit = {},
    onSyncClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    onSoundClicked: () -> Unit = {},
    onBatteryClicked: () -> Unit = {},
    onLocationAndSecurityClicked: () -> Unit = {},
    onConnectedDevicesClicked: () -> Unit = {},
    onAppsClicked: () -> Unit = {},
    onDisplayClicked: () -> Unit = {},
    onSystemClicked: () -> Unit = {},
    onNetworkAndInternetClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        for ((index, item) in vm.settingItems.withIndex()) {
            var newModifier = if (index == 0) Modifier.padding(top = 7.dp) else Modifier

            SettingsContent (
                modifier = newModifier.background(MaterialTheme.colorScheme.surface),
                name = item.first,
                onSettingClicked = {
                    when(item.second) {
                        AppNavigation.Backup -> onBackupClicked()
                        AppNavigation.Sync -> onSyncClicked()
                        AppNavigation.Settings -> onSettingsClicked()
                        AppNavigation.Sound -> onSoundClicked()
                        AppNavigation.Battery -> onBatteryClicked()
                        AppNavigation.LocationAndSecurity -> onLocationAndSecurityClicked()
                        AppNavigation.ConnectedDevices -> onConnectedDevicesClicked()
                        AppNavigation.Apps -> onAppsClicked()
                        AppNavigation.Display -> onDisplayClicked()
                        AppNavigation.System -> onSystemClicked()
                        AppNavigation.NetworkAndInternet -> onNetworkAndInternetClicked()
                    }
                },
            )
        }
    }
}

@Composable
fun SettingsContent (
    modifier: Modifier,
    name: String,
    onSettingClicked: () -> Unit = {}
) {
    Column(modifier
        .background(MaterialTheme.colorScheme.surface)
        .clickable { onSettingClicked() },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 9.dp, bottom = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                text = name
            )
            Icon(
                modifier = Modifier.size(width = 24.dp, height = 24.dp),
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "ArrowForwardIos icon",
                tint = MaterialTheme.colorScheme.onTertiary)
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.tertiary,
            thickness = 1.dp
        )
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun SettingsPreview() {
    SettingsScreen(
        vm = SettingViewModel(
            ProfileRepository(ProfileDiskDataSource(File("/storage/emulated/0/Profile.json")))
        ))
}
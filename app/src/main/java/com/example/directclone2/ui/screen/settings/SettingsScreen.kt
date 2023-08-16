package com.example.directclone2.ui.screen.settings

import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.directclone2.ui.screen.main.MainUiState.SettingsRes
import com.example.directclone2.ui.screen.main.MainViewModel

@Composable
fun SettingsScreen (
    vm: MainViewModel = viewModel(factory = MainViewModel.Factory),
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        SettingsRes.values().forEachIndexed { index, item ->
            var newModifier = if (index == 0) Modifier.padding(top = 7.dp) else Modifier
            SettingsContent (
                modifier = newModifier.background(MaterialTheme.colorScheme.surface),
                name = item.title,
                onSettingClicked = {
                    when(item) {
                        SettingsRes.Backup -> navController.navigate(SettingsRes.Backup.name)
                        SettingsRes.Sync -> navController.navigate(SettingsRes.Sync.name)
                        SettingsRes.Settings -> navController.navigate(SettingsRes.Settings.name)
                        SettingsRes.Sound -> navController.navigate(SettingsRes.Sound.name)
                        SettingsRes.Battery -> navController.navigate(SettingsRes.Battery.name)
                        SettingsRes.LocationAndSecurity -> navController.navigate(SettingsRes.LocationAndSecurity.name)
                        SettingsRes.ConnectedDevices -> navController.navigate(SettingsRes.ConnectedDevices.name)
                        SettingsRes.Apps -> navController.navigate(SettingsRes.Apps.name)
                        SettingsRes.Display -> navController.navigate(SettingsRes.Display.name)
                        SettingsRes.System -> navController.navigate(SettingsRes.System.name)
                        SettingsRes.NetworkAndInternet -> navController.navigate(SettingsRes.NetworkAndInternet.name)
                    }
                },
            )
        }
    }
}

@Composable
fun SettingsContent (
    modifier: Modifier,
    @StringRes name: Int,
    onSettingClicked: () -> Unit = {}
) {
    Column(
        modifier
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
                text = stringResource(name)
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
    SettingsScreen()
}
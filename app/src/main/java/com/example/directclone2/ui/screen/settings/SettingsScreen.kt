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
import com.example.directclone2.ui.Screen
import com.example.directclone2.ui.screens


@Composable
fun SettingsScreen (
    modifier: Modifier = Modifier,
    navigate: (Screen) -> Unit = {}
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        screens.forEachIndexed { index, screen ->
            if (!screen.isInSettingScreen) return@forEachIndexed
            var newModifier = if (index == 0) Modifier.padding(top = 7.dp) else Modifier
            SettingsContent (
                modifier = newModifier.background(MaterialTheme.colorScheme.surface),
                name = screen.resourceId,
                onSettingClicked = { navigate(screen)
                    /*
                    when (screen) {
                        Screen.BackupContent -> navController.navigate(Screen.BackupContent.routeWithArgs)
                        Screen.SyncContent -> navController.navigate(Screen.SyncContent.routeWithArgs)
                        Screen.Settings -> navController.navigate(Screen.Settings.routeWithArgs)
                        Screen.Sound -> navController.navigate(Screen.Sound.routeWithArgs)
                        Screen.Battery -> navController.navigate(Screen.Battery.routeWithArgs)
                        Screen.LocationAndSecurity -> navController.navigate(Screen.LocationAndSecurity.routeWithArgs)
                        Screen.ConnectedDevices -> navController.navigate(Screen.ConnectedDevices.routeWithArgs)
                        Screen.Apps -> navController.navigate(Screen.Apps.routeWithArgs)
                        Screen.Display -> navController.navigate(Screen.Display.routeWithArgs)
                        Screen.System -> navController.navigate(Screen.System.routeWithArgs)
                        Screen.NetworkAndInternet -> navController.navigate(Screen.NetworkAndInternet.routeWithArgs)
                    }
                     */
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
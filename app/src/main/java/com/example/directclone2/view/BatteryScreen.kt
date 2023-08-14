package com.example.directclone2.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.view.ui.components.ButtonInCommonUi
import com.example.directclone2.view.ui.components.CardDividerInCommonUi
import com.example.directclone2.view.ui.components.CardViewInCommonUi
import com.example.directclone2.view.ui.components.RadioButtonsInCardViewInCommonUi
import com.example.directclone2.viewmodel.SettingViewModel
import com.example.directclone2.viewmodel.SmartCharging
import java.io.File
import java.util.Locale


@Composable
fun BatteryScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(),
) {
    if (vm.uiState.isOkSmartChargingInfo) {
        Dialog(onDismissRequest = {}) {
            Box(
                Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 42.dp, bottom = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = stringResource(R.string.smart_charging_label))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 28.dp)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                text = stringResource(R.string.life_x_mode_label))
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {
                            Text(style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = stringResource(R.string.life_x_mode_label_content))
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                text = stringResource(R.string.normal_mode_label))
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {
                            Text(style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = stringResource(R.string.normal_mode_content))
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                text = stringResource(R.string.fast_charging_mode_label))
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = stringResource(R.string.fast_charging_mode_content))
                        }
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        ButtonInCommonUi(
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = MaterialTheme.colorScheme.primary,
                            onClick = { vm.updateIsOkSmartChargingInfo() }
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

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            text = "Battery warning level",
            color = MaterialTheme.colorScheme.primary)
        CardViewInCommonUi(modifier = modifier) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Low warning level")
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "${vm.getBatteryLowWarningLevelForText()}%")
            }
            Slider(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 11.dp)
                    .fillMaxWidth(),
                value = vm.uiState.batteryLowWarningLevel,
                onValueChange = {vm.update("batteryLowWarningLevel", it)})
            CardDividerInCommonUi(modifier = modifier)
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Critical warning level")
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "${vm.getBatteryCriticalWarningLevelForText()}%")
            }
            Slider(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 11.dp)
                    .fillMaxWidth(),
                value = vm.uiState.batteryCriticalWarningLevel,
                onValueChange = {vm.update("batteryCriticalWarningLevel", it)})
        }
        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            text = "Smart charging")
        CardViewInCommonUi(modifier = modifier) {
            Row( modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Smart charging")
                IconButton(
                    modifier = Modifier.then(Modifier.size(24.dp)),
                    onClick = { vm.updateIsOkSmartChargingInfo() }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "ArrowForwardIos icon",
                        tint = MaterialTheme.colorScheme.onTertiary)
                }
            }
            RadioButtonsInCardViewInCommonUi(
                modifier = modifier,
                currentResId = vm.uiState.smartCharging,
                onRadioClicked = {vm.update("smartCharging", it)},
                titleAndSubTitleRes = listOf(
                    Pair(SmartCharging.LifeXMode.resIdOfTitle, SmartCharging.LifeXMode.resIdOfSubTitle),
                    Pair(SmartCharging.NormalMode.resIdOfTitle, SmartCharging.NormalMode.resIdOfSubTitle),
                    Pair(SmartCharging.FastChargingMode.resIdOfTitle, SmartCharging.FastChargingMode.resIdOfSubTitle))
            )
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun BatteryPreview() {
    BatteryScreen(
        vm = SettingViewModel(
            ProfileRepository(ProfileDiskDataSource(File("/storage/emulated/0/Profile.json")))))
}
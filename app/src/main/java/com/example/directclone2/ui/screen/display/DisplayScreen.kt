package com.example.directclone2.ui.screen.display

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.components.CardDividerInCommonUi
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CardViewItemInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi
import com.example.directclone2.ui.components.RadioButtonsInCardViewInCommonUi
import java.io.File

@Composable
fun DisplayScreen(
    modifier: Modifier = Modifier,
    vm: DisplayViewModel = viewModel(factory = DisplayViewModel.Factory),
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        CardViewInCommonUi(modifier.padding(top = 8.dp)) {
            CardViewItemInCommonUi(modifier = modifier) {
                Column() {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            text = stringResource(R.string.brightness_level_label))
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            text = "${vm.getScreenBrightnessForText()}%",
                            color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                    Slider(
                        value = vm.uiState.screenBrightness,
                        onValueChange = {vm.update("screenBrightness", it)})
                }
            }

            CardViewItemInCommonUi(modifier = modifier) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            text = stringResource(R.string.adaptive_brightness_label)
                        )
                        ToggleSwitchInCommonUi(
                            checked = vm.uiState.adaptiveBrightness,
                            onCheckedChange = {vm.update("adaptiveBrightness", it)})
                    }
                    Text(
                        style = MaterialTheme.typography.labelSmall,
                        text = stringResource(R.string.adaptive_brightness_sub_label),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
            CardViewItemInCommonUi(modifier = modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.auto_rotate_screen_label)
                )
                ToggleSwitchInCommonUi(
                    checked = vm.uiState.autoScreenRotate,
                    onCheckedChange = {vm.update("autoScreenRotate", it)})
            }
        }

        CardViewInCommonUi(modifier = modifier) {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 5.dp),
                style = MaterialTheme.typography.labelMedium,
                text = stringResource(R.string.touch_hold_delay_label),
                color = MaterialTheme.colorScheme.onSurface
            )
            RadioButtonsInCardViewInCommonUi(
                modifier = modifier,
                onRadioClicked = {vm.update("touchSensitivity", it)},
                currentResId = vm.uiState.touchSensitivity,
                titleAndSubTitleRes = listOf(
                    Pair(TouchSensitivity.Short.resIdOfTitle, TouchSensitivity.Short.resIdOfSubTitle),
                    Pair(TouchSensitivity.Medium.resIdOfTitle, TouchSensitivity.Medium.resIdOfSubTitle),
                    Pair(TouchSensitivity.Long.resIdOfTitle, TouchSensitivity.Long.resIdOfSubTitle)))
        }

        CardViewInCommonUi(modifier = modifier) {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 5.dp),
                style = MaterialTheme.typography.labelMedium,
                text = stringResource(R.string.font_size_label),
                color = MaterialTheme.colorScheme.onSurface
            )
            RadioButtonsInCardViewInCommonUi(
                modifier = modifier,
                onRadioClicked = {vm.update("systemFontSize", it)},
                currentResId = vm.uiState.systemFontSize,
                titleAndSubTitleRes = listOf(
                    Pair(SystemFontSize.Small.resIdOfTitle, SystemFontSize.Small.resIdOfSubTitle),
                    Pair(SystemFontSize.Default.resIdOfTitle, SystemFontSize.Default.resIdOfSubTitle),
                    Pair(SystemFontSize.Large.resIdOfTitle, SystemFontSize.Large.resIdOfSubTitle),
                    Pair(SystemFontSize.Largest.resIdOfTitle, SystemFontSize.Largest.resIdOfSubTitle)))

            CardDividerInCommonUi(modifier = modifier)

            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 5.dp),
                style = MaterialTheme.typography.labelMedium,
                text = stringResource(R.string.display_size_label),
                color = MaterialTheme.colorScheme.onSurface
            )
            RadioButtonsInCardViewInCommonUi(
                modifier = modifier,
                onRadioClicked = {vm.update("systemDisplaySize", it)},
                currentResId = vm.uiState.systemDisplaySize,
                titleAndSubTitleRes = listOf(
                    Pair(SystemDisplaySize.Small.resIdOfTitle, SystemDisplaySize.Small.resIdOfSubTitle),
                    Pair(SystemDisplaySize.Default.resIdOfTitle, SystemDisplaySize.Default.resIdOfSubTitle),
                    Pair(SystemDisplaySize.Large.resIdOfTitle, SystemDisplaySize.Large.resIdOfSubTitle),
                    Pair(SystemDisplaySize.Largest.resIdOfTitle, SystemDisplaySize.Largest.resIdOfSubTitle)))
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun DisplayPreview() {
    DisplayScreen(
        vm = DisplayViewModel(
            ProfileRepository(ProfileDiskDataSource(File("/storage/emulated/0/Profile.json"))))
    )
}
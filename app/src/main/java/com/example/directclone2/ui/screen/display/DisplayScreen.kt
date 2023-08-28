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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.FakeProfileRepository
import com.example.directclone2.ui.SettingViewModel
import com.example.directclone2.ui.components.CardDividerInCommonUi
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CardViewItemInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi
import com.example.directclone2.ui.components.RadioButtonsInCardViewInCommonUi

@Composable
fun DisplayScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(factory = SettingViewModel.Factory),
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
                        value = vm.displayUiState.screenBrightness,
                        onValueChange = {vm.updateDisplay("screenBrightness", it)})
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
                            checked = vm.displayUiState.adaptiveBrightness,
                            onCheckedChange = {vm.updateDisplay("adaptiveBrightness", it)})
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
                    checked = vm.displayUiState.autoScreenRotate,
                    onCheckedChange = {vm.updateDisplay("autoScreenRotate", it)})
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
                onRadioClicked = {vm.updateDisplay("touchSensitivity", it)},
                currentResId = vm.displayUiState.touchSensitivity.resIdOfTitle,
                titleAndSubTitleRes = listOf(
                    Pair(DisplayUiState.TouchSensitivity.Short.resIdOfTitle, DisplayUiState.TouchSensitivity.Short.resIdOfSubTitle),
                    Pair(DisplayUiState.TouchSensitivity.Medium.resIdOfTitle, DisplayUiState.TouchSensitivity.Medium.resIdOfSubTitle),
                    Pair(DisplayUiState.TouchSensitivity.Long.resIdOfTitle, DisplayUiState.TouchSensitivity.Long.resIdOfSubTitle)))
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
                onRadioClicked = {vm.updateDisplay("systemFontSize", it)},
                currentResId = vm.displayUiState.systemFontSize.resIdOfTitle,
                titleAndSubTitleRes = listOf(
                    Pair(DisplayUiState.SystemFontSize.Small.resIdOfTitle, DisplayUiState.SystemFontSize.Small.resIdOfSubTitle),
                    Pair(DisplayUiState.SystemFontSize.Default.resIdOfTitle, DisplayUiState.SystemFontSize.Default.resIdOfSubTitle),
                    Pair(DisplayUiState.SystemFontSize.Large.resIdOfTitle, DisplayUiState.SystemFontSize.Large.resIdOfSubTitle),
                    Pair(DisplayUiState.SystemFontSize.Largest.resIdOfTitle, DisplayUiState.SystemFontSize.Largest.resIdOfSubTitle)))

            CardDividerInCommonUi(modifier = modifier)

            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 5.dp),
                style = MaterialTheme.typography.labelMedium,
                text = stringResource(R.string.display_size_label),
                color = MaterialTheme.colorScheme.onSurface
            )
            RadioButtonsInCardViewInCommonUi(
                modifier = modifier,
                onRadioClicked = {vm.updateDisplay("systemDisplaySize", it)},
                currentResId = vm.displayUiState.systemDisplaySize.resIdOfTitle,
                titleAndSubTitleRes = listOf(
                    Pair(DisplayUiState.SystemDisplaySize.Small.resIdOfTitle, DisplayUiState.SystemDisplaySize.Small.resIdOfSubTitle),
                    Pair(DisplayUiState.SystemDisplaySize.Default.resIdOfTitle, DisplayUiState.SystemDisplaySize.Default.resIdOfSubTitle),
                    Pair(DisplayUiState.SystemDisplaySize.Large.resIdOfTitle, DisplayUiState.SystemDisplaySize.Large.resIdOfSubTitle),
                    Pair(DisplayUiState.SystemDisplaySize.Largest.resIdOfTitle, DisplayUiState.SystemDisplaySize.Largest.resIdOfSubTitle)))
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun DisplayPreview() {
    DisplayScreen(
        vm = SettingViewModel(FakeProfileRepository(), SavedStateHandle())
    )
}
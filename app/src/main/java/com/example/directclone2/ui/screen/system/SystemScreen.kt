package com.example.directclone2.ui.screen.system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.directclone2.ui.components.DatePickerDialogInCommonUi
import com.example.directclone2.ui.components.ExposedDropdownMenuInCommonUi
import com.example.directclone2.ui.components.ExposedDropdownMenuForArrayResInCommonUi
import com.example.directclone2.ui.components.OutlinedTextFieldInCommonUi
import com.example.directclone2.ui.components.RadioButtonsInCardViewInCommonUi
import com.example.directclone2.ui.components.TextAndIconCardItemInCommonUi
import com.example.directclone2.ui.components.TimePickerDialog
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi

@Composable
fun SystemScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(factory = SettingViewModel.Factory),
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            text = stringResource(R.string.languages_label),
            color = MaterialTheme.colorScheme.primary
        )
        CardViewInCommonUi(modifier.fillMaxWidth()) {
            Text(
                modifier = modifier.padding(start = 16.dp, top = 20.dp, bottom = 13.dp),
                style = MaterialTheme.typography.labelMedium,
                text = stringResource(R.string.languages_label),
                color = MaterialTheme.colorScheme.onSurface
            )
            ExposedDropdownMenuForArrayResInCommonUi(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
                onClickItem = {vm.updateSystem("languages", it)},
                currentSelectedItem = vm.systemUiState.languages,
                arrayRes = R.array.languages_options,
                //R.array.languages_options
            )
        }
        CardViewInCommonUi(modifier) {
            CardViewItemInCommonUi(modifier = modifier) {
                Text(
                    modifier = modifier,
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.spell_checker_label),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ToggleSwitchInCommonUi(
                    checked = vm.systemUiState.spellChecker,
                    onCheckedChange = {vm.updateSystem("spellChecker", it)})
            }
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 13.dp),
                style = MaterialTheme.typography.labelMedium,
                text = stringResource(R.string.languages_label),
                color = MaterialTheme.colorScheme.onSurface
            )
            ExposedDropdownMenuForArrayResInCommonUi(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
                onClickItem = {vm.updateSystem("spellCheckLanguage", it)},
                currentSelectedItem = vm.systemUiState.spellCheckLanguage,
                arrayRes = R.array.sample)
            CardDividerInCommonUi(modifier)
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 13.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.defalut_spell_checker_label)
                )
                Icon(
                    modifier = Modifier.size(width = 24.dp, height = 24.dp),
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings icon",
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
            RadioButtonsInCardViewInCommonUi(
                modifier = modifier,
                currentResId = vm.systemUiState.defaultSpellChecker.resIdOfTitle,
                onRadioClicked = {vm.updateSystem("defaultSpellChecker", it)},
                titleAndSubTitleRes = listOf(
                    Pair(
                        SystemUiState.DefaultSpellChecker.GboardSpellChecker.resIdOfTitle,
                        SystemUiState.DefaultSpellChecker.GboardSpellChecker.resIdOfSubTitle)))
            Text(
                modifier = modifier.padding(start = 16.dp, top = 14.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(R.string.pointer_speed_label)
            )
            Slider(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                value = vm.systemUiState.spellCheckPointerSpeed,
                onValueChange = {vm.updateSystem("spellCheckPointerSpeed", it)})
        }
        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            text = stringResource(R.string.data_and_time_label),
            color = MaterialTheme.colorScheme.primary)
        CardViewInCommonUi(modifier = modifier) {
            CardViewItemInCommonUi(modifier = modifier) {
                Text(style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = stringResource(R.string.use_network_provided_time_label))
                ToggleSwitchInCommonUi(
                    checked = vm.systemUiState.useNetworkProvidedTime,
                    onCheckedChange = {vm.updateSystem("useNetworkProvidedTime", it)})
            }
            Text(
                modifier = modifier.padding(start = 16.dp, top = 14.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(R.string.date_label)
            )
            val openDatePickerDialog = remember { mutableStateOf(vm.systemUiState.openDatePickerDialog) }
            TextAndIconCardItemInCommonUi(
                text = vm.systemUiState.systemDate,
                onClickItem = {openDatePickerDialog.value = true},
                icon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date Picker Icon")})
            DatePickerDialogInCommonUi(
                openDialog = openDatePickerDialog,
                onSelectedDate = vm::updateSystemDate)
            CardDividerInCommonUi(modifier)
            Text(
                modifier = modifier.padding(start = 16.dp, top = 14.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(R.string.time_label))
            val openTimePickerDialog = remember { mutableStateOf(vm.systemUiState.openTimePickerDialog) }
            TextAndIconCardItemInCommonUi(
                text = vm.systemUiState.systemTime,
                onClickItem = {openTimePickerDialog.value = true},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Time Picker Icon")})
            TimePickerDialog(
                openDialog = openTimePickerDialog,
                onSelectedTime = vm::updateSystemTime)
        }
        CardViewInCommonUi(modifier = modifier) {
            CardViewItemInCommonUi(modifier = modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = stringResource(R.string.use_network_provided_time_zone_label))
                ToggleSwitchInCommonUi(
                    checked = vm.systemUiState.useNetworkProvidedTimeZone,
                    onCheckedChange = {vm.updateSystem("useNetworkProvidedTimeZone", it)})
            }
            Text(
                modifier = modifier.padding(start = 16.dp, top = 14.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(R.string.time_zone_label))
            ExposedDropdownMenuInCommonUi(
                modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp))
        }
        CardViewInCommonUi(modifier = modifier) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = stringResource(R.string.use_24_hour_format_label))
                ToggleSwitchInCommonUi(
                    checked = vm.systemUiState.use24hourFormat,
                    onCheckedChange = {vm.updateSystem("use24hourFormat", it)})
            }
            Text(
                modifier = modifier.padding(start = 16.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                text = "1:00 PM")
        }
        CardViewInCommonUi(modifier = modifier) {
            Text(
                modifier = modifier.padding(start = 16.dp, top = 14.dp, bottom = 13.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = stringResource(R.string.ntp_server_label))
            OutlinedTextFieldInCommonUi(
                modifier = modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
                value = vm.systemUiState.ntpServer,
                onValueChange = {vm.updateSystem("ntpServer", it)})
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun SystemPreview() {
    SystemScreen(
        vm = SettingViewModel(FakeProfileRepository(), SavedStateHandle())
    )
}
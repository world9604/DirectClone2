package com.example.directclone2.ui.screen.connecteddevices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.FakeProfileRepository
import com.example.directclone2.ui.SettingViewModel
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CardViewItemInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi

@Composable
fun ConnectedDevicesScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(factory = SettingViewModel.Factory),
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            text = stringResource(R.string.display_label),
            color = MaterialTheme.colorScheme.primary)
        CardViewInCommonUi(modifier) {
            CardViewItemInCommonUi(modifier = modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = stringResource(R.string.bluetooth_label))
                ToggleSwitchInCommonUi(
                    checked = vm.connectedDevicesUiState.bluetooth,
                    onCheckedChange = {vm.updateConnectedDevices("bluetooth", it)}
                )
            }
            CardViewItemInCommonUi(modifier = modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.nfc_label))
                ToggleSwitchInCommonUi(
                    checked = vm.connectedDevicesUiState.nfc,
                    onCheckedChange = {vm.updateConnectedDevices("nfc", it)}
                )
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun ConnectedDevicesPreview() {
    ConnectedDevicesScreen(
        vm = SettingViewModel(FakeProfileRepository(), SavedStateHandle())
    )
}
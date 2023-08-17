package com.example.directclone2.ui.screen.connecteddevices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CardViewItemInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi
import java.io.File

@Composable
fun ConnectedDevicesScreen(
    modifier: Modifier = Modifier,
    vm: ConnectedDevicesViewModel = viewModel(factory = ConnectedDevicesViewModel.Factory),
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
                    checked = vm.uiState.bluetooth,
                    onCheckedChange = {vm.update("bluetooth", it)}
                )
            }
            CardViewItemInCommonUi(modifier = modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(R.string.nfc_label))
                ToggleSwitchInCommonUi(
                    checked = vm.uiState.nfc,
                    onCheckedChange = {vm.update("nfc", it)}
                )
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun ConnectedDevicesPreview() {
    ConnectedDevicesScreen(
        vm = ConnectedDevicesViewModel(
            ProfileRepository.getInstance(
                ProfileDiskDataSource.getInstance(File("/storage/emulated/0/Profile.json"))))
    )
}
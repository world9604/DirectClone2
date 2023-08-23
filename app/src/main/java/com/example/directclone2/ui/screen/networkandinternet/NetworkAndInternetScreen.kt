package com.example.directclone2.ui.screen.networkandinternet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.R
import com.example.directclone2.model.FakeProfileRepository
import com.example.directclone2.ui.NavigationDestination
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CardViewItemInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi

@Composable
fun NetworkAndInternetScreen(
    modifier: Modifier = Modifier,
    vm: NetworkAndInternetViewModel = viewModel(factory = NetworkAndInternetViewModel.Factory),
) {

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        CardViewInCommonUi(modifier.padding(top = 16.dp)) {
            CardViewItemInCommonUi(modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Wi-Fi")
                ToggleSwitchInCommonUi(
                    checked = vm.uiState.wifi,
                    onCheckedChange = {vm.update("wifi", it)}
                )
            }
            CardViewItemInCommonUi(modifier) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        text = "Saved Networks")
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        text = "1 Network")
                }
            }
            CardViewItemInCommonUi(modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Data Server")
                ToggleSwitchInCommonUi(
                    checked = vm.uiState.dataServer,
                    onCheckedChange = {vm.update("dataServer", it)}
                )
            }
            CardViewItemInCommonUi(modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Roaming")
                ToggleSwitchInCommonUi(
                    checked = vm.uiState.roaming,
                    onCheckedChange = {vm.update("roaming", it)}
                )
            }
            CardViewItemInCommonUi(modifier) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        text = "Ethernet")
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        text = "DHCP (Automatic IP)")
                }
            }
            CardViewItemInCommonUi(modifier) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "VPN")
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun NetworkAndInternetPreview() {
    NetworkAndInternetScreen(
        vm = NetworkAndInternetViewModel(FakeProfileRepository(), SavedStateHandle())
    )
}
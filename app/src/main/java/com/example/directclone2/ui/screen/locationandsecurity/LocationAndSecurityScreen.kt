package com.example.directclone2.ui.screen.locationandsecurity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.model.FakeProfileRepository
import com.example.directclone2.ui.SettingViewModel
import com.example.directclone2.ui.components.CardDividerInCommonUi
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CardViewItemInCommonUi
import com.example.directclone2.ui.components.ExposedDropdownMenuInCommonUi
import com.example.directclone2.ui.components.OutlinedTextFieldInCommonUi
import com.example.directclone2.ui.components.RadioButtonsWithContentsInCardViewInCommonUi
import com.example.directclone2.ui.components.SecondaryCardInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi

@Composable
fun LocationAndSecurityScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(factory = SettingViewModel.Factory),
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        CardViewInCommonUi(modifier = modifier) {
            CardViewItemInCommonUi(modifier = modifier) {
                Text(text = "Use location")
                ToggleSwitchInCommonUi(
                    checked = vm.locationAndSecurityUiState.useLocation,
                    onCheckedChange = {vm.updateScreenLock("useLocation", it)}
                )
            }
        }
        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            text = "Screen Lock",
            color = MaterialTheme.colorScheme.primary)
        SecondaryCardInCommonUi { modifier ->
            Text(
                modifier = modifier,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                text = "Current Password")
        }
        CardViewInCommonUi(modifier = modifier) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = "PIN or Password")
            OutlinedTextFieldInCommonUi(
                modifier = modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                value = vm.locationAndSecurityUiState.currentScreenLockPinOrPassword,
                onValueChange = {vm.updateScreenLock("currentScreenLockPinOrPassword", it)})
        }
        SecondaryCardInCommonUi { modifier ->
            Text(
                modifier = modifier,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                text = "Choose Screen Lock")
        }
        CardViewInCommonUi(modifier = modifier) {
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = "Choose Screen Lock")
            RadioButtonsWithContentsInCardViewInCommonUi(
                modifier = modifier,
                currentResId = vm.locationAndSecurityUiState.screenLock.resIdOfTitle,
                onRadioClicked = vm::updateScreenLock,
                resAndContents = arrayOf(
                    Triple(LocationAndSecurityUiState.ScreenLock.None.resIdOfTitle,
                        LocationAndSecurityUiState.ScreenLock.None.resIdOfSubTitle){},
                    Triple(LocationAndSecurityUiState.ScreenLock.Swipe.resIdOfTitle,
                        LocationAndSecurityUiState.ScreenLock.Swipe.resIdOfSubTitle){},
                    Triple(LocationAndSecurityUiState.ScreenLock.Pin.resIdOfTitle,
                        LocationAndSecurityUiState.ScreenLock.Pin.resIdOfSubTitle){
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            OutlinedTextFieldInCommonUi(
                                modifier = modifier.fillMaxWidth(),
                                value = vm.locationAndSecurityUiState.screenLockPin,
                                onValueChange = {vm.updateScreenLock("screenLockPin", it)}
                            )
                        }
                    },
                    Triple(LocationAndSecurityUiState.ScreenLock.Password.resIdOfTitle,
                        LocationAndSecurityUiState.ScreenLock.Password.resIdOfSubTitle){
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        ) {
                            OutlinedTextFieldInCommonUi(
                                modifier = modifier.fillMaxWidth(),
                                value = vm.locationAndSecurityUiState.screenLockPassword,
                                onValueChange = {vm.updateScreenLock("screenLockPassword", it)}
                            )
                        }
                    },
                ))
        }
        SecondaryCardInCommonUi { modifier ->
            Text(
                modifier = modifier,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                text = "Lock Screen Message")
        }
        CardViewInCommonUi(modifier = modifier) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = "Message")
            OutlinedTextFieldInCommonUi(
                modifier = modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                value = vm.locationAndSecurityUiState.screenLockMessage,
                onValueChange = {vm.updateScreenLock("screenLockMessage", it)})
        }
        SecondaryCardInCommonUi { modifier ->
            Text(
                modifier = modifier,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary,
                text = "Screen Lock")
        }
        CardViewInCommonUi(modifier = modifier) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = "Lock After Screen Timeout")
            ExposedDropdownMenuInCommonUi(
                modifier = modifier.padding(start = 16.dp, end = 16.dp),
                currentResId = vm.locationAndSecurityUiState.lockAfterScreenTimeout.resIdOfTitle,
                onClickItem = {vm.updateScreenLock("lockAfterScreenTimeout", it)},
                itemRes = listOf(
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Immediately.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Sec5.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Sec15.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Sec30.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Min1.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Min2.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Min5.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Min10.resIdOfTitle,
                    LocationAndSecurityUiState.LockAfterScreenTimeout.Min30.resIdOfTitle))
            CardDividerInCommonUi(modifier.padding(top = 14.dp, bottom = 16.dp))
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = "Power Button Instantly Locks")
            ExposedDropdownMenuInCommonUi(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 30.dp),
                currentResId = vm.locationAndSecurityUiState.powerButtonInstantlyLocks.resIdOfTitle,
                onClickItem = {vm.updateScreenLock("powerButtonInstantlyLocks", it)},
                itemRes = listOf(
                    LocationAndSecurityUiState.PowerButtonInstantlyLocks.Disable.resIdOfTitle,
                    LocationAndSecurityUiState.PowerButtonInstantlyLocks.Enable.resIdOfTitle))
        }

        //여백 추가
        Column(modifier.height(200.dp)) {
            Row(modifier.fillMaxWidth()) {
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun LocationAndSecurityPreview() {
    LocationAndSecurityScreen(
        vm = SettingViewModel(FakeProfileRepository(), SavedStateHandle())
    )
}
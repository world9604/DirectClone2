package com.example.directclone2.view

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.view.ui.components.CardDividerInCommonUi
import com.example.directclone2.view.ui.components.CardViewInCommonUi
import com.example.directclone2.view.ui.components.CardViewItemInCommonUi
import com.example.directclone2.view.ui.components.RadioButtonsInCardViewInCommonUi
import com.example.directclone2.view.ui.components.SecondaryToggleCardInCommonUi
import com.example.directclone2.view.ui.components.ToggleSwitchInCommonUi
import com.example.directclone2.viewmodel.Calls
import com.example.directclone2.viewmodel.Conversations
import com.example.directclone2.viewmodel.VibrateOnTouch
import com.example.directclone2.viewmodel.Messages
import com.example.directclone2.viewmodel.SettingViewModel
import java.io.File


@Composable
fun SoundScreen(
    modifier: Modifier = Modifier,
    vm: SettingViewModel = viewModel(),
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
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
                    text = "Media volume")
            }
            Slider(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 11.dp)
                    .fillMaxWidth(),
                value = vm.uiState.musicVolume,
                onValueChange = {vm.update("musicVolume", it)})
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
                    text = "Call voice volume")
            }
            Slider(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 11.dp)
                    .fillMaxWidth(),
                value = vm.uiState.callVolume,
                onValueChange = {vm.update("callVolume", it)})
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
                    text = "Alarm volume")
            }
            Slider(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 11.dp)
                    .fillMaxWidth(),
                value = vm.uiState.alarmVolume,
                onValueChange = {vm.update("alarmVolume", it)})
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
                    text = "Notification volume")
            }
            Slider(
                modifier = modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 11.dp)
                    .fillMaxWidth(),
                value = vm.uiState.notificationVolume,
                onValueChange = {vm.update("notificationVolume", it)})
            CardDividerInCommonUi(modifier = modifier)
            Text(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 13.dp),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                text = "Vibrate for calls")
            RadioButtonsInCardViewInCommonUi(
                modifier = modifier,
                onRadioClicked = {vm.update("vibrateOnTouch", it)},
                currentResId = vm.uiState.vibrateOnTouch,
                titleAndSubTitleRes = listOf(
                    Pair(
                        VibrateOnTouch.Enable.resIdOfTitle,
                        VibrateOnTouch.Enable.resIdOfSubTitle),
                    Pair(
                        VibrateOnTouch.Disable.resIdOfTitle,
                        VibrateOnTouch.Disable.resIdOfSubTitle)))
        }

        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            text = "Do Not Disturb")

        var openPeopleCard by remember { mutableStateOf(false) }
        SecondaryToggleCardInCommonUi(
            modifier = modifier,
            text = "People",
            isOpen = openPeopleCard,
            onClickItem = {openPeopleCard = !openPeopleCard}
        ) { modifier ->
            Text(
                modifier = modifier.padding(top = 20.dp, bottom = 13.dp, start = 16.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium,
                text = "Conversations")
            Text(
                modifier = modifier.padding(bottom = 6.dp, start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                text = "Conversations that can interrupt")
            RadioButtonsInCardViewInCommonUi(
                onRadioClicked = {vm.update("conversations", it)},
                currentResId = vm.uiState.conversations,
                titleAndSubTitleRes = listOf(
                    Pair(Conversations.AllConversations.resIdOfTitle, Conversations.AllConversations.resIdOfSubTitle),
                    Pair(Conversations.PriorityConversations.resIdOfTitle, Conversations.PriorityConversations.resIdOfSubTitle),
                    Pair(Conversations.None.resIdOfTitle, Conversations.None.resIdOfSubTitle),
                ))
            CardDividerInCommonUi(modifier)
            Text(
                modifier = modifier.padding(top = 20.dp, bottom = 13.dp, start = 16.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium,
                text = "Calls")
            Text(
                modifier = modifier.padding(bottom = 6.dp, start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                text = "Calls that can interrupt")
            RadioButtonsInCardViewInCommonUi(
                currentResId = vm.uiState.calls,
                onRadioClicked = {vm.update("calls", it)},
                titleAndSubTitleRes = listOf(
                    Pair(Calls.StarredContacts.resIdOfTitle, Calls.StarredContacts.resIdOfSubTitle),
                    Pair(Calls.Contacts.resIdOfTitle, Calls.Contacts.resIdOfSubTitle),
                    Pair(Calls.Anyone.resIdOfTitle, Calls.Anyone.resIdOfSubTitle),
                    Pair(Calls.None.resIdOfTitle, Calls.None.resIdOfSubTitle),
                ))
            CardDividerInCommonUi(modifier)
            Text(
                modifier = modifier.padding(top = 20.dp, bottom = 13.dp, start = 16.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium,
                text = "Messages")
            Text(
                modifier = modifier.padding(bottom = 6.dp, start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
                text = "Messages that can interrupt")
            RadioButtonsInCardViewInCommonUi(
                currentResId = vm.uiState.messages,
                onRadioClicked = {vm.update("messages", it)},
                titleAndSubTitleRes = listOf(
                    Pair(Messages.StarredContacts.resIdOfTitle, Messages.StarredContacts.resIdOfSubTitle),
                    Pair(Messages.Contacts.resIdOfTitle, Messages.Contacts.resIdOfSubTitle),
                    Pair(Messages.Anyone.resIdOfTitle, Messages.Anyone.resIdOfSubTitle),
                    Pair(Messages.None.resIdOfTitle, Messages.None.resIdOfSubTitle),
                ))
        }

        var openAlarmAndOtherCard by remember { mutableStateOf(false) }
        SecondaryToggleCardInCommonUi(
            modifier = modifier,
            text = "Alarms & other interruptions",
            isOpen = openAlarmAndOtherCard,
            onClickItem = {openAlarmAndOtherCard = !openAlarmAndOtherCard}
        ) { modifier ->
            Column {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 9.dp),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary,
                    text = "Allow interruptions that make sound")
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Alarms")
                    ToggleSwitchInCommonUi(
                        checked = vm.uiState.alarms,
                        onCheckedChange = { vm.update("alarms", it) })
                }
            }
            CardDividerInCommonUi(modifier)
            CardViewItemInCommonUi(modifier) {
                Column {
                    Row (
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            text = "Media sounds")
                        ToggleSwitchInCommonUi(
                            checked = vm.uiState.mediaSounds,
                            onCheckedChange = { vm.update("mediaSounds", it) })
                    }
                    Text(
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        text = "Sounds from videos, games, and other media")
                }
            }
            CardViewItemInCommonUi(modifier) {
                Column {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            text = "Touch sounds"
                        )
                        ToggleSwitchInCommonUi(
                            checked = vm.uiState.touchSounds,
                            onCheckedChange = { vm.update("touchSounds", it) })
                    }
                    Text(
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        text = "Sounds from the keyboard and other buttons"
                    )
                }
            }
            CardViewItemInCommonUi(modifier) {
                Row (
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Reminders")
                    ToggleSwitchInCommonUi(
                        checked = vm.uiState.reminders,
                        onCheckedChange = { vm.update("reminders", it) })
                }
            }
            CardViewItemInCommonUi(modifier) {
                Row (
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Calendar events")
                    ToggleSwitchInCommonUi(
                        checked = vm.uiState.calendarEvents,
                        onCheckedChange = { vm.update("calendarEvents", it) })
                }
            }
        }

        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            text = "Advanced")
        CardViewInCommonUi(
            modifier = modifier
        ) {
            Column {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 9.dp),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary,
                    text = "Other sounds and vibrations")
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Dial pad tones")
                    ToggleSwitchInCommonUi(
                        checked = vm.uiState.dialPadTones,
                        onCheckedChange = { vm.update("dialPadTones", it) })
                }
            }
            CardDividerInCommonUi(modifier)
            CardViewItemInCommonUi(modifier) {
                Row (
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Screen locking sounds")
                    ToggleSwitchInCommonUi(
                        checked = vm.uiState.screenLockingSounds,
                        onCheckedChange = { vm.update("screenLockingSounds", it) })
                }
            }
            CardViewItemInCommonUi(modifier) {
                Row (
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Charging sounds and vibration")
                    ToggleSwitchInCommonUi(
                        checked = vm.uiState.chargingSoundsAndVibration,
                        onCheckedChange = { vm.update("chargingSoundsAndVibration", it) })
                }
            }
            CardViewItemInCommonUi(modifier) {
                Row (
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Touch sounds")
                    ToggleSwitchInCommonUi(
                        checked = vm.uiState.touchSounds,
                        onCheckedChange = { vm.update("touchSounds", it) })
                }
            }
            CardViewItemInCommonUi(modifier) {
                Column {
                    Row (
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            text = "Touch vibration")
                        ToggleSwitchInCommonUi(
                            checked = vm.uiState.touchVibration,
                            onCheckedChange = { vm.update("touchVibration", it) })
                    }
                    Text(style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        text = "Haptic feedback for tap, keyboard, and more")
                }
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun SoundPreview() {
    SoundScreen(
        vm = SettingViewModel(
            ProfileRepository(ProfileDiskDataSource(File("/storage/emulated/0/Profile.json")))))
}
package com.example.directclone2.view.ui.components

import android.annotation.SuppressLint
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.directclone2.R
import com.example.directclone2.view.ui.theme.theme_light_switch_off_border
import com.example.directclone2.view.ui.theme.theme_light_switch_off_thumb
import com.example.directclone2.view.ui.theme.theme_light_switch_off_track
import com.example.directclone2.view.ui.theme.theme_light_switch_on_thumb
import com.example.directclone2.view.ui.theme.theme_light_switch_on_track
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: AppNavigation,
    onBackButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        navigationIcon = {
            if (currentScreen == AppNavigation.Backup || currentScreen == AppNavigation.Sync) {
                Image(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    painter = painterResource(R.drawable.icon_direct_clone),
                    contentDescription = null,
                    contentScale = ContentScale.Crop)
            } else {
                IconButton(onClick = onBackButtonClicked) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back button",
                        tint = MaterialTheme.colorScheme.onSecondary)
                }
            }
         },
        title = { Text(
            style = MaterialTheme.typography.headlineLarge,
            text = stringResource(currentScreen.title)
        ) },
        actions = {
            if (currentScreen == AppNavigation.Backup || currentScreen == AppNavigation.Sync) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        tint = MaterialTheme.colorScheme.onSecondary)
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Info",
                        tint = MaterialTheme.colorScheme.onSecondary)
                }
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.onSecondary,
        )
    )
}

@Preview(group="Test", showBackground = true)
@Composable
fun AppBarPreview() {
    Column() {
        AppBar(currentScreen = AppNavigation.ConnectedDevices)
        AppBar(currentScreen = AppNavigation.Backup)
    }
}

@Composable
fun CardDividerInCommonUi(modifier: Modifier) {
    Divider(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp),
        color = MaterialTheme.colorScheme.tertiary,
        thickness = 1.dp)
}

@Composable
fun CardViewInCommonUi(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            content()
        }
    }
}

@Composable
fun CardViewItemInCommonUi(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 13.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
    CardDividerInCommonUi(modifier)
}

@Preview(group="Test", showBackground = true)
@Composable
fun CardViewPreview() {
    CardViewInCommonUi(Modifier.fillMaxWidth()) {
        Text(text = "sample text1")
        Text(text = "sample text2")
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun SecondaryCardViewInCommonUiPreview() {
    SecondaryCardInCommonUi() { modifier ->
        Text(
            modifier = modifier,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
            text = "Current Password")
    }
}

@Composable
fun SecondaryCardInCommonUi(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onTertiary),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            content(Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp))
        }
    }
}

@Preview(group="Work", showBackground = true)
@Composable
fun SecondaryToggleCardInCommonUiPreview() {
    SecondaryToggleCardInCommonUi() { modifier ->
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
                    checked = true,
                    onCheckedChange = { })
            }
        }
    }
}

@Composable
fun SecondaryToggleCardInCommonUi(
    modifier: Modifier = Modifier,
    text: String = "sample text",
    isOpen: Boolean = true,
    onClickItem: () -> Unit = {},
    content: @Composable (Modifier) -> Unit
) {
    val (backgroundColor, textColor, tintColor) = when {
        isOpen -> {
            Triple(MaterialTheme.colorScheme.onPrimaryContainer,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.onTertiary)
        }
        else -> {
            Triple(MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.tertiaryContainer,
                MaterialTheme.colorScheme.tertiaryContainer)
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClickItem() }
                .padding(bottom = 4.dp)
                .clip(RoundedCornerShape(8.dp)),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = modifier,
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor,
                    text = text)
                Icon(
                    imageVector = Icons.Filled.ExpandMore,
                    tint = tintColor,
                    contentDescription = "item toggle icon",
                    modifier = Modifier.rotate(if (isOpen) 180f else 0f))
            }
        }
        if (isOpen) {
            Card(
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
            ) {
                Column(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    content(modifier)
                }
            }
        }
    }
}

@Composable
fun ToggleSwitchInCommonUi(
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = theme_light_switch_on_thumb,
            checkedTrackColor = theme_light_switch_on_track,
            uncheckedThumbColor = theme_light_switch_off_thumb,
            uncheckedTrackColor = theme_light_switch_off_track,
            uncheckedBorderColor = theme_light_switch_off_border,
        ),
    )
}

@Preview(group="Test", showBackground = true)
@Composable
fun SwitchPreview() {
    Column(Modifier.fillMaxWidth()) {
        ToggleSwitchInCommonUi(true)
        ToggleSwitchInCommonUi(false)
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun RadioButtonsInCardViewPreview() {
    RadioButtonsInCardViewInCommonUi()
}

@Composable
fun RadioButtonsInCardViewInCommonUi(
    modifier: Modifier = Modifier,
    @StringRes currentResId: Int = R.string.brightness_level_label,
    onRadioClicked: (Int) -> Unit = {},
    titleAndSubTitleRes: List<Pair<Int, Int>> = listOf(
        Pair(R.string.brightness_level_label, R.string.brightness_level_label),
        Pair(R.string.languages_label, R.string.brightness_level_label),
        Pair(R.string.app_name, R.string.brightness_level_label)),
) {
    CardViewInCommonUi(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier.padding(bottom = 6.dp)) {
            for (res in titleAndSubTitleRes) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, start = 16.dp, end = 16.dp, bottom = 6.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        modifier = modifier.padding(end = 4.dp),
                        selected = currentResId == res.first,
                        onClick = { onRadioClicked(res.first) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.onSurface,
                            unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)))
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                            text = stringResource(res.first))
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            text = stringResource(res.second))
                    }
                }
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun RadioButtonsWithContentsInCardViewPreview() {
    RadioButtonsWithContentsInCardViewInCommonUi()
}

@Composable
fun RadioButtonsWithContentsInCardViewInCommonUi(
    modifier: Modifier = Modifier,
    @StringRes currentResId: Int = R.string.brightness_level_label,
    onRadioClicked: (Int) -> Unit = {},
    resAndContents: Array<Triple<Int, Int, @Composable () -> Unit>> = arrayOf(
        Triple(R.string.brightness_level_label, R.string.brightness_level_label){
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(6.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.tertiary),
                value = "sample text1",
                onValueChange = {})
        },
        Triple(R.string.languages_label, R.string.brightness_level_label){},
        Triple(R.string.app_name, R.string.brightness_level_label){}),
) {
    CardViewInCommonUi(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier.padding(bottom = 6.dp)) {
            for (resAndContentsItem in resAndContents) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, start = 16.dp, end = 16.dp, bottom = 6.dp),
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            modifier = modifier.padding(end = 4.dp),
                            selected = currentResId == resAndContentsItem.first,
                            onClick = { onRadioClicked(resAndContentsItem.first) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.onSurface,
                                unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)))
                        Column(
                            modifier = modifier,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimary,
                                //color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = stringResource(resAndContentsItem.first))
                            Text(
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                text = stringResource(resAndContentsItem.second))
                        }
                    }
                    resAndContentsItem.third()
                }
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun ExposedDropdownMenuPreview() {
    ExposedDropdownMenuForArrayResInCommonUi(Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 14.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuForArrayResInCommonUi(
    modifier: Modifier,
    onClickItem: (String) -> Unit = {},
    currentSelectedItem: String = "Brightness level",
    @ArrayRes arrayRes: Int = R.array.sample
) {
    var expanded by remember { mutableStateOf(false) }
    val items = stringArrayResource(arrayRes)
    //var selectedOptionText by remember { mutableStateOf(items[0]) }

    ExposedDropdownMenuBox (
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.tertiary
                )
                .menuAnchor(),
            value = currentSelectedItem,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    Icons.Filled.ExpandMore,
                    null,
                    Modifier.rotate(if (expanded) 180f else 0f)) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                errorTextColor = MaterialTheme.colorScheme.onPrimary,

                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.surface,

                cursorColor = Color.Red,
                errorCursorColor = Color.Red,
                selectionColors = TextSelectionColors(handleColor = Color.Red, backgroundColor = Color.Red),

                focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                disabledIndicatorColor = MaterialTheme.colorScheme.surface,
                errorIndicatorColor = MaterialTheme.colorScheme.surface,

                focusedLeadingIconColor = Color.Red,
                unfocusedLeadingIconColor = Color.Red,
                disabledLeadingIconColor = Color.Red,
                errorLeadingIconColor = Color.Red,

                focusedTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
                errorTrailingIconColor = MaterialTheme.colorScheme.onTertiary,

                focusedLabelColor = Color.Red,
                unfocusedLabelColor = Color.Red,
                disabledLabelColor = Color.Red,
                errorLabelColor = Color.Red,

                focusedPlaceholderColor = Color.Red,
                unfocusedPlaceholderColor = Color.Red,
                disabledPlaceholderColor = Color.Red,
                errorPlaceholderColor = Color.Red,

                focusedPrefixColor = Color.Red,
                unfocusedPrefixColor = Color.Red,
                disabledPrefixColor = Color.Red,
                errorPrefixColor = Color.Red,

                focusedSuffixColor = Color.Red,
                unfocusedSuffixColor = Color.Red,
                disabledSuffixColor = Color.Red,
                errorSuffixColor = Color.Red))
        ExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(
                        text = selectionOption,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge
                    ) },
                    onClick = {
                        //selectedOptionText = selectionOption
                        onClickItem(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuInCommonUi(
    modifier: Modifier,
    onClickItem: (Int) -> Unit = {},
    currentResId: Int = R.string.brightness_level_label,
    itemRes: List<Int> = listOf(
        R.string.brightness_level_label, R.string.adaptive_brightness_label)
) {
    var expanded by remember { mutableStateOf(false) }
    val item = stringResource(currentResId)

    ExposedDropdownMenuBox (
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.tertiary
                )
                .menuAnchor(),
            value = item,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Filled.ExpandMore,
                    null,
                    Modifier.rotate(if (expanded) 180f else 0f)) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                errorTextColor = MaterialTheme.colorScheme.onPrimary,

                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.surface,

                cursorColor = Color.Red,
                errorCursorColor = Color.Red,
                selectionColors = TextSelectionColors(
                    handleColor = Color.Red,
                    backgroundColor = Color.Red),

                focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                disabledIndicatorColor = MaterialTheme.colorScheme.surface,
                errorIndicatorColor = MaterialTheme.colorScheme.surface,

                focusedLeadingIconColor = Color.Red,
                unfocusedLeadingIconColor = Color.Red,
                disabledLeadingIconColor = Color.Red,
                errorLeadingIconColor = Color.Red,

                focusedTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
                errorTrailingIconColor = MaterialTheme.colorScheme.onTertiary,

                focusedLabelColor = Color.Red,
                unfocusedLabelColor = Color.Red,
                disabledLabelColor = Color.Red,
                errorLabelColor = Color.Red,

                focusedPlaceholderColor = Color.Red,
                unfocusedPlaceholderColor = Color.Red,
                disabledPlaceholderColor = Color.Red,
                errorPlaceholderColor = Color.Red,

                focusedPrefixColor = Color.Red,
                unfocusedPrefixColor = Color.Red,
                disabledPrefixColor = Color.Red,
                errorPrefixColor = Color.Red,

                focusedSuffixColor = Color.Red,
                unfocusedSuffixColor = Color.Red,
                disabledSuffixColor = Color.Red,
                errorSuffixColor = Color.Red))
        ExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surface),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            itemRes.forEach { item ->
                val textItem = stringResource(item)
                DropdownMenuItem(
                    text = {
                        Text(text = textItem,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodyLarge)
                    },
                    onClick = {
                        onClickItem(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun DatePickerDialogPreview() {
    DatePickerDialogInCommonUi()
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogInCommonUi(
    openDialog: MutableState<Boolean> = remember { mutableStateOf(true) },
    onSelectedDate: (Long) -> Unit = {}
) {
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onSelectedDate(datePickerState.selectedDateMillis ?: 0L)
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun TextAndIconCardItemInCommonUi(){
    TimePickerDialog(onSelectedTime = {hour, minute, is24hour ->
        "$hour : $minute, $is24hour"
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit = {},
    openDialog: MutableState<Boolean> = remember { mutableStateOf(true) },
    onSelectedTime: (hour: Int, minute: Int, is24hour: Boolean) -> Unit,
) {
    if (openDialog.value) {
        val state = rememberTimePickerState()
        Dialog(
            onDismissRequest = {
                onCancel()
                openDialog.value = false
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
        ) {
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 6.dp,
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .background(
                        shape = MaterialTheme.shapes.extraLarge,
                        color = MaterialTheme.colorScheme.surface
                    ),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        text = title,
                        style = MaterialTheme.typography.labelMedium
                    )
                    TimePicker(
                        state = state,
                        modifier = Modifier,
                        layoutType = TimePickerLayoutType.Vertical
                    )
                    Row(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = {
                                onCancel()
                                openDialog.value = false
                            }
                        ) { Text("Cancel") }
                        TextButton(
                            onClick = {
                                onSelectedTime(state.hour, state.minute, state.is24hour)
                                openDialog.value = false
                            }
                        ) { Text("OK") }
                    }
                }
            }
        }
    }
}

@Composable
fun TextAndIconCardItemInCommonUi(
    modifier: Modifier = Modifier,
    text: String = "2023 - 08 - 23",
    onClickItem: () -> Unit = {},
    icon: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClickItem() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 13.dp, top = 16.dp, bottom = 16.dp)
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                text = text)
            icon()
        }
    }
}

@Composable
fun OutlinedTextFieldInCommonUi(
    modifier: Modifier = Modifier,
    value: String = "sample text",
    onValueChange: (String) -> Unit = {},
    placeholderText: String = "sample placeholder text"
) {
    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        maxLines = 1,
        placeholder = { Text(
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onTertiary,
            text = placeholderText) },
        shape = RoundedCornerShape(6.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary),
        value = value,
        onValueChange = {onValueChange(it)})
}

@Preview(showBackground = true)
@Composable
fun OutlinedTextFieldInCommonUiPreview() {
    OutlinedTextFieldInCommonUi()
}

@Composable
fun ButtonInCommonUi(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    onClick: () -> Unit = {},
    text: @Composable () -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.tertiaryContainer),
        shape = RoundedCornerShape(8.dp),
        onClick = {onClick()}
    ) {
        text()
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonInCommonUiPreview() {
    ButtonInCommonUi()
}

@Composable
fun CheckBoxInCommonUi(
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Checkbox(
        colors = CheckboxDefaults.colors(
            checkmarkColor = MaterialTheme.colorScheme.onSecondary,
            checkedColor = MaterialTheme.colorScheme.secondary,
            uncheckedColor = MaterialTheme.colorScheme.tertiary),
        checked = checked, onCheckedChange = {onCheckedChange(it)})
}

@Preview(showBackground = true)
@Composable
fun CheckBoxInCommonUiPreview() {
    CheckBoxInCommonUi()
}
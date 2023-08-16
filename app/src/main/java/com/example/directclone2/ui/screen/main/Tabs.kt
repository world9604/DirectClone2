package com.example.directclone2.ui.screen.main

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale

@Composable
fun Tabs(vm: MainViewModel) {
    TabRow(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(48.dp),
        selectedTabIndex = vm.currentTab.index) {
        vm.tabs.forEach { content ->
            Tab(
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onTertiary,
                selected = vm.currentTab.index == content.index,
                onClick = { vm.updateTab(content) },
                text = {
                    Text(
                        text = stringResource(content.textRes).uppercase(Locale.getDefault()),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun TabsPreview() {
    Tabs(vm = viewModel())
}
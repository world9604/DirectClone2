package com.example.directclone2.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import java.io.File
import java.util.Locale

@Composable
fun SyncContent(
    modifier: Modifier = Modifier,
    vm: MainViewModel = viewModel(factory = MainViewModel.Factory),
    onAppClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                text = stringResource(R.string.empty_backup_item))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondaryContainer,
                text = "Backup File Not Found.")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(48.sp)
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {vm.save()}
            ) {
                Text(
                    text = stringResource(R.string.sync_content_start_direct_clone_service_btn)
                        .uppercase(Locale.getDefault()),
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Preview(group="Test", heightDp = 800, showBackground = true)
@Composable
fun SyncPreview() {
    SyncContent()
}
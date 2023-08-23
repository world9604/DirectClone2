package com.example.directclone2.ui.screen.apps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.directclone2.R
import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.ui.NavigationDestination
import com.example.directclone2.ui.components.CardViewInCommonUi
import com.example.directclone2.ui.components.CardViewItemInCommonUi
import com.example.directclone2.ui.components.SecondaryToggleCardInCommonUi
import com.example.directclone2.ui.components.ToggleSwitchInCommonUi
import java.io.File


@Composable
fun AppsScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
         var openAppsCard by remember { mutableStateOf(false) }
        SecondaryToggleCardInCommonUi(
            modifier = modifier,
            text = "Enable 30 Apps",
            isOpen = openAppsCard,
            onClickItem = {openAppsCard = !openAppsCard}
        ) { modifier ->
            var apps = listOf("", "", "", "", "", "", "",)
            CardViewInCommonUi(
                modifier = modifier
            ) {
                apps.forEach { app ->
                    CardViewItemInCommonUi(modifier) {
                        Row (
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.Top
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_launcher_background),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .size(32.dp)
                                        .clip(CircleShape)
                                )
                                Column {
                                    Text(
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.secondary,
                                        text = "Android Auto")
                                    Text(
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                                        text = "230 KB")
                                }
                            }
                            ToggleSwitchInCommonUi()
                        }
                    }
                }
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun AppsPreview() {
    AppsScreen()
}
package com.example.directclone2.ui.screen.savednetworks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.directclone2.ui.components.CardViewInCommonUi
import java.util.Locale

@Composable
fun SavedNetworksScreen(
    modifier: Modifier = Modifier,
) {
    var wifis = listOf("", "", "", "")

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.bodyMedium,
            text = "WI-FI NETWORKS".uppercase(Locale.getDefault()))
        CardViewInCommonUi(modifier) {
            wifis.forEach { wifi ->
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        modifier = modifier.padding(end = 16.dp),
                        checked = true, onCheckedChange = {})
                    Row(
                        modifier = modifier.fillMaxWidth()
                            .padding(end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Pointmobile_Public_A")
                        Icon(
                            Icons.Outlined.Lock,
                            "WiFi Network Icon")
                    }
                }
            }
        }
    }
}

@Preview(group="Test", showBackground = true)
@Composable
fun SavedNetworksPreview() {
    SavedNetworksScreen()
}
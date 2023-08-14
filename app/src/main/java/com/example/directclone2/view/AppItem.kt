package com.example.directclone2.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppItem (
    val id: Int = 0,
    var packageName: String = "",
    var appName: String = "",
    var isPreInstalledApp: Boolean = false,
    initialSelected: Boolean = false
) {
    var selected: Boolean by mutableStateOf(initialSelected)
}
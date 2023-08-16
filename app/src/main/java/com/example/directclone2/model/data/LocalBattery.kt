package com.example.directclone2.model.data

import com.example.directclone2.ui.screen.battery.BatteryUiState
import com.google.gson.annotations.SerializedName

data class LocalBattery (
        @SerializedName("battery_low_warning_level")
        val batteryLowWarningLevel: String = "false",
        @SerializedName("battery_critical_warning_level")
        val batteryCriticalWarningLevel: String = "false",
)

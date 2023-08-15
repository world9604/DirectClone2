package com.example.directclone2.model.data

import com.example.directclone2.model.ConvertorUtil
import com.example.directclone2.ui.screen.battery.BatteryUiState
import com.google.gson.annotations.SerializedName

data class LocalBattery (
        @SerializedName("battery_low_warning_level")
        val batteryLowWarningLevel: String = "false",
        @SerializedName("battery_critical_warning_level")
        val batteryCriticalWarningLevel: String = "false",
)

fun List<LocalBattery>.toExternal() = map(LocalBattery::toExternal)

fun LocalBattery.toExternal() = BatteryUiState(
        batteryLowWarningLevel = ConvertorUtil.stringToFloat(batteryLowWarningLevel),
        batteryCriticalWarningLevel = ConvertorUtil.stringToFloat(batteryCriticalWarningLevel),
        smartCharging = BatteryUiState.SmartCharging.NormalMode.resIdOfTitle
)

fun BatteryUiState.toLocal() = LocalBattery(
        batteryLowWarningLevel = batteryLowWarningLevel.toString(),
        batteryCriticalWarningLevel = batteryCriticalWarningLevel.toString(),
)

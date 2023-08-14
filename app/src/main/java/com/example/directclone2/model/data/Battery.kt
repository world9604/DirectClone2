package com.example.directclone2.model.data

import com.example.directclone2.model.ConvertorUtil
import com.example.directclone2.viewmodel.ProfileUiState
import com.google.gson.annotations.SerializedName

data class Battery (
        @SerializedName("battery_low_warning_level")
        val batteryLowWarningLevel: String = "false",
        @SerializedName("battery_critical_warning_level")
        val batteryCriticalWarningLevel: String = "false",
)

fun List<Battery>.toExternal() = map(Battery::toExternal)

fun Battery.toExternal() = ProfileUiState(
        batteryLowWarningLevel = ConvertorUtil.stringToFloat(batteryLowWarningLevel),
        batteryCriticalWarningLevel = ConvertorUtil.stringToFloat(batteryCriticalWarningLevel),
)

fun ProfileUiState.toBattery() = Battery(
        batteryLowWarningLevel = batteryLowWarningLevel.toString(),
        batteryCriticalWarningLevel = batteryCriticalWarningLevel.toString(),
)

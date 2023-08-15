package com.example.directclone2.ui.screen.battery

import androidx.annotation.StringRes
import com.example.directclone2.R
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class BatteryUiState(
    val batteryLowWarningLevel: Float = 0f,
    val batteryCriticalWarningLevel: Float = 0f,
    val smartCharging: SmartCharging = SmartCharging.NormalMode,
    val isOkSmartChargingInfo: Boolean = false,
) {
    enum class SmartCharging (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        LifeXMode(R.string.smart_charging_life_x_mode_option, R.string.empty),
        NormalMode(R.string.smart_charging_normal_mode_option, R.string.empty),
        FastChargingMode(R.string.smart_charging_fast_charging_mode_option, R.string.empty),
    }
}

fun <T: Any> BatteryUiState.update(fieldToBeUpdated: String, value: T): BatteryUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as BatteryUiState
}
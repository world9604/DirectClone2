package com.example.directclone2.ui.screen.display

import androidx.annotation.StringRes
import com.example.directclone2.R
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class DisplayUiState(
    val screenBrightness: Float = 0f,
    val autoScreenRotate: Boolean = false,
    val adaptiveBrightness: Boolean = false,
    val systemFontSize: SystemFontSize = SystemFontSize.Default,
    val systemDisplaySize: SystemDisplaySize = SystemDisplaySize.Default,
    val touchSensitivity: TouchSensitivity = TouchSensitivity.Short,
) {
    enum class TouchSensitivity (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        Short(R.string.touch_sensitivity_short_option, R.string.empty),
        Medium(R.string.touch_sensitivity_medium_option, R.string.empty),
        Long(R.string.touch_sensitivity_long_option, R.string.empty)
    }

    enum class SystemFontSize (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        Small(R.string.font_size_small_option, R.string.empty),
        Default(R.string.font_size_default_option, R.string.empty),
        Large(R.string.font_size_large_option, R.string.empty),
        Largest(R.string.font_size_largest_option, R.string.empty)
    }

    enum class SystemDisplaySize (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        Small(R.string.display_size_small_option, R.string.empty),
        Default(R.string.display_size_default_option, R.string.empty),
        Large(R.string.display_size_large_option, R.string.empty),
        Largest(R.string.display_size_largest_option, R.string.empty)
    }
}

fun <T: Any> DisplayUiState.update(fieldToBeUpdated: String, value: T): DisplayUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as DisplayUiState
}
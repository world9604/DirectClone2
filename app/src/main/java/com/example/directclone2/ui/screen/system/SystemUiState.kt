package com.example.directclone2.ui.screen.system

import androidx.annotation.StringRes
import com.example.directclone2.R
import java.util.TimeZone
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class SystemUiState(
    val languages: String = "",
    val spellChecker: Boolean = false,
    val spellCheckLanguage: String = "when an unknown printer",
    val defaultSpellChecker: DefaultSpellChecker = DefaultSpellChecker.GboardSpellChecker,
    val spellCheckPointerSpeed: Float = 0f,
    val useNetworkProvidedTime: Boolean = false,
    val openDatePickerDialog: Boolean = false,
    val openTimePickerDialog: Boolean = false,
    val systemDate: String = "",
    val systemTime: String = "",
    val useNetworkProvidedTimeZone: Boolean = false,
    val timeZone: TimeZone = TimeZone.getDefault(),
    val use24hourFormat: Boolean = false,
    val ntpServer: String = "",
) {
    enum class DefaultSpellChecker(
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        GboardSpellChecker(R.string.default_spell_checker_gboard_spell_checker_option, R.string.empty)
    }
}

fun <T: Any> SystemUiState.update(fieldToBeUpdated: String, value: T): SystemUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as SystemUiState
}
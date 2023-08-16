package com.example.directclone2.ui.screen.networkandinternet

import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class NetworkAndInternetUiState(
    val wifi: Boolean = false,
    val savedNetworks: List<String> = listOf(""),
    val dataServer: Boolean = false,
    val roaming: Boolean = false,
    val ethernet: String = "",
    val vpn: String = "",
)

fun <T: Any> NetworkAndInternetUiState.update(fieldToBeUpdated: String, value: T): NetworkAndInternetUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as NetworkAndInternetUiState
}

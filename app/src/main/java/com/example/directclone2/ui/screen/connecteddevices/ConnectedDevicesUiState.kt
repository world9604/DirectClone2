package com.example.directclone2.ui.screen.connecteddevices

import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class ConnectedDevicesUiState(
    val bluetooth: Boolean = false,
    val nfc: Boolean = false,
)

fun <T: Any> ConnectedDevicesUiState.update(fieldToBeUpdated: String, value: T): ConnectedDevicesUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as ConnectedDevicesUiState
}

package com.example.directclone2.ui.screen.locationandsecurity

import androidx.annotation.StringRes
import com.example.directclone2.R
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class LocationAndSecurityUiState(
    val useLocation: Boolean = false,
    val currentScreenLockPinOrPassword: String = "",
    @StringRes val screenLock: Int = ScreenLock.None.resIdOfTitle,
    val screenLockPin: String = "",
    val screenLockPassword: String = "",
    val screenLockMessage: String = "",
    @StringRes val lockAfterScreenTimeout: Int = LockAfterScreenTimeout.Immediately.resIdOfTitle,
    @StringRes val powerButtonInstantlyLocks: Int = PowerButtonInstantlyLocks.Disable.resIdOfTitle,
) {
    enum class PowerButtonInstantlyLocks (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        Enable(R.string.power_button_instantly_locks_enable_option, R.string.empty),
        Disable(R.string.power_button_instantly_locks_disable_option, R.string.empty),
    }

    enum class LockAfterScreenTimeout (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        Immediately(R.string.lock_screen_timeout_immediately_option, R.string.empty),
        Sec5(R.string.lock_screen_timeout_5_sec_option, R.string.empty),
        Sec15(R.string.lock_screen_timeout_15_sec_option, R.string.empty),
        Sec30(R.string.lock_screen_timeout_30_sec_option, R.string.empty),
        Min1(R.string.lock_screen_timeout_1_min_option, R.string.empty),
        Min2(R.string.lock_screen_timeout_2_min_option, R.string.empty),
        Min5(R.string.lock_screen_timeout_5_min_option, R.string.empty),
        Min10(R.string.lock_screen_timeout_10_min_option, R.string.empty),
        Min30(R.string.lock_screen_timeout_30_min_option, R.string.empty),
    }

    enum class ScreenLock (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        None(R.string.screenlock_none_option, R.string.empty),
        Swipe(R.string.screenlock_swipe_option, R.string.empty),
        Pin(R.string.screenlock_pin_option, R.string.screenlock_pin_sub_option),
        Password(R.string.screenlock_password_option, R.string.screenlock_password_sub_option)
    }
}

fun <T: Any> LocationAndSecurityUiState.update(fieldToBeUpdated: String, value: T): LocationAndSecurityUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as LocationAndSecurityUiState
}
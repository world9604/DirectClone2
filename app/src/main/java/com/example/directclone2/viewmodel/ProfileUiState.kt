package com.example.directclone2.viewmodel

import androidx.annotation.StringRes
import com.example.directclone2.R
import java.util.TimeZone
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class ProfileUiState (
    var id: String = "0",

    /* ConnectedDevices Screen */
    val bluetooth: Boolean = false,
    val nfc: Boolean = false,

    /* Display Screen */
    val screenBrightness: Float = 0f,
    val autoScreenRotate: Boolean = false,
    val adaptiveBrightness: Boolean = false,
    @StringRes val systemFontSize: Int = SystemFontSize.Default.resIdOfTitle,
    @StringRes val systemDisplaySize: Int = SystemDisplaySize.Default.resIdOfTitle,
    @StringRes val touchSensitivity: Int = TouchSensitivity.Short.resIdOfTitle,

    /* Sound Screen */
    @StringRes val vibrateOnTouch: Int = VibrateOnTouch.Disable.resIdOfTitle,
    @StringRes val conversations: Int = Conversations.None.resIdOfTitle,
    @StringRes val messages: Int = Messages.None.resIdOfTitle,
    @StringRes val calls: Int = Calls.None.resIdOfTitle,
    val musicVolume: Float = 1f,
    val ringVolume: Float = 1f,
    val callVolume: Float = 1f,
    val notificationVolume: Float = 1f,
    val alarmVolume: Float = 1f,
    val alarms: Boolean = false,
    val mediaSounds: Boolean = false,
    val touchSounds: Boolean = false,
    val reminders: Boolean = false,
    val calendarEvents: Boolean = false,
    val dialPadTones: Boolean = false,
    val screenLockingSounds: Boolean = false,
    val chargingSoundsAndVibration: Boolean = false,
    val advancedTouchSounds: Boolean = false,
    val touchVibration: Boolean = false,

    /* Battery Screen */
    val batteryLowWarningLevel: Float = 0f,
    val batteryCriticalWarningLevel: Float = 0f,
    @StringRes val smartCharging: Int = SmartCharging.NormalMode.resIdOfTitle,
    val isOkSmartChargingInfo: Boolean = false,

    /* LocationAndSecurity Screen */
    val useLocation: Boolean = false,
    val currentScreenLockPinOrPassword: String = "",
    @StringRes val screenLock: Int = ScreenLock.None.resIdOfTitle,
    val screenLockPin: String = "",
    val screenLockPassword: String = "",
    val screenLockMessage: String = "",
    @StringRes val lockAfterScreenTimeout: Int = LockAfterScreenTimeout.Immediately.resIdOfTitle,
    @StringRes val powerButtonInstantlyLocks: Int = PowerButtonInstantlyLocks.Disable.resIdOfTitle,

    /* System Screen */
    val languages: String = "",
    val spellChecker: Boolean = false,
    val spellCheckLanguage: String = "when an unknown printer",
    @StringRes val defaultSpellChecker: Int = DefaultSpellChecker.GboardSpellChecker.resIdOfTitle,
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

    /* NetworkAndInternet Screen */
    val wifi: Boolean = false,
    val savedNetworks: List<String> = listOf(""),
    val dataSaver: Boolean = false,
    val roaming: Boolean = false,
    val ethernet: String = "",
    val vpn: String = "",

    /* MainScreen (Backup Content) */
    val backupFileName: String = "",
    val isInternalStorage: Boolean = false,
    val backupFileSaveLocation: String = "",
    val usePassword: Boolean = false,
    val password: String = "",
    val confirmPassword: String = "",
    val openBackupDialog: Boolean = false,
    val openSetPasswordDialog: Boolean = false,
    val openBackupResultDialog: Boolean = false,
    val isCompletedCreateBackupFile: Boolean = false,
)

fun <T: Any> ProfileUiState.update(fieldToBeUpdated: String, value: T): ProfileUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as ProfileUiState
}

enum class DefaultSpellChecker(
    @StringRes val resIdOfTitle: Int,
    @StringRes val resIdOfSubTitle: Int,
) {
    GboardSpellChecker(R.string.default_spell_checker_gboard_spell_checker_option, R.string.empty)
}

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

enum class VibrateOnTouch (
    @StringRes val resIdOfTitle: Int,
    @StringRes val resIdOfSubTitle: Int,
) {
    Enable(R.string.vibrate_on_touch_always_option, R.string.empty),
    Disable(R.string.vibrate_on_touch_never_option, R.string.empty)
}

enum class Conversations (
    @StringRes val resIdOfTitle: Int,
    @StringRes val resIdOfSubTitle: Int,
) {
    AllConversations(R.string.conversations_all_option, R.string.none),
    PriorityConversations(R.string.conversations_priority_option, R.string.none),
    None(R.string.none, R.string.empty)
}

enum class Calls (
    @StringRes val resIdOfTitle: Int,
    @StringRes val resIdOfSubTitle: Int,
) {
    StarredContacts(R.string.calls_starred_contacts_option, R.string.none),
    Contacts(R.string.calls_contacts_option, R.string.none),
    Anyone(R.string.calls_anyone_option, R.string.calls_anyone_sub_option),
    None(R.string.none, R.string.empty)
}

enum class Messages (
    @StringRes val resIdOfTitle: Int,
    @StringRes val resIdOfSubTitle: Int,
) {
    StarredContacts(R.string.messages_starred_contacts_option, R.string.none),
    Contacts(R.string.messages_contacts_option, R.string.none),
    Anyone(R.string.messages_anyone_option, R.string.messages_anyone_sub_option),
    None(R.string.none, R.string.empty)
}

enum class SmartCharging (
    @StringRes val resIdOfTitle: Int,
    @StringRes val resIdOfSubTitle: Int,
) {
    LifeXMode(R.string.smart_charging_life_x_mode_option, R.string.empty),
    NormalMode(R.string.smart_charging_normal_mode_option, R.string.empty),
    FastChargingMode(R.string.smart_charging_fast_charging_mode_option, R.string.empty),
}
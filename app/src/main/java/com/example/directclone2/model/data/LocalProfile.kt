package com.example.directclone2.model.data


import com.example.directclone2.model.ConvertorUtil.booleanToEnableOrDisable
import com.example.directclone2.model.ConvertorUtil.booleanToOnOrOff
import com.example.directclone2.model.ConvertorUtil.callsNameToRes
import com.example.directclone2.model.ConvertorUtil.callsResToName
import com.example.directclone2.model.ConvertorUtil.conversationsNameToRes
import com.example.directclone2.model.ConvertorUtil.conversationsResToName
import com.example.directclone2.model.ConvertorUtil.enableOrDisableToBoolean
import com.example.directclone2.model.ConvertorUtil.lockAfterScreenTimeoutNameToRes
import com.example.directclone2.model.ConvertorUtil.lockAfterScreenTimeoutResToName
import com.example.directclone2.model.ConvertorUtil.messagesNameToRes
import com.example.directclone2.model.ConvertorUtil.messagesResToName
import com.example.directclone2.model.ConvertorUtil.onOrOffToBoolean
import com.example.directclone2.model.ConvertorUtil.smartChargingNameToRes
import com.example.directclone2.model.ConvertorUtil.smartChargingResToName
import com.example.directclone2.model.ConvertorUtil.stringToBoolean
import com.example.directclone2.model.ConvertorUtil.stringToFloat
import com.example.directclone2.model.ConvertorUtil.systemDisplaySizeNameToRes
import com.example.directclone2.model.ConvertorUtil.systemDisplaySizeResToName
import com.example.directclone2.model.ConvertorUtil.systemFontSizeNameToRes
import com.example.directclone2.model.ConvertorUtil.systemFontSizeResToName
import com.example.directclone2.model.ConvertorUtil.touchSensitivityNameToRes
import com.example.directclone2.model.ConvertorUtil.touchSensitivityResToName
import com.example.directclone2.model.ConvertorUtil.vibrateOnTouchNameToRes
import com.example.directclone2.model.ConvertorUtil.vibrateOnTouchResToName
import com.example.directclone2.viewmodel.ProfileUiState
import com.google.gson.annotations.SerializedName


data class LocalProfile (
    val id: String,

    /* ConnectedDevices Screen */
    @SerializedName("Scan2StageEntity/wiress_config/bluetooth")
    var bluetooth: String = "ON",
    @SerializedName("Scan2StageEntity/wiress_config/nfc")
    var nfc: String = "ON",

    /* Display Screen */
    @SerializedName("Scan2StageEntity/system_config/set_screen_brightness")
    var screenBrightness: String = "90",
    @SerializedName("Scan2StageEntity/system_config/set_auto_rotate_screen")
    var autoScreenRotate: String = "Enable",
    @SerializedName("Scan2StageEntity/system_config/set_adaptive_brightness")
    var adaptiveBrightness: String = "Enable",
    @SerializedName("Scan2StageEntity/system_config/set_system_font_size")
    var systemFontSize: String = "Largest",
    @SerializedName("Scan2StageEntity/system_config/set_system_display_size")
    var systemDisplaySize: String = "Large",
    @SerializedName("Scan2StageEntity/system_config/set_touch_sensitivity")
    var touchSensitivity: String = "Wet",

    /* Sound Screen */
    @SerializedName("Scan2StageEntity/system_config/set_enabled_other_sounds_vibrate_on_touch")
    val vibrateOnTouch: String = "Disable",
    @SerializedName("Scan2StageEntity/system_config/set_volume_music")
    val musicVolume: String = "1",
    @SerializedName("Scan2StageEntity/system_config/set_volume_ring")
    val ringVolume: String = "1",
    @SerializedName("Scan2StageEntity/system_config/set_volume_call")
    val callVolume: String = "1",
    @SerializedName("Scan2StageEntity/system_config/set_volume_notification")
    val notificationVolume: String = "1",
    @SerializedName("Scan2StageEntity/system_config/set_volume_alarm")
    val alarmVolume: String = "1",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_conversations")
    val conversations: String = "None",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_messages")
    val messages: String = "None",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_calls")
    val calls: String = "None",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_alarms")
    val alarms: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_media_sounds")
    val mediaSounds: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_touch_sounds")
    val touchSounds: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_reminders")
    val reminders: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_calendar_events")
    val calendarEvents: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_dial_pad_tones")
    val dialPadTones: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_screen_locking_sounds")
    val screenLockingSounds: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_charging_sounds_and_vibration")
    val chargingSoundsAndVibration: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_advanced_touch_sounds")
    val advancedTouchSounds: String = "true",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_touch_vibration")
    val touchVibration: String = "true",

    /* Battery Screen */
    @SerializedName("Scan2StageEntity/battery/battery_low_warning_level")
    val batteryLowWarningLevel: String = "false",
    @SerializedName("Scan2StageEntity/battery/battery_critical_warning_level")
    val batteryCriticalWarningLevel: String = "false",
    @SerializedName("Scan2StageEntity/system_config/smart_charging")
    val smartCharging: String = "NormalMode",

    /* LocationAndSecurity Screen */
    @SerializedName("Scan2StageEntity/screenlock_config/lockType")
    val screenLock: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/gps")
    val useLocation: String = "OFF",
    @SerializedName("Scan2StageEntity/screenlock_config/oldLockPassword")
    val currentScreenLockPinOrPassword: String = "",
    @SerializedName("Scan2StageEntity/screenlock_config/lockTypePassword")
    val screenLockPinOrPassword: String = "",
    @SerializedName("Scan2StageEntity/screenlock_config/lockScreenMsg")
    val screenLockMessage: String = "",
    @SerializedName("Scan2StageEntity/screenlock_config/lockAfterTimeout")
    val lockAfterScreenTimeout: String = "",
    @SerializedName("Scan2StageEntity/screenlock_config/powerButtonInstantlyLocks")
    val powerButtonInstantlyLocks: String = "",

    /* System Screen */
    @SerializedName("Scan2StageEntity/system_config/default_spell_checker")
    val defaultSpellChecker: String = "GboardSpellChecker",
    @SerializedName("Scan2StageEntity/system_config/set_language")
    val languages: String = "",
    @SerializedName("Scan2StageEntity/system_config/spell_checker")
    val spellChecker: Boolean = false,
    @SerializedName("Scan2StageEntity/system_config/spell_check_language")
    val spellCheckLanguage: String = "",
    @SerializedName("Scan2StageEntity/system_config/spell_check_pointer_speed")
    val spellCheckPointerSpeed: Float = 0f,
    @SerializedName("Scan2StageEntity/datetime_config/use_network_provided_time")
    val useNetworkProvidedTime: String = "",
    @SerializedName("Scan2StageEntity/datetime_config/date_time_value")
    val systemDate: String = "",
    @SerializedName("Scan2StageEntity/datetime_config/time_value")
    val systemTime: String = "",
    @SerializedName("Scan2StageEntity/datetime_config/use_network_provided_timezone")
    val useNetworkProvidedTimeZone: String = "",
    @SerializedName("Scan2StageEntity/datetime_config/timezone")
    val timeZone: String = "",
    @SerializedName("Scan2StageEntity/datetime_config/use_24hour_format")
    val use24hourFormat: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_ntp_server")
    val ntpServer: String = "",

    /* NetworkAndInternet Screen */
    @SerializedName("Scan2StageEntity/wiress_config/wlan")
    val wifi: String = "OFF",
    @SerializedName("Scan2StageEntity/wiress_config/saved_networks")
    val savedNetworks: List<String> = listOf(""),
    @SerializedName("Scan2StageEntity/wiress_config/data_sever")
    val dataSever: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/roaming")
    val roaming: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/vpn")
    val vpn: String = "",
)

/*
fun LocalProfile.update(fieldToBeUpdated: String, value: String): LocalProfile {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as LocalProfile
}
 */

fun LocalProfile.toExternal() = ProfileUiState(
    id = id,
    bluetooth = onOrOffToBoolean(bluetooth),
    nfc = onOrOffToBoolean(nfc),
    screenBrightness = stringToFloat(screenBrightness),
    autoScreenRotate = enableOrDisableToBoolean(autoScreenRotate),
    adaptiveBrightness = enableOrDisableToBoolean(adaptiveBrightness),
    systemFontSize = systemFontSizeNameToRes(systemFontSize),
    systemDisplaySize = systemDisplaySizeNameToRes(systemDisplaySize),
    touchSensitivity = touchSensitivityNameToRes(touchSensitivity),
    vibrateOnTouch = vibrateOnTouchNameToRes(vibrateOnTouch),
    conversations = conversationsNameToRes(conversations),
    messages = messagesNameToRes(messages),
    calls = callsNameToRes(calls),
    musicVolume = stringToFloat(musicVolume),
    ringVolume = stringToFloat(ringVolume),
    callVolume = stringToFloat(callVolume),
    notificationVolume = stringToFloat(notificationVolume),
    alarmVolume = stringToFloat(alarmVolume),
    alarms = stringToBoolean(alarms),
    mediaSounds = stringToBoolean(mediaSounds),
    touchSounds = stringToBoolean(touchSounds),
    reminders = stringToBoolean(reminders),
    calendarEvents = stringToBoolean(calendarEvents),
    dialPadTones = stringToBoolean(dialPadTones),
    screenLockingSounds = stringToBoolean(screenLockingSounds),
    chargingSoundsAndVibration = stringToBoolean(chargingSoundsAndVibration),
    advancedTouchSounds = stringToBoolean(advancedTouchSounds),
    touchVibration = stringToBoolean(touchVibration),

    batteryLowWarningLevel = stringToFloat(batteryLowWarningLevel),
    batteryCriticalWarningLevel = stringToFloat(batteryCriticalWarningLevel),
    smartCharging = smartChargingNameToRes(smartCharging),

    lockAfterScreenTimeout = lockAfterScreenTimeoutNameToRes(lockAfterScreenTimeout),
)

fun List<LocalProfile>.toExternal() = map(LocalProfile::toExternal)

fun ProfileUiState.toLocal() = LocalProfile(
    id = id,
    bluetooth = booleanToOnOrOff(bluetooth),
    nfc = booleanToOnOrOff(nfc),
    screenBrightness = screenBrightness.toString(),
    autoScreenRotate = booleanToEnableOrDisable(autoScreenRotate),
    adaptiveBrightness = booleanToEnableOrDisable(adaptiveBrightness),
    systemFontSize = systemFontSizeResToName(systemFontSize),
    systemDisplaySize = systemDisplaySizeResToName(systemDisplaySize),
    touchSensitivity = touchSensitivityResToName(touchSensitivity),
    vibrateOnTouch = vibrateOnTouchResToName(vibrateOnTouch),
    conversations = conversationsResToName(conversations),
    messages = messagesResToName(messages),
    calls = callsResToName(calls),
    musicVolume = musicVolume.toString(),
    ringVolume = ringVolume.toString(),
    callVolume = callVolume.toString(),
    notificationVolume = notificationVolume.toString(),
    alarmVolume = alarmVolume.toString(),
    alarms = alarms.toString(),
    mediaSounds = mediaSounds.toString(),
    touchSounds = touchSounds.toString(),
    reminders = reminders.toString(),
    calendarEvents = calendarEvents.toString(),
    dialPadTones = dialPadTones.toString(),
    screenLockingSounds = screenLockingSounds.toString(),
    chargingSoundsAndVibration = chargingSoundsAndVibration.toString(),
    advancedTouchSounds = advancedTouchSounds.toString(),
    touchVibration = touchVibration.toString(),

    batteryLowWarningLevel = batteryLowWarningLevel.toString(),
    batteryCriticalWarningLevel = batteryCriticalWarningLevel.toString(),
    smartCharging = smartChargingResToName(smartCharging),

    lockAfterScreenTimeout = lockAfterScreenTimeoutResToName(lockAfterScreenTimeout),

    screenLockPinOrPassword = screenLockPin + screenLockPassword
)

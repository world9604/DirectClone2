package com.example.directclone2.model.data



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.directclone2.ui.screen.main.BackupFile
import com.google.gson.annotations.SerializedName
import java.io.File
import java.util.Date

@Entity(tableName = "profiles")
data class LocalProfile (
    @PrimaryKey
    val id: String,

    /* File Info */
    val fileName: String = "",
    val filePath: String = "",
    val createdDate: Date = Date(),
    val isFileCreated: Boolean = false,
    val password: String = "",

    /* BackupApp Info */
    val backupApps: List<LocalBackupApp> = listOf(),

    /* ConnectedDevices Screen */
    @SerializedName("Scan2StageEntity/wiress_config/bluetooth")
    var bluetooth: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/nfc")
    var nfc: String = "",

    /* Display Screen */
    @SerializedName("Scan2StageEntity/system_config/set_screen_brightness")
    var screenBrightness: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_auto_rotate_screen")
    var autoScreenRotate: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_adaptive_brightness")
    var adaptiveBrightness: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_system_font_size")
    var systemFontSize: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_system_display_size")
    var systemDisplaySize: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_touch_sensitivity")
    var touchSensitivity: String = "",

    /* Sound Screen */
    @SerializedName("Scan2StageEntity/system_config/set_enabled_other_sounds_vibrate_on_touch")
    val vibrateOnTouch: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_volume_music")
    val musicVolume: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_volume_ring")
    val ringVolume: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_volume_call")
    val callVolume: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_volume_notification")
    val notificationVolume: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_volume_alarm")
    val alarmVolume: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_conversations")
    val conversations: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_messages")
    val messages: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_calls")
    val calls: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/people_alarms")
    val alarms: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_media_sounds")
    val mediaSounds: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_touch_sounds")
    val touchSounds: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_reminders")
    val reminders: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/alarms_calendar_events")
    val calendarEvents: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_dial_pad_tones")
    val dialPadTones: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_screen_locking_sounds")
    val screenLockingSounds: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_charging_sounds_and_vibration")
    val chargingSoundsAndVibration: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_advanced_touch_sounds")
    val advancedTouchSounds: String = "",
    @SerializedName("Scan2StageEntity/do_not_disturb/advanced_touch_vibration")
    val touchVibration: String = "",

    /* Battery Screen */
    @SerializedName("Scan2StageEntity/battery/battery_low_warning_level")
    val batteryLowWarningLevel: String = "",
    @SerializedName("Scan2StageEntity/battery/battery_critical_warning_level")
    val batteryCriticalWarningLevel: String = "",
    @SerializedName("Scan2StageEntity/system_config/smart_charging")
    val smartCharging: String = "",

    /* LocationAndSecurity Screen */
    @SerializedName("Scan2StageEntity/screenlock_config/lockType")
    val screenLock: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/gps")
    val useLocation: String = "",
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
    val defaultSpellChecker: String = "",
    @SerializedName("Scan2StageEntity/system_config/set_language")
    val languages: String = "American English / en-US",
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
    val wifi: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/saved_networks")
    val savedNetworks: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/data_server")
    val dataServer: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/roaming")
    val roaming: String = "",
    @SerializedName("Scan2StageEntity/wiress_config/vpn")
    val vpn: String = "",
)

fun List<LocalProfile>.toExternal() = map(LocalProfile::toExternal)

fun LocalProfile.toExternal() = BackupFile(
    profileId = id,
    file = File(filePath, fileName),
    createdDate = createdDate,
    password = password,
)
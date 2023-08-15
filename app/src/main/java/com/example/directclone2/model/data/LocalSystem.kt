package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class LocalSystem (
    @SerializedName("default_spell_checker")
    val defaultSpellChecker: String = "GboardSpellChecker",
    @SerializedName("set_language")
    val languages: String = "",
    @SerializedName("spell_checker")
    val spellChecker: Boolean = false,
    @SerializedName("spell_check_language")
    val spellCheckLanguage: String = "",
    @SerializedName("spell_check_pointer_speed")
    val spellCheckPointerSpeed: Float = 0f,
    @SerializedName("set_screen_brightness")
    var screenBrightness: String = "90",
    @SerializedName("set_auto_rotate_screen")
    var autoScreenRotate: String = "Enable",
    @SerializedName("set_adaptive_brightness")
    var adaptiveBrightness: String = "Enable",
    @SerializedName("set_system_font_size")
    var systemFontSize: String = "Largest",
    @SerializedName("set_system_display_size")
    var systemDisplaySize: String = "Large",
    @SerializedName("set_touch_sensitivity")
    var touchSensitivity: String = "Wet",
    @SerializedName("set_enabled_other_sounds_vibrate_on_touch")
    val vibrateOnTouch: String = "Disable",
    @SerializedName("set_volume_music")
    val musicVolume: String = "1",
    @SerializedName("set_volume_ring")
    val ringVolume: String = "1",
    @SerializedName("set_volume_call")
    val callVolume: String = "1",
    @SerializedName("set_volume_notification")
    val notificationVolume: String = "1",
    @SerializedName("set_volume_alarm")
    val alarmVolume: String = "1",
    @SerializedName("smart_charging")
    val smartCharging: String = "NormalMode",
    @SerializedName("set_ntp_server")
    val ntpServer: String = "",
)
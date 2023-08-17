package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class LocalDoNotDisturb (
    @SerializedName("people_conversations")
    val conversations: String = "None",
    @SerializedName("people_messages")
    val messages: String = "None",
    @SerializedName("people_calls")
    val calls: String = "None",
    @SerializedName("people_alarms")
    val alarms: String = "true",
    @SerializedName("alarms_media_sounds")
    val mediaSounds: String = "true",
    @SerializedName("alarms_touch_sounds")
    val touchSounds: String = "true",
    @SerializedName("alarms_reminders")
    val reminders: String = "true",
    @SerializedName("alarms_calendar_events")
    val calendarEvents: String = "true",
    @SerializedName("advanced_dial_pad_tones")
    val dialPadTones: String = "true",
    @SerializedName("advanced_screen_locking_sounds")
    val screenLockingSounds: String = "true",
    @SerializedName("advanced_charging_sounds_and_vibration")
    val chargingSoundsAndVibration: String = "true",
    @SerializedName("advanced_advanced_touch_sounds")
    val advancedTouchSounds: String = "true",
    @SerializedName("advanced_touch_vibration")
    val touchVibration: String = "true",
)
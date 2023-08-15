package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class LocalDoNotDisturb (
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
)
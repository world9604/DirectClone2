package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class Scan2Stage(
    @SerializedName("battery")
    val battery: Battery,
    @SerializedName("datetime_config")
    val dateTime: DateTime,
    @SerializedName("do_not_disturb")
    val doNotDisturb: DoNotDisturb,
    @SerializedName("screenlock_config")
    val screenLock: ScreenLock,
    @SerializedName("system_config")
    val system: System,
    @SerializedName("wiress_config")
    val wireless: Wireless,
)

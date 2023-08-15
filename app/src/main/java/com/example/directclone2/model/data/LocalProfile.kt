package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class LocalProfile(
    @SerializedName("battery")
    val localBattery: LocalBattery = LocalBattery(),
    @SerializedName("datetime_config")
    val localDateTime: LocalDateTime = LocalDateTime(),
    @SerializedName("do_not_disturb")
    val localDoNotDisturb: LocalDoNotDisturb = LocalDoNotDisturb(),
    @SerializedName("screenlock_config")
    val localScreenLock: LocalScreenLock = LocalScreenLock(),
    @SerializedName("system_config")
    val localSystem: LocalSystem = LocalSystem(),
    @SerializedName("wiress_config")
    val localWireless: LocalWireless = LocalWireless(),
)

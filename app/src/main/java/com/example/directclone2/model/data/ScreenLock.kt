package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class ScreenLock (
    @SerializedName("Scan2StageEntity/screenlock_config/lockType")
    val screenLock: String = "",
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
)
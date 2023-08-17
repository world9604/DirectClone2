package com.example.directclone2.model.data

import com.google.gson.annotations.SerializedName

data class LocalScreenLock (
    @SerializedName("lockType")
    val screenLock: String = "",
    @SerializedName("oldLockPassword")
    val currentScreenLockPinOrPassword: String = "",
    @SerializedName("lockTypePassword")
    val screenLockPinOrPassword: String = "",
    @SerializedName("lockScreenMsg")
    val screenLockMessage: String = "",
    @SerializedName("lockAfterTimeout")
    val lockAfterScreenTimeout: String = "",
    @SerializedName("powerButtonInstantlyLocks")
    val powerButtonInstantlyLocks: String = "",
)
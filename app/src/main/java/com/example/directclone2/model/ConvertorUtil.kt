package com.example.directclone2.model

import androidx.annotation.StringRes
import com.example.directclone2.ui.screen.sound.Calls
import com.example.directclone2.ui.screen.sound.Conversations
import com.example.directclone2.ui.screen.locationandsecurity.LockAfterScreenTimeout
import com.example.directclone2.ui.screen.sound.Messages
import com.example.directclone2.ui.screen.battery.SmartCharging
import com.example.directclone2.ui.screen.display.SystemDisplaySize
import com.example.directclone2.ui.screen.display.SystemFontSize
import com.example.directclone2.ui.screen.display.TouchSensitivity
import com.example.directclone2.ui.screen.sound.VibrateOnTouch

object ConvertorUtil {
    private const val KEEP_OPTION_STR = "Keep Current Option"
    private const val KEEP_OPTION_INT = 6767
    private const val KEEP_OPTION_FLOAT = 6767f

    fun stringToInt(value: String): Int {
        if (KEEP_OPTION_STR == value) return KEEP_OPTION_INT
        return value.toInt()
    }

    fun stringToBoolean(value: String): Boolean {
        return when (value) {
            "true" -> true
            "false" -> false
            else -> false
        }
    }

    fun stringToFloat(value: String): Float {
        if (KEEP_OPTION_STR == value) return KEEP_OPTION_FLOAT
        return value.toFloat()
    }

    fun booleanToOnOrOff(value: Boolean): String {
        return when (value) {
            true -> "ON"
            false -> "OFF"
        }
    }

    fun onOrOffToBoolean(value: String): Boolean {
        return when (value) {
            "ON" -> true
            "OFF" -> false
            else -> false
        }
    }

    fun booleanToEnableOrDisable(value: Boolean): String {
        return when (value) {
            true -> "Enable"
            false -> "Disable"
        }
    }

    fun enableOrDisableToBoolean(value: String): Boolean {
        return when (value) {
            "Enable" -> true
            "Disable" -> false
            else -> false
        }
    }

    @StringRes
    fun lockAfterScreenTimeoutNameToRes(value: String): Int {
        return when (value) {
            LockAfterScreenTimeout.Immediately.name -> LockAfterScreenTimeout.Immediately.resIdOfTitle
            LockAfterScreenTimeout.Sec5.name -> LockAfterScreenTimeout.Sec5.resIdOfTitle
            LockAfterScreenTimeout.Sec15.name -> LockAfterScreenTimeout.Sec15.resIdOfTitle
            LockAfterScreenTimeout.Sec30.name -> LockAfterScreenTimeout.Sec30.resIdOfTitle
            LockAfterScreenTimeout.Min1.name -> LockAfterScreenTimeout.Min1.resIdOfTitle
            LockAfterScreenTimeout.Min2.name -> LockAfterScreenTimeout.Min2.resIdOfTitle
            LockAfterScreenTimeout.Min5.name -> LockAfterScreenTimeout.Min5.resIdOfTitle
            LockAfterScreenTimeout.Min10.name -> LockAfterScreenTimeout.Min10.resIdOfTitle
            LockAfterScreenTimeout.Min30.name -> LockAfterScreenTimeout.Min30.resIdOfTitle
            else -> LockAfterScreenTimeout.Immediately.resIdOfTitle
        }
    }

    fun lockAfterScreenTimeoutResToName(@StringRes value: Int): String {
        return when (value) {
            LockAfterScreenTimeout.Immediately.resIdOfTitle -> LockAfterScreenTimeout.Immediately.name
            LockAfterScreenTimeout.Sec5.resIdOfTitle -> LockAfterScreenTimeout.Sec5.name
            LockAfterScreenTimeout.Sec15.resIdOfTitle -> LockAfterScreenTimeout.Sec15.name
            LockAfterScreenTimeout.Sec30.resIdOfTitle -> LockAfterScreenTimeout.Sec30.name
            LockAfterScreenTimeout.Min1.resIdOfTitle -> LockAfterScreenTimeout.Min1.name
            LockAfterScreenTimeout.Min2.resIdOfTitle -> LockAfterScreenTimeout.Min2.name
            LockAfterScreenTimeout.Min5.resIdOfTitle -> LockAfterScreenTimeout.Min5.name
            LockAfterScreenTimeout.Min10.resIdOfTitle -> LockAfterScreenTimeout.Min10.name
            LockAfterScreenTimeout.Min30.resIdOfTitle -> LockAfterScreenTimeout.Min30.name
            else -> LockAfterScreenTimeout.Immediately.name
        }
    }

    @StringRes
    fun systemFontSizeNameToRes(value: String): Int {
        return when (value) {
            SystemFontSize.Small.name -> SystemFontSize.Small.resIdOfTitle
            SystemFontSize.Default.name -> SystemFontSize.Default.resIdOfTitle
            SystemFontSize.Large.name -> SystemFontSize.Large.resIdOfTitle
            SystemFontSize.Largest.name -> SystemFontSize.Largest.resIdOfTitle
            else -> SystemFontSize.Default.resIdOfTitle
        }
    }

    fun systemFontSizeResToName(@StringRes value: Int): String {
        return when (value) {
            SystemFontSize.Small.resIdOfTitle -> SystemFontSize.Small.name
            SystemFontSize.Default.resIdOfTitle -> SystemFontSize.Default.name
            SystemFontSize.Large.resIdOfTitle -> SystemFontSize.Large.name
            SystemFontSize.Largest.resIdOfTitle -> SystemFontSize.Largest.name
            else -> SystemFontSize.Default.name
        }
    }

    @StringRes
    fun systemDisplaySizeNameToRes(value: String): Int {
        return when (value) {
            SystemDisplaySize.Small.name -> SystemDisplaySize.Small.resIdOfTitle
            SystemDisplaySize.Default.name -> SystemDisplaySize.Default.resIdOfTitle
            SystemDisplaySize.Large.name -> SystemDisplaySize.Large.resIdOfTitle
            SystemDisplaySize.Largest.name -> SystemDisplaySize.Largest.resIdOfTitle
            else -> SystemDisplaySize.Default.resIdOfTitle
        }
    }

    fun systemDisplaySizeResToName(@StringRes value: Int): String {
        return when (value) {
            SystemDisplaySize.Small.resIdOfTitle -> SystemDisplaySize.Small.name
            SystemDisplaySize.Default.resIdOfTitle -> SystemDisplaySize.Default.name
            SystemDisplaySize.Large.resIdOfTitle -> SystemDisplaySize.Large.name
            SystemDisplaySize.Largest.resIdOfTitle -> SystemDisplaySize.Largest.name
            else -> SystemDisplaySize.Default.name
        }
    }

    @StringRes
    fun touchSensitivityNameToRes(value: String): Int {
        return when (value) {
            TouchSensitivity.Short.name -> TouchSensitivity.Short.resIdOfTitle
            TouchSensitivity.Medium.name -> TouchSensitivity.Medium.resIdOfTitle
            TouchSensitivity.Long.name -> TouchSensitivity.Long.resIdOfTitle
            else -> TouchSensitivity.Medium.resIdOfTitle
        }
    }

    fun touchSensitivityResToName(@StringRes value: Int): String {
        return when (value) {
            TouchSensitivity.Short.resIdOfTitle -> TouchSensitivity.Short.name
            TouchSensitivity.Medium.resIdOfTitle -> TouchSensitivity.Medium.name
            TouchSensitivity.Long.resIdOfTitle -> TouchSensitivity.Long.name
            else -> TouchSensitivity.Medium.name
        }
    }

    @StringRes
    fun vibrateOnTouchNameToRes(value: String): Int {
        return when (value) {
            VibrateOnTouch.Enable.name -> VibrateOnTouch.Enable.resIdOfTitle
            VibrateOnTouch.Disable.name -> VibrateOnTouch.Disable.resIdOfTitle
            else -> VibrateOnTouch.Disable.resIdOfTitle
        }
    }

    fun vibrateOnTouchResToName(@StringRes value: Int): String {
        return when (value) {
            VibrateOnTouch.Enable.resIdOfTitle -> VibrateOnTouch.Enable.name
            VibrateOnTouch.Disable.resIdOfTitle -> VibrateOnTouch.Disable.name
            else -> VibrateOnTouch.Disable.name
        }
    }

    @StringRes
    fun conversationsNameToRes(value: String): Int {
        return when (value) {
            Conversations.AllConversations.name -> Conversations.AllConversations.resIdOfTitle
            Conversations.PriorityConversations.name -> Conversations.PriorityConversations.resIdOfTitle
            Conversations.None.name -> Conversations.None.resIdOfTitle
            else -> Conversations.None.resIdOfTitle
        }
    }

    fun conversationsResToName(@StringRes value: Int): String {
        return when (value) {
            Conversations.AllConversations.resIdOfTitle -> Conversations.AllConversations.name
            Conversations.PriorityConversations.resIdOfTitle -> Conversations.PriorityConversations.name
            Conversations.None.resIdOfTitle -> Conversations.None.name
            else -> Conversations.None.name
        }
    }

    @StringRes
    fun messagesNameToRes(value: String): Int {
        return when (value) {
            Messages.StarredContacts.name -> Messages.StarredContacts.resIdOfTitle
            Messages.Contacts.name -> Messages.Contacts.resIdOfTitle
            Messages.Anyone.name -> Messages.Anyone.resIdOfTitle
            Messages.None.name -> Messages.None.resIdOfTitle
            else -> Messages.None.resIdOfTitle
        }
    }

    fun messagesResToName(@StringRes value: Int): String {
        return when (value) {
            Messages.StarredContacts.resIdOfTitle -> Messages.StarredContacts.name
            Messages.Contacts.resIdOfTitle -> Messages.Contacts.name
            Messages.Anyone.resIdOfTitle -> Messages.Anyone.name
            Messages.None.resIdOfTitle -> Messages.None.name
            else -> Messages.None.name
        }
    }

    @StringRes
    fun callsNameToRes(value: String): Int {
        return when (value) {
            Calls.StarredContacts.name -> Calls.StarredContacts.resIdOfTitle
            Calls.Contacts.name -> Calls.Contacts.resIdOfTitle
            Calls.Anyone.name -> Calls.Anyone.resIdOfTitle
            Calls.None.name -> Calls.None.resIdOfTitle
            else -> Calls.None.resIdOfTitle
        }
    }

    fun callsResToName(@StringRes value: Int): String {
        return when (value) {
            Calls.StarredContacts.resIdOfTitle -> Calls.StarredContacts.name
            Calls.Contacts.resIdOfTitle -> Calls.Contacts.name
            Calls.Anyone.resIdOfTitle -> Calls.Anyone.name
            Calls.None.resIdOfTitle -> Calls.None.name
            else -> Calls.None.name
        }
    }

    @StringRes
    fun smartChargingNameToRes(value: String): Int {
        return when (value) {
            SmartCharging.LifeXMode.name -> SmartCharging.LifeXMode.resIdOfTitle
            SmartCharging.NormalMode.name -> SmartCharging.NormalMode.resIdOfTitle
            SmartCharging.FastChargingMode.name -> SmartCharging.FastChargingMode.resIdOfTitle
            else -> SmartCharging.NormalMode.resIdOfTitle
        }
    }

    fun smartChargingResToName(@StringRes value: Int): String {
        return when (value) {
            SmartCharging.LifeXMode.resIdOfTitle -> SmartCharging.LifeXMode.name
            SmartCharging.NormalMode.resIdOfTitle -> SmartCharging.NormalMode.name
            SmartCharging.FastChargingMode.resIdOfTitle -> SmartCharging.FastChargingMode.name
            else -> SmartCharging.NormalMode.name
        }
    }
}
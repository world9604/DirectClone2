package com.example.directclone2.ui.screen.sound

import androidx.annotation.StringRes
import com.example.directclone2.R
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class SoundUiState(
    @StringRes val vibrateOnTouch: Int = VibrateOnTouch.Disable.resIdOfTitle,
    @StringRes val conversations: Int = Conversations.None.resIdOfTitle,
    @StringRes val messages: Int = Messages.None.resIdOfTitle,
    @StringRes val calls: Int = Calls.None.resIdOfTitle,
    val musicVolume: Float = 1f,
    val ringVolume: Float = 1f,
    val callVolume: Float = 1f,
    val notificationVolume: Float = 1f,
    val alarmVolume: Float = 1f,
    val alarms: Boolean = false,
    val mediaSounds: Boolean = false,
    val touchSounds: Boolean = false,
    val reminders: Boolean = false,
    val calendarEvents: Boolean = false,
    val dialPadTones: Boolean = false,
    val screenLockingSounds: Boolean = false,
    val chargingSoundsAndVibration: Boolean = false,
    val advancedTouchSounds: Boolean = false,
    val touchVibration: Boolean = false,
) {
    enum class VibrateOnTouch (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        Enable(R.string.vibrate_on_touch_always_option, R.string.empty),
        Disable(R.string.vibrate_on_touch_never_option, R.string.empty)
    }

    enum class Conversations (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        AllConversations(R.string.conversations_all_option, R.string.none),
        PriorityConversations(R.string.conversations_priority_option, R.string.none),
        None(R.string.none, R.string.empty)
    }

    enum class Calls (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        StarredContacts(R.string.calls_starred_contacts_option, R.string.none),
        Contacts(R.string.calls_contacts_option, R.string.none),
        Anyone(R.string.calls_anyone_option, R.string.calls_anyone_sub_option),
        None(R.string.none, R.string.empty)
    }

    enum class Messages (
        @StringRes val resIdOfTitle: Int,
        @StringRes val resIdOfSubTitle: Int,
    ) {
        StarredContacts(R.string.messages_starred_contacts_option, R.string.none),
        Contacts(R.string.messages_contacts_option, R.string.none),
        Anyone(R.string.messages_anyone_option, R.string.messages_anyone_sub_option),
        None(R.string.none, R.string.empty)
    }
}

fun <T: Any> SoundUiState.update(fieldToBeUpdated: String, value: T): SoundUiState {
    val copy = this::class.memberFunctions.first { it.name == "copy" }
    val instanceParameter = copy.instanceParameter!!
    val parameterToBeUpdated = copy.parameters.first { it.name == fieldToBeUpdated }
    return copy.callBy(mapOf(instanceParameter to this, parameterToBeUpdated to value)) as SoundUiState
}
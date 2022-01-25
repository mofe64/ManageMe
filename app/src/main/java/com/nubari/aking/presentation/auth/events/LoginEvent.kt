package com.nubari.aking.presentation.auth.events

sealed class LoginEvent {
    data class EnteredUsername(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()
    object UsernameFocusChange : LoginEvent()
    object PasswordFocusChange : LoginEvent()
}

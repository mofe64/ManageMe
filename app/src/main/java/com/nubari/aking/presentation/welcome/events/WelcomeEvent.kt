package com.nubari.aking.presentation.welcome.events

sealed class WelcomeEvent {
    data class SlideChange(val index: Int) : WelcomeEvent()
}
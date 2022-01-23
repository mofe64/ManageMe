package com.nubari.aking.welcome.events

sealed class WelcomeEvent {
    data class SlideChange(val index: Int) : WelcomeEvent()
}
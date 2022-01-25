package com.nubari.aking.presentation.welcome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nubari.aking.presentation.welcome.events.WelcomeEvent
import com.nubari.aking.presentation.welcome.state.WelcomeState

class WelcomeVieModel : ViewModel() {
    private val _welcomeState: MutableLiveData<WelcomeState> by lazy {
        MutableLiveData<WelcomeState>(WelcomeState())
    }
    val welcomeState: LiveData<WelcomeState>
        get() = _welcomeState


    fun createEvent(event: WelcomeEvent) {
        onEvent(event)
    }

    private fun onEvent(event: WelcomeEvent) {
        when (event) {
            is WelcomeEvent.SlideChange -> {
                _welcomeState.value = welcomeState.value?.copy(
                    currentScreen = event.index
                )
            }
        }
    }
}
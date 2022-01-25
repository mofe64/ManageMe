package com.nubari.aking.presentation.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nubari.aking.presentation.auth.events.AuthEvent
import com.nubari.aking.presentation.auth.state.AuthState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _state = MutableLiveData(AuthState())
    val state: LiveData<AuthState>
        get() = _state


    fun createEvent(event: AuthEvent) {
        onEvent(event)
    }

    private fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> {
                viewModelScope.launch {
                    _state.value = state.value?.copy(
                        isProcessing = true,
                    )
                    delay(1000)
                    _state.value = state.value?.copy(
                        isProcessing = true,
                        isAuthenticated = true
                    )
                }
            }
            is AuthEvent.Register -> {
                viewModelScope.launch {
                    _state.value = state.value?.copy(
                        isProcessing = true
                    )
                    delay(3000)
                    _state.value = state.value?.copy(
                        isProcessing = false,
                        isAuthenticated = true
                    )

                }
            }
        }
    }
}

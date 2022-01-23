package com.nubari.aking.auth.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.nubari.aking.auth.events.LoginEvent
import com.nubari.aking.auth.state.InputState
import com.nubari.aking.auth.state.InputType
import com.nubari.aking.auth.util.ValidationResponse


class LoginViewModel() : ViewModel() {

    private val _usernameState = MutableLiveData(
        InputState(
            type = InputType.EMAIL
        )
    )

    val usernameState: LiveData<InputState>
        get() = _usernameState

    private val _passwordState = MutableLiveData(
        InputState(
            type = InputType.PASSWORD
        )
    )
    val passwordState: LiveData<InputState>
        get() = _passwordState

    fun createEvent(event: LoginEvent) {
        onEvent(event)
    }

    private fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredUsername -> {
                _usernameState.value = usernameState.value?.copy(
                    text = event.value
                )
            }
            is LoginEvent.EnteredPassword -> {
                _passwordState.value = usernameState.value?.copy(
                    text = event.value
                )
            }
            is LoginEvent.UsernameFocusChange -> {
                usernameState.value?.let { usernameState ->
                    if (usernameState.touched) {
                        val status = validateInput(InputType.EMAIL, usernameState.text)
                        _usernameState.value = usernameState.copy(
                            isValid = status.isValid,
                            errorMessage = status.errorMessage
                        )
                    } else {
                        _usernameState.value = usernameState.copy(
                            touched = true
                        )
                    }
                }

            }
            is LoginEvent.PasswordFocusChange -> {
                passwordState.value?.let { passwordState ->
                    if (passwordState.touched) {
                        val status =
                            validateInput(InputType.PASSWORD, passwordState.text)
                        _passwordState.value = passwordState.copy(
                            isValid = status.isValid,
                            errorMessage = status.errorMessage
                        )
                    } else {
                        _passwordState.value = passwordState.copy(
                            touched = true
                        )
                    }
                }

            }

        }
    }

    private fun validateInput(type: InputType, value: String): ValidationResponse {
        when (type) {
            InputType.EMAIL -> {
                return if (!TextUtils.isEmpty(value) && Patterns.EMAIL_ADDRESS.matcher(
                        value
                    ).matches()
                ) {
                    ValidationResponse(true)
                } else {
                    ValidationResponse(false, "Enter valid email")
                }
            }
            InputType.PASSWORD -> {
                return if (!TextUtils.isEmpty(value) && value.length > 5) {
                    ValidationResponse(true)
                } else {
                    ValidationResponse(false, "Password should be greater than 5 chars")
                }

            }
            InputType.TEXT -> {
                return if (!TextUtils.isEmpty(value)) {
                    ValidationResponse(true)
                } else {
                    ValidationResponse(false, "Can't be empty")
                }

            }
        }
    }
}
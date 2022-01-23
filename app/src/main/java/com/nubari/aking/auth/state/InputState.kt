package com.nubari.aking.auth.state

data class InputState(
    val text: String = "",
    val isValid: Boolean = true,
    val errorMessage: String = "",
    val type: InputType,
    val touched: Boolean = false
)

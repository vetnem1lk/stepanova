package com.example.stepanova

data class LoginFormState(
    val usernameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confPasswordError: String? = null,
    val isDataValid: Boolean = false
)

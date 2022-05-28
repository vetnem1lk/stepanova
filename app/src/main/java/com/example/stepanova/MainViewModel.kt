package com.example.stepanova

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun loginDataChanged(username: String, password: String, email: String, confPass: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = "Логин должен быть не менее 8 символов")
        }
        if (!isEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = "Неверная почта")
        }
        if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = "Пароль должен содержать 1 букву и 1 цифру. Не менее 8 символов")
        }
        if (password != confPass) {
            _loginForm.value = LoginFormState(confPasswordError = "пароли не совпадают")
        }
        if (isUserNameValid(username) && isEmailValid(email) && isPasswordValid(password) && password == confPass){
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.length > 7
    }

    private fun isEmailValid(email: String): Boolean {
        return email.length > 11 && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        var isNumber = false
        var isLetter = false
        for (char in password) {
            if (char.isDigit())
                isNumber = true
            if (char.isLetter())
                isLetter = true
        }
        return password.length > 7 && isLetter && isNumber
    }
}
package com.example.stepanova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stepanova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val username = binding.username
        val password = binding.password
        val email = binding.email
        val confPass = binding.confPass
        val login = binding.login

        login.setOnClickListener {
            Toast.makeText(applicationContext, "Done!", Toast.LENGTH_SHORT).show()
        }

        viewModel.loginFormState.observe(this, Observer {
            val loginState = it ?: return@Observer

            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = loginState.usernameError
            }
            if (loginState.passwordError != null) {
                password.error = loginState.passwordError
            }
            if (loginState.confPasswordError != null) {
                confPass.error = loginState.confPasswordError
            }
            if (loginState.emailError != null) {
                email.error = loginState.emailError
            }
        })

        username.afterTextChanged {
            viewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                confPass.text.toString(),
            )
        }
        email.afterTextChanged {
            viewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                confPass.text.toString(),
            )
        }
        password.afterTextChanged {
            viewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                confPass.text.toString(),
            )
        }
        confPass.afterTextChanged {
            viewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                confPass.text.toString(),
            )
        }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}
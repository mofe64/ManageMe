package com.nubari.aking.presentation.auth.screens

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.nubari.aking.R
import com.nubari.aking.presentation.auth.events.AuthEvent
import com.nubari.aking.presentation.auth.events.LoginEvent
import com.nubari.aking.presentation.auth.viewmodel.AuthViewModel
import com.nubari.aking.presentation.auth.viewmodel.LoginViewModel

import com.nubari.aking.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var authViewModel: AuthViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.backArrowBtn.setOnClickListener {
            it.findNavController().navigateUp()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading
        loginButton.isEnabled = false

        loginViewModel.usernameState.observe(viewLifecycleOwner,
            Observer { usernameState ->
                if (usernameState == null) {
                    return@Observer
                }
                loginButton.isEnabled = usernameState.isValid
                if (!usernameState.isValid) {
                    usernameEditText.error = usernameState.errorMessage
                } else {
                    usernameEditText.error = null
                }


            }
        )
        usernameEditText.setOnFocusChangeListener { _, _ ->
            loginViewModel.createEvent(LoginEvent.UsernameFocusChange)
        }
        usernameEditText.afterTextChanged {
            loginViewModel.createEvent(LoginEvent.EnteredUsername(it))
        }
        loginViewModel.passwordState.observe(viewLifecycleOwner,
            Observer { passwordState ->
                if (passwordState == null) {
                    return@Observer
                }
                loginButton.isEnabled = passwordState.isValid
                Log.i("observe password", passwordState.toString())
                if (!passwordState.isValid) {
                    passwordEditText.error = passwordState.errorMessage
                } else {
                    passwordEditText.error = null
                }
            }
        )
        passwordEditText.afterTextChanged {
            loginViewModel.createEvent(LoginEvent.EnteredPassword(it))
        }
        passwordEditText.setOnFocusChangeListener { _, _ ->
            loginViewModel.createEvent(LoginEvent.PasswordFocusChange)
        }
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val username = loginViewModel.usernameState.value.toString()
                val password = loginViewModel.passwordState.value.toString()
                authViewModel.createEvent(AuthEvent.Login(username, password))
            }
            false
        }

        loginButton.setOnClickListener {
            val username = loginViewModel.usernameState.value.toString()
            val password = loginViewModel.passwordState.value.toString()
            authViewModel.createEvent(AuthEvent.Login(username, password))
        }

        authViewModel.state.observe(viewLifecycleOwner, Observer { authState ->
            if (authState == null) {
                return@Observer
            }
            if (authState.isProcessing) {
                loginButton.visibility = View.GONE
                loadingProgressBar.visibility = View.VISIBLE
            } else {
                loadingProgressBar.visibility = View.GONE
                loginButton.visibility = View.VISIBLE
            }
            if (authState.isAuthenticated) {
                val action = LoginFragmentDirections.actionLoginFragmentToWorkList()
                findNavController().navigate(action)
            }
        })
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).window.statusBarColor = Color.TRANSPARENT


    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).window.statusBarColor =
            resources.getColor(R.color.carnation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}
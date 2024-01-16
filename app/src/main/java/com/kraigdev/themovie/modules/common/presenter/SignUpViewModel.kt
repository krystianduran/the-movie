package com.kraigdev.themovie.modules.common.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kraigdev.themovie.core.auth.GoogleEmailAuthClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val googleEmailAuthClient: GoogleEmailAuthClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onSignUp(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            val signUpResult = googleEmailAuthClient.createUserWithEmailAndPassword(
                email = email,
                password = password
            )

            _state.update {
                it.copy(
                    isSignUpSuccessful = signUpResult.user != null,
                    signUpError = if (signUpResult.user == null) "Sign up failed" else null
                )
            }
        }
    }

    fun resetState() {
        _state.update { SignUpState() }
    }

    data class SignUpState(
        val isSignUpSuccessful: Boolean = false,
        val signUpError: String? = null
    )
}

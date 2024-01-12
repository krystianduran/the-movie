package com.kraigdev.themovie.modules.common.presenter

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.kraigdev.themovie.core.auth.GoogleAuthUiClient
import com.kraigdev.themovie.core.auth.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInWithEmailResult(result: AuthResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.user != null,
                signInError = if (result.user == null) "Sign in failed" else null
            )
        }
    }

    fun sendResultData(result: Intent?) {
        viewModelScope.launch {
            val signInResult = googleAuthUiClient.signInWithIntent(
                result ?: return@launch
            )
            onSignInWithGoogleResult(signInResult)
        }
    }

    private fun onSignInWithGoogleResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun signInIntentSender() {
        viewModelScope.launch {
            val signInIntentSender: IntentSender? = googleAuthUiClient.signIn()
            _state.update {
                it.copy(signInIntentSender = signInIntentSender)
            }
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    data class SignInState(
        val isSignInSuccessful: Boolean = false,
        val signInError: String? = null,
        val signInIntentSender: IntentSender? = null
    )
}

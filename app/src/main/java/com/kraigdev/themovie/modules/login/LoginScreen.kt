package com.kraigdev.themovie.modules.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.kraigdev.themovie.modules.common.presenter.SignInViewModel
import com.kraigdev.themovie.modules.common.presenter.composables.GoogleButton

@Composable
fun LoginScreen(
    state: SignInViewModel.SignInState,
    onGoogleSignInButton: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.signInError) {
        state.signInError?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    LoginScreenView(onGoogleSignInButton = onGoogleSignInButton)
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        state = SignInViewModel.SignInState(),
        onGoogleSignInButton = {}
    )
}

@Composable
private fun LoginScreenView(onGoogleSignInButton: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        GoogleButton(
            onClicked = onGoogleSignInButton,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge
        )
    }
}

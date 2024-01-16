package com.kraigdev.themovie.modules.splash.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kraigdev.themovie.core.navigation.AppScreens
import com.kraigdev.themovie.modules.common.presenter.composables.LogoView
import com.kraigdev.themovie.ui.theme.SplashBackground
import kotlinx.coroutines.delay

private const val SPLASH_DELAY = 1000L

@Composable
fun SplashScreen(
    state: SplashViewModel.UiState,
    onNavigate: (AppScreens) -> Unit
) {
    LaunchedEffect(state) {
        delay(SPLASH_DELAY)
        Log.d("SplashScreen", "showOnboarding: ${state.showOnboarding}")
        state.showOnboarding?.let {
            if (it) {
                onNavigate(AppScreens.Onboarding)
            } else {
                onNavigate(AppScreens.AuthNavigation)
            }
        }
    }

    SplashScreenView()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        state = SplashViewModel.UiState(),
        onNavigate = {}
    )
}

@Composable
private fun SplashScreenView() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = SplashBackground),
        contentAlignment = Alignment.Center
    ) {
        LogoView()
    }
}

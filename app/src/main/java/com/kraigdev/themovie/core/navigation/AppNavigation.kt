package com.kraigdev.themovie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kraigdev.themovie.core.navigation.routes.auth.authNavigation
import com.kraigdev.themovie.modules.main.MainScreen
import com.kraigdev.themovie.modules.onboarding.presentation.OnboardingScreen
import com.kraigdev.themovie.modules.onboarding.presentation.OnboardingViewModel
import com.kraigdev.themovie.modules.splash.presentation.SplashScreen
import com.kraigdev.themovie.modules.splash.presentation.SplashViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route
    ) {
        composable(AppScreens.Splash.route) {
            val splashViewModel = hiltViewModel<SplashViewModel>()
            val state by splashViewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                splashViewModel.showOnboarding()
            }

            SplashScreen(
                state = state,
                onNavigate = {
                    navController.popBackStack()
                    navController.navigate(it.route)
                }
            )
        }
        composable(AppScreens.Onboarding.route) {
            val onboardingViewModel = hiltViewModel<OnboardingViewModel>()
            val state by onboardingViewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(state) {
                if (state.success) {
                    navController.popBackStack()
                    navController.navigate(AppScreens.AuthNavigation.route)
                }
            }

            OnboardingScreen(
                onFinishOnboarding = {
                    onboardingViewModel.savePreferenceOnboarding()
                }
            )
        }
        authNavigation(
            navController = navController
        )
        composable(AppScreens.Main.route) {
            MainScreen()
        }
    }
}
